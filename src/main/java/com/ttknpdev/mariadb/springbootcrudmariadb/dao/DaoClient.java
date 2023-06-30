package com.ttknpdev.mariadb.springbootcrudmariadb.dao;

import com.ttknpdev.commonsresponsettknpdev.exception.config.ConfigExceptionHandler;
import com.ttknpdev.commonsresponsettknpdev.exception.handler.NotAllowedMethod;
import com.ttknpdev.commonsresponsettknpdev.response.CommonsResponseObject;
import com.ttknpdev.mariadb.springbootcrudmariadb.log.Logging;
import com.ttknpdev.mariadb.springbootcrudmariadb.entity.Client;
import com.ttknpdev.mariadb.springbootcrudmariadb.repository.ClientRepository;
import com.ttknpdev.mariadb.springbootcrudmariadb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ControllerAdvice
public class DaoClient extends Logging implements ClientService<Client> {

    private ClientRepository repository;
    @Autowired
    public DaoClient(ClientRepository repository) {
        daoClient.info("repository client autowired!");
        this.repository = repository;
    }

    public static void main(String[] args) {
        daoClient.info("repository client autowired!");

    }
    @ExceptionHandler
    public ResponseEntity getNotAllowed(NotAllowedMethod notAllowedMethod) {
        return ConfigExceptionHandler.getNotAllowedMethod(notAllowedMethod);
    }
    @ExceptionHandler
    public ResponseEntity getException(Exception exception) {
        return ConfigExceptionHandler.getExceptionMethod(exception);
    }

    @Override
    public Client create(Client obj) {

        try {
            // bad performance
            Client client = repository.save(obj);
            daoClient.debug(client);
            List<Client> clients = new ArrayList<>(repository.findAll());
            client.setIdentity((long) clients.size()); // size() return real rows of table
            return client;

        } catch (Exception e) {

            daoClient.debug("found some invalid value of Pojo : logging in create() method");
            throw new NotAllowedMethod(e.getMessage());

        }

    }

    @Override
    public Client read(String phone) {
        return repository.findById(phone)
                .orElseThrow(()->{
                    daoClient.debug("not found the phone on your table mariadb : logging in read() method");
                    throw new NotAllowedMethod("phone : "+phone+" it didn't exist");
                });
    }

    @Override
    public Client update(String phone, Client obj) {
        return repository.findById(phone)
                .map(client -> {
                    client.setState(obj.getState());
                    client.setSalary(obj.getSalary());
                    client.setFullname(obj.getFullname());
                    return repository.save(client);
                })
                .orElseThrow(()-> {
                    daoClient.debug("not found the phone on your table mariadb or Invalid value of Pojo : logging in update() method");
                    throw new NotAllowedMethod("phone : "+phone+" it didn't exist or invalid value of Pojo");
                });
    }

    @Override
    public List<Client> reads() {
        List<Client> clients = new ArrayList<>(repository.findAll());
        if (clients.size() < 0) {
            daoClient.debug("not found information clients on your table mariadb : logging in reads() method");
            throw new NotAllowedMethod("info clients didn't exist");
        }else {
            return repository.findAll();
        }
    }

    @Override
    public List<Client> readsByStatus(Integer status) {
        List<Client> clients = repository.readsAllByStatus(status);
        if (clients.size() < 0) {
            daoClient.debug("not found information clients by status "+status+" : logging in readsByStatus() method");
            throw new NotAllowedMethod("info clients didn't exist");
        }else {
            return clients;
        }
    }

    @Override
    public Map<?, ?> delete(String phone) {
        Map<String,Client> response = new HashMap<>();;
        return repository.findById(phone)
                .map(client -> {
                    response.put("deleted",client);
                    repository.delete(client);
                    return response;
                })
                .orElseThrow(()-> {
                    throw new NotAllowedMethod("phone : "+phone+" it didn't exist");
                });
    }

    @Override
    public Map<?, ?> deleteAllState(int state) {
        Map<String,Integer> response = new HashMap<>();
        try {
            int rows = repository.deleteAllByState(state);
            response.put("deleted",rows);
        }catch (Exception exception) {
            daoClient.debug("found errors "+exception.getMessage());
            throw new NotAllowedMethod("cause ,"+exception.getMessage());
        }
        return response;
    }
}
