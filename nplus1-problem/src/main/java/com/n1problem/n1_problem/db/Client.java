package com.n1problem.n1_problem.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")

//fifth way
@NamedEntityGraph(
        name = "clients_with_payments",
        attributeNodes = {
            @NamedAttributeNode("payments")
    }
)//you can create here many different EntityGraphs
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@BatchSize(size = 5)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Payment> payments = new ArrayList<>();


}
