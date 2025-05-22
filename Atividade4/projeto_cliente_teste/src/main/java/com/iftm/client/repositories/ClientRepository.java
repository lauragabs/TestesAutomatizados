package com.iftm.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iftm.client.entities.Client;

import java.time.Instant;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Busca clientes cujo nome contenha a palavra especificada, ignorando maiúsculas e minúsculas.
     *
     * @param palavra A palavra a ser buscada no nome dos clientes.
     * @return Uma lista de clientes cujo nome contém a palavra especificada.
     */
    @Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :palavra, '%'))")
    List<Client> findClientsByNameContaining(@Param("palavra") String palavra);

    /**
     * Busca clientes cuja renda esteja entre os valores mínimo e máximo especificados.
     *
     * @param rendaMinima O valor mínimo da renda.
     * @param rendaMaxima O valor máximo da renda.
     * @return Uma lista de clientes cuja renda está dentro do intervalo especificado.
     */
    List<Client> findClientsByIncomeBetween(Double rendaMinima, Double rendaMaxima);

    /**
     * Busca clientes cuja data de nascimento esteja entre as datas inicial e final especificadas.
     *
     * @param dataInicio A data inicial do intervalo.
     * @param dataFim A data final do intervalo.
     * @return Uma lista de clientes cuja data de nascimento está dentro do intervalo especificado.
     */
    List<Client> findClientsByBirthDateBetween(Instant dataInicio, Instant dataFim);
}