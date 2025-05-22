package com.iftm.client.repositories;

import com.iftm.client.entities.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ClientRepositoryTest {

    private final ClientRepository repositorioCliente;

    public ClientRepositoryTest(ClientRepository repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    /**
     * Testa a busca de clientes cujo nome contém uma palavra específica, ignorando diferenças de maiúsculas e minúsculas.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de clientes por nome contendo uma palavra, ignorando diferenças de maiúsculas e minúsculas.")
    void testaBuscaClientesPorNomeContendo() {
        // Arrange
        String palavra = "maria";

        // Act
        List<Client> resultado = repositorioCliente.findClientsByNameContaining(palavra);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Carolina Maria de Jesus", resultado.get(0).getName());
    }

    /**
     * Testa a busca de clientes cujo nome contém parte de uma string específica, ignorando diferenças de maiúsculas e minúsculas.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de clientes por nome contendo parte de uma string, ignorando diferenças de maiúsculas e minúsculas.")
    void testaBuscaClientesPorNomeContendoParte() {
        // Arrange
        String palavra = "silvio";

        // Act
        List<Client> resultado = repositorioCliente.findClientsByNameContaining(palavra);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Silvio Almeida", resultado.get(0).getName());
    }

    /**
     * Testa a busca de clientes cuja renda está dentro de um intervalo especificado.
     * Verifica se o resultado contém os clientes esperados.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com renda dentro de um intervalo especificado.")
    void testaBuscaClientesPorRendaEntre() {
        // Arrange
        Double rendaMinima = 2000.0;
        Double rendaMaxima = 3000.0;

        // Act
        List<Client> resultado = repositorioCliente.findClientsByIncomeBetween(rendaMinima, rendaMaxima);

        // Assert
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Lázaro Ramos", resultado.stream().filter(c -> c.getName().equals("Lázaro Ramos")).findFirst().orElse(null).getName());
        assertEquals("Gilberto Gil", resultado.stream().filter(c -> c.getName().equals("Gilberto Gil")).findFirst().orElse(null).getName());
        assertEquals("Jorge Amado", resultado.stream().filter(c -> c.getName().equals("Jorge Amado")).findFirst().orElse(null).getName());
    }

    /**
     * Testa a busca de clientes cuja data de nascimento está dentro de um intervalo especificado.
     * Verifica se o resultado contém os clientes esperados.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com data de nascimento dentro de um intervalo especificado.")
    void testaBuscaClientesPorDataNascimentoEntre() {
        // Arrange
        Instant dataInicio = Instant.parse("1990-01-01T00:00:00Z");
        Instant dataFim = Instant.parse("2000-01-01T00:00:00Z");

        // Act
        List<Client> resultado = repositorioCliente.findClientsByBirthDateBetween(dataInicio, dataFim);

        // Assert
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Lázaro Ramos", resultado.stream().filter(c -> c.getName().equals("Lázaro Ramos")).findFirst().orElse(null).getName());
        assertEquals("Carolina Maria de Jesus", resultado.stream().filter(c -> c.getName().equals("Carolina Maria de Jesus")).findFirst().orElse(null).getName());
        assertEquals("Jose Saramago", resultado.stream().filter(c -> c.getName().equals("Jose Saramago")).findFirst().orElse(null).getName());
    }
}