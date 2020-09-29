package com.khh.multidatabases.controllers;

import com.khh.multidatabases.domain.d2.User;
import com.khh.multidatabases.repository.d2.D2UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(D2UserController.URI_PREFIX)
public class D2UserController {
    public static final String URI_PREFIX = "/d2/users";

    @Autowired
    D2UserRepositry d2UserRepositry;

    public D2UserController(
//            D2UserRepositry userRepositry
//            @Qualifier("d1DataSource") DataSource d1DataSource,
//            @Qualifier("d2DataSource") DataSource d2DataSource
    ) {
//        this.d2UserRepositry = userRepositry;
    }
    @GetMapping(value = {"", "/"})
    public List<User> index() {
        return d2UserRepositry.findAll();
    }

    @GetMapping(value = "/create")
    public User create() {
        return d2UserRepositry.save(User.builder().name(UUID.randomUUID().toString()).build());
    }


}
