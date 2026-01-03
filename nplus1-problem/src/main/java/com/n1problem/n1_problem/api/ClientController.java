package com.n1problem.n1_problem.api;

import com.n1problem.n1_problem.db.Client;
import com.n1problem.n1_problem.db.ClientRepository;
import com.n1problem.n1_problem.db.CustomClientRepository;
import com.n1problem.n1_problem.db.Payment;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final ClientRepository clientRepository;

    private final CustomClientRepository customRepository;

    @GetMapping("/clients")
    public List<ClientDto> getAllClients() {
        log.info("Called method getAllClients");

        //1st way
        //List<Client> clients = clientRepository.findAll();

        //2nd way
        //List<Client> clients = customRepository.findAllClientWithPayments();

        //3rd way
        //List<Client> clients = clientRepository.findAllWithPayments();

        //4th,5th way
        //List<Client> clients = customRepository.findAllClientWithPaymentsWithGraph();

        //6th way
        //List<Client> clients = clientRepository.findAllWithPaymentsWithGraph();

        //7th(batchsize) way and 8th(subselect) way(like 1st way)
        List<Client> clients = clientRepository.findAll();

        var dtoList = clients.stream()
                .map(this::mapClientToDto)
                .toList();

        log.info("Method getAllClients() returning result");
        return dtoList;
    }

    private ClientDto mapClientToDto(Client client) {
        log.info("Mapping client to dto: clientId={}", client.getId());
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getPayments()
                        .stream()
                        .map(this::mapPaymentToDto)
                        .toList()
        );
    }

    private PaymentDto mapPaymentToDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getAmount(),
                payment.getClientId()
        );
    }
}
