package com.clone.chat;

import com.clone.chat.domain.UserRoom;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChatApplication.class)
@ActiveProfiles("dev")
//@Transactional
@Slf4j
public class TestTest {


    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

//    @Autowired
//    private TestRestTemplate restTemplate;
//	@Value("${project.properties.header-name}")
//	String headerName = null;

    @BeforeEach()
    public void initMockMvc() {
        this.mockMvc = webAppContextSetup(wac).build();
    }


    @Test
    @Transactional
    public void 테스트() throws Exception {

        HttpHeaders headers = new HttpHeaders();


        MvcResult result = mockMvc.perform(get("/apis/chats/rooms").headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
//		.andExpect(content().contentType(APPLICATION_JSON));
//		.andExpect(jsonPath("$.hello", is("world")));

        String content = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

//        UserInChatRoom[] user = mapper.readValue(content, UserInChatRoom[].class);
//        List<UserInChatRoom> rooms = Arrays.asList(mapper.readValue(content, UserInChatRoom[].class));
        List<UserRoom> rooms = mapper.readValue(content, new TypeReference<List<UserRoom>>() {});
        log.info("--> {}", rooms);
//        System.err.println("JSon Result=" + mapper.defaultPrettyPrintingWriter().writeValueAsString(user));
//        assertTrue(user.getResult().equals("ok"));


//        ResponseEntity<List<UserInChatRoom>> response = restTemplate.getForEntity("/apis/chats/rooms", List.class);
//        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        then(response.getBody()).isNotNull();
    }

}
