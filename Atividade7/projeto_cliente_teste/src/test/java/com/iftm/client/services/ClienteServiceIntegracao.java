package com.iftm.client.services; // Corrected package declaration

import com.iftm.client.dto.ClientDTO;
import com.iftm.client.entities.Client;
import com.iftm.client.repositories.ClientRepository;
import com.iftm.client.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@SpringBootTest
@Transactional
class ClientServiceIntegracao {

    @Autowired
    private ClientService service;

    @Autowired
    private ClientRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Client client;

    /**
     * Executado antes de cada teste.
     * Limpa o banco, insere um cliente e define os IDs de teste.
     */
    @BeforeEach
    void setUp() {
        repository.deleteAll();

        client = Client.builder()
                .name("John Doe")
                .cpf("12345678901")
                .income(3000.0)
                .birthDate(Instant.parse("1990-01-01T00:00:00Z"))
                .children(2)
                .build();

        client = repository.save(client);
        existingId = client.getId();
        nonExistingId = 999L;
    }

    /**
     * Testa se o método {@code findAllPaged} retorna uma página
     * contendo pelo menos um {@code ClientDTO} quando há registros no banco.
     */
    @Test
    void findAllPagedShouldReturnPageOfClientDTOs() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ClientDTO> result = service.findAllPaged(pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.getTotalElements());
    }

    /**
     * Testa se o método {@code findById} retorna corretamente um
     * {@code ClientDTO} quando o ID fornecido existe.
     */
    @Test
    void findByIdShouldReturnClientDTOWhenIdExists() {
        ClientDTO dto = service.findById(existingId);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals("John Doe", dto.getName());
    }

    /**
     * Testa se o método {@code findById} lança uma exceção
     * {@code ResourceNotFoundException} quando o ID não existe.
     */
    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    /**
     * Testa se o método {@code insert} insere um novo cliente e retorna
     * um {@code ClientDTO} com os dados corretos.
     */
    @Test
    void insertShouldReturnClientDTO() {
        ClientDTO dto = ClientDTO.builder()
                .name("Jane Smith")
                .cpf("98765432100")
                .income(5000.0)
                .birthDate(Instant.parse("1985-05-20T00:00:00Z"))
                .children(1)
                .build();

        ClientDTO inserted = service.insert(dto);

        Assertions.assertNotNull(inserted.getId());
        Assertions.assertEquals("Jane Smith", inserted.getName());
    }

    /**
     * Testa se o método {@code update} atualiza os dados corretamente
     * quando o ID existe.
     */
    @Test
    void updateShouldReturnClientDTOWhenIdExists() {
        ClientDTO dto = ClientDTO.builder()
                .name("Updated Name")
                .cpf("11122233344")
                .income(4000.0)
                .birthDate(Instant.parse("1992-03-15T00:00:00Z"))
                .children(3)
                .build();

        ClientDTO updated = service.update(existingId, dto);

        Assertions.assertEquals("Updated Name", updated.getName());
        Assertions.assertEquals(3, updated.getChildren());
    }

}