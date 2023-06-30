package com.ttknpdev.mariadb.springbootcrudmariadb.controller;

import com.ttknpdev.commonsresponsettknpdev.constants.CommonsConstants;
import com.ttknpdev.commonsresponsettknpdev.response.CommonsResponseObject;
import com.ttknpdev.mariadb.springbootcrudmariadb.entity.Client;
import com.ttknpdev.mariadb.springbootcrudmariadb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class EndpointClient {
    private ClientService service;
    @Autowired
    public EndpointClient(ClientService service) {
        this.service = service;
    }
    @GetMapping(value = "/reads")
    private ResponseEntity reads() {
        return ResponseEntity
                .status(CommonsConstants.STATUS_ACCEPTED)
                .body(CommonsResponseObject.<List<Client>>builder()
                        .code(CommonsConstants.STRING_ACCEPTED)
                        .status(CommonsConstants.STATUS_ACCEPTED)
                        .info(service.reads())
                        .build());
    }

    @GetMapping(value = "/read/{phone}")
    private ResponseEntity read(@PathVariable String phone) {
        return ResponseEntity
                .status(CommonsConstants.STATUS_ACCEPTED)
                .body(CommonsResponseObject.<Client>builder()
                        .code(CommonsConstants.STRING_ACCEPTED)
                        .status(CommonsConstants.STATUS_ACCEPTED)
                        .info((Client) service.read(phone))
                        .build());
    }

    @GetMapping(value = "/reads/status/{status}")
    private ResponseEntity readsByStatus(@PathVariable Integer status) {
        return ResponseEntity
                .status(CommonsConstants.STATUS_ACCEPTED)
                .body(CommonsResponseObject.<List<Client>>builder()
                        .code(CommonsConstants.STRING_ACCEPTED)
                        .status(CommonsConstants.STATUS_ACCEPTED)
                        .info( service.readsByStatus(status))
                        .build());
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody Client client) {
        return ResponseEntity
                .status(CommonsConstants.STATUS_CREATED)
                .body(CommonsResponseObject.<Client>builder()
                        .code(CommonsConstants.STRING_CREATED)
                        .status(CommonsConstants.STATUS_CREATED)
                        .info((Client) service.create(client))
                        .build());
    }

    @PutMapping(value = "/update/{phone}")
   private ResponseEntity update(@RequestBody Client client , @PathVariable String phone) {
        return ResponseEntity
                .status(CommonsConstants.STATUS_OK)
                .body(CommonsResponseObject.<Client>builder()
                        .code(CommonsConstants.STRING_OK)
                        .status(CommonsConstants.STATUS_OK)
                        .info((Client) service.update(phone,client))
                        .build());
    }
   @DeleteMapping(value = "/delete/{phone}")
   private ResponseEntity delete(@PathVariable String phone) {
       return ResponseEntity
               .status(CommonsConstants.STATUS_OK)
               .body(CommonsResponseObject.<Map<String,Client>>builder()
                       .code(CommonsConstants.STRING_OK)
                       .status(CommonsConstants.STATUS_OK)
                       .info((Map<String, Client>) service.delete(phone))
                       .build());
   }

    @DeleteMapping(value = "/delete/state/{sta}")
    private ResponseEntity deleteAllState(@PathVariable int sta) {
        return ResponseEntity
                .status(CommonsConstants.STATUS_OK)
                .body(CommonsResponseObject.<Map<String,Integer>>builder()
                        .code(CommonsConstants.STRING_OK)
                        .status(CommonsConstants.STATUS_OK)
                        .info(service.deleteAllState(sta))
                        .build());
    }
}



