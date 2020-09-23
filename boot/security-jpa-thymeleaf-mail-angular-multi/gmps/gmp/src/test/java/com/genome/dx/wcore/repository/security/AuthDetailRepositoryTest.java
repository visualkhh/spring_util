package com.genome.dx.wcore.repository.security;

import com.genome.dx.GmpApplication;
import com.genome.dx.wcore.domain.security.AuthDetail;
import com.genome.dx.wcore.domain.security.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GmpApplication.class)
@ActiveProfiles("local")
@Slf4j
@Transactional
public class AuthDetailRepositoryTest {

    @Autowired
    AuthDetailRepository authDetailRepository;


    @Test
    public void findAuthByAdmLginId() {
//        System.out.println("-----");
//        List<AuthDetail> omnifit = authDetailRepository.findByAdmLginId("omnifit");
//        log.debug("omnifit: {}", omnifit);
    }

    @Test
    public void findByAdmLginId() {
    }
}
