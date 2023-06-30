package com.ttknpdev.mariadb.springbootcrudmariadb.testing;

import com.ttknpdev.mariadb.springbootcrudmariadb.dao.DaoClient;
import com.ttknpdev.mariadb.springbootcrudmariadb.entity.Client;
import com.ttknpdev.mariadb.springbootcrudmariadb.repository.ClientRepository;
import com.ttknpdev.mariadb.springbootcrudmariadb.service.ClientService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
@DataJpaTest
@Rollback(value = false)
@Transactional
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Testing {
    @Autowired
    private ClientRepository repository;
    private ClientService service;
    @Autowired
    private void setService() {
        this.service = new DaoClient(repository);
    }

    @Test
    public void create() {
        Client clientA = new Client();
        clientA.setFullname("Mark Ryder");
        clientA.setPhone("0746523214");
        clientA.setSalary(19000F);
        clientA.setState(true);
        assertNotNull("maybe create() method failed for creating!!",service.create(clientA));
    }

    @Test
    public void read() {
        assertNotNull("maybe read() method failed for retrieving!!",service.read("0746523214"));
    }

    @Test
    public void reads() {
        assertNotNull("maybe reads() method failed for retrieving!!",service.reads());
    }

    @Test
    public void update() {
        Client clientA = new Client();
        clientA.setFullname("Json Ryder");
        clientA.setPhone("0746523214");
        clientA.setSalary(12000F);
        clientA.setState(false);
        assertNotNull(service.update("0746523214" , clientA));
    }
}
