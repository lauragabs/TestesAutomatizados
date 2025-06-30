package com.iftm.client.repositories;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.iftm.client.entities.Client;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository repositorioCliente;

    /**
     * Testa a busca de clientes cujo nome contém uma palavra específica, ignorando
     * diferenças de maiúsculas e minúsculas.
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
     * Testa a busca de clientes cujo nome contém parte de uma string específica,
     * ignorando diferenças de maiúsculas e minúsculas.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de clientes por nome contendo parte de uma string, ignorando diferenças de maiúsculas e minúsculas.")
    void testaBuscaClientesPorNomeContendoParte() {
        // Arrange
        String palavra = "on";

        // Act
        List<Client> resultado = repositorioCliente.findClientsByNameContaining(palavra);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Conceição Evaristo", resultado.get(0).getName());
        assertEquals("Toni Morrison", resultado.get(1).getName());
    }

    /**
     * Testa a busca de clientes cuja renda está dentro de um intervalo
     * especificado.
     * Verifica se o resultado contém os clientes esperados.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com renda dentro de um intervalo especificado.")
    void testaBuscaClientesPorRendaEntre() {
        // Arrange
        Double rendaMinima = 4000.0;
        Double rendaMaxima = 6000.0;

        // Act
        List<Client> resultado = repositorioCliente.findClientsByIncomeBetween(rendaMinima, rendaMaxima);

        // Assert
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Djamila Ribeiro", resultado.stream().filter(c -> c.getName().equals("Djamila Ribeiro"))
                .findFirst().orElse(null).getName());
        assertEquals("Jose Saramago",
                resultado.stream().filter(c -> c.getName().equals("Jose Saramago")).findFirst().orElse(null).getName());
        assertEquals("Silvio Almeida", resultado.stream().filter(c -> c.getName().equals("Silvio Almeida")).findFirst()
                .orElse(null).getName());
    }

    /**
     * Testa a busca de clientes cuja data de nascimento está num intervalo
     * especificado.
     * Verifica se o resultado contém os clientes esperados.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com data de nascimento dentro de um intervalo especificado.")
    void testaBuscaClientesPorDataNascimentoEntre() {
        // Arrange
        Instant dataInicio = Instant.parse("1996-12-23T07:00:00Z");
        Instant dataFim = Instant.parse("2020-07-12T20:50:00Z");

        // Act
        List<Client> resultado = repositorioCliente.findClientsByBirthDateBetween(dataInicio, dataFim);

        // Assert
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("Lázaro Ramos",
                resultado.stream().filter(c -> c.getName().equals("Lázaro Ramos")).findFirst().orElse(null).getName());
        assertEquals("Carolina Maria de Jesus", resultado.stream()
                .filter(c -> c.getName().equals("Carolina Maria de Jesus")).findFirst().orElse(null).getName());
        assertEquals("Jose Saramago",
                resultado.stream().filter(c -> c.getName().equals("Jose Saramago")).findFirst().orElse(null).getName());
    }

    /**
     * Testa a busca de um cliente com o salario maior que um valor especificado.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com salario maior que um valor especificado.")
    void testaBuscaClientesPorSalarioMaiorQue() {
        // Arrange
        Double salario = 5000.0;

        // Act
        List<Client> resultado = repositorioCliente.findClientsByIncomeGreaterThan(salario);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carolina Maria de Jesus", resultado.stream()
                .filter(c -> c.getName().equals("Carolina Maria de Jesus")).findFirst().orElse(null).getName());
        assertEquals("Toni Morrison",
                resultado.stream().filter(c -> c.getName().equals("Toni Morrison")).findFirst().orElse(null).getName());
    }

    /**
     * Testa a busca de um cliente com o salario menor que um valor especificado.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de clientes com salario menor que um valor especificado.")
    void testaBuscaClientesPorSalarioMenorQue() {
        // Arrange
        Double salario = 3000.0;

        // Act
        List<Client> resultado = repositorioCliente.findClientsByIncomeLessThan(salario);

        // Assert
        assertNotNull(resultado);
        assertEquals(6, resultado.size());
        assertEquals("Conceição Evaristo", resultado.stream().filter(c -> c.getName().equals("Conceição Evaristo"))
                .findFirst().orElse(null).getName());
        assertEquals("Lázaro Ramos",
                resultado.stream().filter(c -> c.getName().equals("Lázaro Ramos")).findFirst().orElse(null).getName());
        assertEquals("Gilberto Gil",
                resultado.stream().filter(c -> c.getName().equals("Gilberto Gil")).findFirst().orElse(null).getName());
        assertEquals("Yuval Noah Harari", resultado.stream().filter(c -> c.getName().equals("Yuval Noah Harari"))
                .findFirst().orElse(null).getName());
        assertEquals("Chimamanda Adichie", resultado.stream().filter(c -> c.getName().equals("Chimamanda Adichie"))
                .findFirst().orElse(null).getName());
        assertEquals("Jorge Amado",
                resultado.stream().filter(c -> c.getName().equals("Jorge Amado")).findFirst().orElse(null).getName());
    }

    /**
     * Testa a busca de um cliente pelo nome exato, ignorando diferenças de
     * maiúsculas e minúsculas.
     * Verifica se o resultado contém o cliente esperado.
     */
    @Test
    @DisplayName("Verifica a busca de cliente pelo nome exato, ignorando diferenças de maiúsculas e minúsculas.")
    void testaBuscaClientePorNomeExato() {
        // Arrange
        String nome = "Conceição Evaristo";

        // Act
        Client resultado = repositorioCliente.findClientByNameIgnoreCase(nome);

        // Assert
        assertNotNull(resultado);
        assertEquals("Conceição Evaristo", resultado.getName());
    }

    /**
     * Testa a busca de um cliente pelo nome exato, ignorando diferenças de
     * maiúsculas e minúsculas.
     * Verifica se o resultado retorna null quando o cliente não é encontrado.
     */
    @Test
    @DisplayName("Verifica a busca de cliente pelo nome exato, ignorando diferenças de maiúsculas e minúsculas.")
    void testaBuscaClientePorNomeExatoNaoEncontrado() {
        // Arrange
        String nome = "Nome Inexistente";

        // Act
        Client resultado = repositorioCliente.findClientByNameIgnoreCase(nome);

        // Assert
        assertNull(resultado);
    }

}