package com.khh.multidatabases.controllers;

import com.khh.multidatabases.domain.d1.User;
import com.khh.multidatabases.repository.d1.D1UserRepositry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(D1UserController.URI_PREFIX)
public class D1UserController {
    public static final String URI_PREFIX = "/d1/users";


    DataSource d2DataSource;
    DataSource d1DataSource;

    D1UserRepositry d1UserRepositry;

    public D1UserController(
            D1UserRepositry userRepositry
//            @Qualifier("d1DataSource") DataSource d1DataSource,
//            @Qualifier("d2DataSource") DataSource d2DataSource
    ) {
        this.d1UserRepositry = userRepositry;
//        this.d1DataSource = d1DataSource;
//        this.d2DataSource = d2DataSource;
    }

    @GetMapping(value = {"", "/"})
    public List<User> index() {
        return d1UserRepositry.findAll();
    }

    @GetMapping(value = "/create")
    public User create() {
        return d1UserRepositry.save(User.builder().name(UUID.randomUUID().toString()).build());
    }


}
