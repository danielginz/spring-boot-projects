package com.n1problem.n1_problem.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    //@Query("select c from Client c join fetch c.payments")
    //@Override
    List<Client> findAll();

    //you can also to create your own method like findAllWithPayments(instead of overriding findAll
    @Query("select c from Client c join fetch c.payments")
    List<Client> findAllWithPayments();

    @Query("select c from Client c")
    //@EntityGraph(attributePaths = {"payments"})
    @EntityGraph("clients_with_payments")
    List<Client> findAllWithPaymentsWithGraph();
}
