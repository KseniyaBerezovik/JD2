package by.itacademy.controller;

import by.itacademy.entity.otherEntity.Address;
import by.itacademy.entity.userEntity.Client;
import by.itacademy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(path = "/client")
    public String getUserInfo() {
        return "client";
    }

    @PostMapping(path = "/client")
    public String saveUser(Client client, Address address, Model model) {
        client.setAddress(address);
        Long id = clientService.save(client);
        model.addAttribute("clientID", id);
        return "redirect:/client-info/{clientID}";
    }

    @GetMapping(path = "/client-info/{clientID}")
    public String seeClient(@PathVariable("clientID") Long clientID, Model model) {
        Client client = clientService.getByID(clientID);
        model.addAttribute("client", client);
        return "client-info";
    }
}