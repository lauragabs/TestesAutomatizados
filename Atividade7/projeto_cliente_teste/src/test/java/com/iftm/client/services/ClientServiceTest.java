package com.iftm.client.services;

import com.iftm.client.dto.ClientDTO;
import com.iftm.client.entities.Client;
import com.iftm.client.repositories.ClientRepository;
import com.iftm.client.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {

    @InjectMocks
    private ClientService service;

    @Mock
    private ClientRepository repository;

    // • findAllPaged deveria retornar uma página com todos os clientes
    // (e chamar o método findAll do repository)

    /**
     * Verifica se o método {@code findAllPaged} retorna uma página de
     * {@code ClientDTOs}
     * quando um {@code PageRequest} válido é fornecido.
     */
    @Test
    void findAllPagedShouldReturnPageOfClientDTOsWhenPageRequestIsValid() {
        // Arrange
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Client> clients = List.of(createClient());

        Page<Client> page = new PageImpl<>(clients);

        when(repository.findAll(pageRequest)).thenReturn(page);

        // Act
        Page<ClientDTO> result = service.findAllPaged(pageRequest);

        // Assert
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("John Doe", result.getContent().get(0).getName());
        verify(repository, times(1)).findAll(pageRequest);
    }

    /**
     * Verifica se o método {@code findAllPaged} retorna uma página vazia de
     * {@code ClientDTOs}
     * quando não existem clientes no repositório.
     */
    @Test
    void findAllPagedShouldReturnEmptyPageWhenNoClientsExist() {
        // Arrange
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Client> emptyPage = Page.empty();

        when(repository.findAll(pageRequest)).thenReturn(emptyPage);

        // Act
        Page<ClientDTO> result = service.findAllPaged(pageRequest);

        // Assert
        Assertions.assertTrue(result.isEmpty());
        verify(repository, times(1)).findAll(pageRequest);
    }

    // • findById deveria
    // ◦ retornar um ClientDTO quando o id existir
    // ◦ lançar ResourceNotFoundException quando o id não existir

    /**
     * Testa se o método {@code findById} retorna um {@code ClientDTO}
     * quando o ID fornecido existe no repositório.
     */
    @Test
    void findByIdShouldReturnClientDTOWhenIdExists() {

        // Arrange
        Long id = 1L;
        Optional<Client> obj = Optional.of(createClient());

        when(repository.findById(id)).thenReturn(obj);

        // Act
        ClientDTO clientDTO = service.findById(id);

        // Assert
        Assertions.assertEquals(1, clientDTO.getId());
        Assertions.assertEquals("John Doe", clientDTO.getName());
        verify(repository, times(1)).findById(1L);
    }

    /**
     * Testa se o método {@code findById} lança uma exceção
     * {@code ResourceNotFoundException}
     * quando o ID fornecido não existe no repositório.
     */
    @Test
    void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        // Arrange
        Long id = 1L;
        Optional<Client> obj = Optional.empty();

        when(repository.findById(id)).thenReturn(obj);

        // Act e Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(id);
        });

        verify(repository, times(1)).findById(id);
    }

    // • insert deveria retornar um ClientDTO ao inserir um novo cliente

    /**
     * Testa se o método {@code insert} retorna um {@code ClientDTO}
     * ao inserir um novo cliente no repositório.
     */
    @Test
    void insertShouldReturnClientDTO() {
        // Arrange
        ClientDTO dto = createClientDTO();
        Client client = createClient();
        when(repository.save(any(Client.class))).thenReturn(client);

        // Act
        ClientDTO clientDTO = service.insert(dto);

        // Assert
        Assertions.assertEquals(dto.getCpf(), clientDTO.getCpf());
        Assertions.assertEquals(dto.getChildren(), clientDTO.getChildren());
    }

    // • update deveria
    // ◦ retornar um ClientDTO quando o id existir
    // ◦ lançar uma ResourceNotFoundException quando o id não existir

    /**
     * Testa se o método {@code update} retorna um {@code ClientDTO}
     * quando o ID fornecido existe no repositório.
     */
    @Test
    void updateShouldReturnClientDTOWhenIdExists() {
        // Arrange
        Long id = 1L;
        ClientDTO dto = createClientDTO();
        Client client = createClient();

        when(repository.getOne(id)).thenReturn(client);
        when(repository.save(client)).thenReturn(client);

        // Act
        ClientDTO clientDTO = service.update(id, dto);

        // Assert
        Assertions.assertEquals(dto.getId(), clientDTO.getId());
        Assertions.assertEquals(dto.getCpf(), clientDTO.getCpf());

    }

    /**
     * Testa se o método {@code update} lança uma exceção
     * {@code ResourceNotFoundException}
     * quando o ID fornecido não existe no repositório.
     */
    @Test
    void updateShouldThrowEntityNotFoundExceptionWhenIdDoesNotExists() {
        // Arrange
        Long id = 1L;
        ClientDTO dto = createClientDTO();
        when(repository.getOne(id)).thenThrow(new EntityNotFoundException("Id não encontrado"));

        // Act e Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(id, dto);
        });

        verify(repository, times(1)).getOne(id);

    }

    // • delete deveria
    // ◦ retornar vazio quando o id existir
    // ◦ lançar uma EmptyResultDataAccessException quando o id não existir
    /**
     * Testa se o método {@code delete} não lança exceções
     * quando o ID fornecido existe no repositório.
     */
    @Test
    void deleteShouldReturnEmptyWhenIdExists() {
        // Arrange
        Long id = 1L;
        doNothing().when(repository).deleteById(id);

        // Act e Assert
        Assertions.assertDoesNotThrow(() -> service.delete(id));

        verify(repository, times(1)).deleteById(id);
    }

    /**
     * Testa se o método {@code delete} lança uma exceção
     * {@code ResourceNotFoundException}
     * quando o ID fornecido não existe no repositório.
     */
    @Test
    void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        // Arrange
        Long id = 1L;
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(id);

        // Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(id));

        verify(repository, times(1)).deleteById(id);
    }

    // • findByIncome deveria retornar uma página com os clientes que tenham o
    // Income
    // informado (e chamar o mét-odo findByIncome do repository)

    /**
     * Testa se o método {@code findByIncome} retorna uma página de
     * {@code ClientDTOs}
     * quando um valor de renda é fornecido.
     */
    @Test
    void findByIncomeShouldReturnPageOfClientDTOsWhenIncomeIsProvided() {
        // Arrange
        Double income = 3000.0; // agora o income confere com o client criado
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Client> clients = List.of(createClient());
        Page<Client> page = new PageImpl<>(clients, pageRequest, clients.size());

        when(repository.findByIncome(eq(income), eq(pageRequest))).thenReturn(page);

        // Act
        Page<ClientDTO> result = service.findByIncome(income, pageRequest);

        // Assert
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals("John Doe", result.getContent().get(0).getName());
        verify(repository, times(1)).findByIncome(eq(income), eq(pageRequest));
    }

    private Client createClient() {
        return Client.builder()
                .id(1L)
                .name("John Doe")
                .cpf("12345678901")
                .income(3000.0)
                .birthDate(Instant.now())
                .children(2)
                .build();
    }

    private ClientDTO createClientDTO() {
        return ClientDTO.builder()
                .id(1L)
                .name("John Doe")
                .cpf("12345678901")
                .income(3000.0)
                .birthDate(Instant.now())
                .children(2)
                .build();
    }
}