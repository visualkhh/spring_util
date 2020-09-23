package com.genome.dx.wcore.repository.security;

import com.genome.dx.ThesisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThesisApplication.class)
@ActiveProfiles("prod")
@Slf4j
@Transactional
public class AuthDetailRepositoryTest {

    @Test
    public void findAuthByAdmLginId() {
    }

    @Test
    public void findByAdmLginId() {
    }
}
