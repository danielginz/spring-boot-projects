package com.n1problem.n1_problem.db;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomClientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Client> findAllClientWithPayments() {
        String jpql = "select c from Client c join fetch c.payments";
        return entityManager.createQuery(jpql, Client.class).getResultList();
    }

    //forth way
    /*public List<Client> findAllClientWithPaymentsWithGraph() {
        EntityGraph<Client> graph = entityManager.createEntityGraph(Client.class);
        graph.addSubgraph("payments");
        return entityManager.createQuery("select c from Client c", Client.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
    }*/

    //fifth way
    public List<Client> findAllClientWithPaymentsWithGraph() {
        EntityGraph<Client> graph = (EntityGraph<Client>) entityManager.getEntityGraph("clients_with_payments");

        return entityManager.createQuery("select c from Client c", Client.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
    }
}
