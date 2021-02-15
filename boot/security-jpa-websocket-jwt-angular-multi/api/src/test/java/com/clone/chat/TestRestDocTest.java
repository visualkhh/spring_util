package com.clone.chat;

import com.clone.chat.controller.api.anon.AnonApisController;
import com.clone.chat.service.ChatService;
import com.clone.chat.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("dev")
//@SpringBootTest(classes = ChatApplication.class)
//@AutoConfigureRestDocs(outputDir = "target/snippets")
@SpringBootTest(classes = ChatApplication.class)
@AutoConfigureRestDocs
//@ComponentScan("**")
@ComponentScan(basePackages = "com.clone.chat")
//@AutoConfigureWebClient
@Slf4j
public class TestRestDocTest {


    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @MockBean
    private AnonApisController userController;
    @MockBean
    private UserService userService;
//    @Autowired
//    private WebApplicationContext wac;
//    private MockMvc mockMvc;

//    @Autowired
//    RestDocumentationContextProvider restDocumentation;
//    @Autowired
//    private TestRestTemplate restTemplate;
//	@Value("${project.properties.header-name}")
//	String headerName = null;

    @BeforeEach()
    public void initMockMvc(WebApplicationContext webApplicationContext,
                            RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
//                .alwaysDo(document("{method-name}",
//                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }


    @Test
    @Transactional
    public void 테스트() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> crud = new HashMap<>();
        crud.put("title", "Sample Model");
        crud.put("body", "http://www.baeldung.com/");

        this.mockMvc.perform(get("/apis/chats/rooms").content(objectMapper.writeValueAsString(crud))).andExpect(status().isOk())
                .andDo(document("index"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

//        this.mockMvc.perform(get("/apis/chats/rooms").content(objectMapper.writeValueAsString(crud)))
//                .andExpect(status().isOk())
//                .andDo(document("index",
//                        links(linkWithRel("crud").description("The CRUD resource")),
//                        responseFields(subsectionWithPath("_links").description("Links to other resources")),
//                        responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload"))
//                ));


        /*
         this.mockMvc.perform(delete("/crud/{id}", 10)).andExpect(status().isOk())
      .andDo(document("crud-delete-example",
      pathParameters(
        parameterWithName("id").description("The id of the input to delete")
      )));
         */
//        MvcResult result = mockMvc.perform(get("/apis/chats/rooms").headers(headers).contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }

    @Test
    @Transactional
    public void 테스트2() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> crud = new HashMap<>();
        crud.put("title", "Sample Model");
        crud.put("body", "http://www.baeldung.com/");

        this.mockMvc.perform(get("/apis/chats/rooms").content(objectMapper.writeValueAsString(crud))).andExpect(status().isOk())
                .andDo(document("chat"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
