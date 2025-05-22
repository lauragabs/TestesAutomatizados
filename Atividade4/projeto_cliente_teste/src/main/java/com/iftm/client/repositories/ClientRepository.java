package com.iftm.client.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iftm.client.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Busca clientes cujo nome contenha a palavra especificada, ignorando
     * maiúsculas e minúsculas.
     *
     * @param palavra A palavra a ser buscada no nome dos clientes.
     * @return Uma lista de clientes cujo nome contém a palavra especificada.
     */
    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :palavra, '%'))")
    List<Client> findClientsByNameContaining(@Param("palavra") String palavra);

    /**
     * Busca clientes cuja renda esteja entre os valores mínimo e máximo
     * especificados.
     *
     * @param rendaMinima O valor mínimo da renda.
     * @param rendaMaxima O valor máximo da renda.
     * @return Uma lista de clientes cuja renda está dentro do intervalo
     *         especificado.
     */
    List<Client> findClientsByIncomeBetween(Double rendaMinima, Double rendaMaxima);

    /**
     * Busca clientes cuja data de nascimento esteja entre as datas inicial e final
     * especificadas.
     *
     * @param dataInicio A data inicial do intervalo.
     * @param dataFim    A data final do intervalo.
     * @return Uma lista de clientes cuja data de nascimento está dentro do
     *         intervalo especificado.
     */
    List<Client> findClientsByBirthDateBetween(Instant dataInicio, Instant dataFim);

    /**
     * Busca cliente pelo nome(exato).
     *
     * @param nome O nome do cliente a ser buscado.
     * @return O cliente encontrado ou null se não for encontrado.
     */
    @Query("SELECT c FROM Client c WHERE LOWER(c.name) = LOWER(:nome)")
    Client findClientByNameIgnoreCase(@Param("nome") String nome);

    /**
     * Busca cliente pelo salario maior que um valor.
     *
     * @param renda O valor da renda a ser buscado.
     * @return Uma lista de clientes cuja renda é maior que o valor especificado.
     */
    List<Client> findClientsByIncomeGreaterThan(Double renda);

    /**
     * Busca cliente pelo salario menor que um valor.
     *
     * @param renda O valor da renda a ser buscado.
     * @return Uma lista de clientes cuja renda é menor que o valor especificado.
     */
    List<Client> findClientsByIncomeLessThan(Double renda);
}
