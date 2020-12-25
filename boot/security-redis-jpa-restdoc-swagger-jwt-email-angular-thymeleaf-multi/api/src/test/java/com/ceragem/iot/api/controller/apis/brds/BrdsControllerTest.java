package com.ceragem.iot.api.controller.apis.brds;

import com.ceragem.iot.ApiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static com.ceragem.iot.api.test.DocumentationUtils.getDocumentRequest;
import static com.ceragem.iot.api.test.DocumentationUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("doctest")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureRestDocs
@Slf4j
class BrdsControllerTest {

    private MockMvc mockMvc;

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
                .build();
    }


    @Test
    @Transactional
    public void 게시물가져오기() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> crud = new HashMap<>();
        crud.put("firstName", "Sample Model");
        crud.put("lastName", "dataaaa");
        crud.put("birthDate", "dataaaa");
        crud.put("hobby", "dataaaa");

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        requestParams.add("name", "john");
        requestParams.add("age", "30");

        this.mockMvc.perform(put("/apis/brds")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crud)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andDo(
                        document("brds",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                requestParameters(
                                        parameterWithName("id").description("번호"),
                                        parameterWithName("name").description("이름"),
                                        parameterWithName("age").description("age")
                                ),
//                                pathParameters(
//                                        parameterWithName("id").description("아이디")
//                                )
                                requestFields(
                                        fieldWithPath("firstName").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("lastName").type(JsonFieldType.STRING).description("성"),
                                        fieldWithPath("birthDate").type(JsonFieldType.STRING).description("생년월일"),
                                        fieldWithPath("hobby").type(JsonFieldType.STRING).description("취미")
                                )
                                ,responseFields(
                                        fieldWithPath("[].no").type(JsonFieldType.NUMBER).description("번호"),
                                        fieldWithPath("[].category_no").type(JsonFieldType.NUMBER).description("카테고리"),
                                        fieldWithPath("[].style").type(JsonFieldType.STRING).description("style"),
                                        fieldWithPath("[].regist_date").type(JsonFieldType.STRING).description("날짜(YYYY-MM)"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("타이틀"),
                                        fieldWithPath("[].image").optional().type(JsonFieldType.NUMBER).description("image"),
                                        fieldWithPath("[].stream").optional().type(JsonFieldType.STRING).description("stream"),
                                        fieldWithPath("[].stream_duration").optional().type(JsonFieldType.STRING).description("stream_duration"),
                                        fieldWithPath("[].link_url").type(JsonFieldType.STRING).description("link_url"),
                                        fieldWithPath("[].description").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("[].view_count").type(JsonFieldType.NUMBER).description("view_count"),
                                        fieldWithPath("[].like_count").type(JsonFieldType.NUMBER).description("like_count"),
                                        fieldWithPath("[].play_count").type(JsonFieldType.NUMBER).description("play_count"),
                                        fieldWithPath("[].reply_count").type(JsonFieldType.NUMBER).description("reply_count"),
                                        fieldWithPath("[].is_hidden").type(JsonFieldType.NUMBER).description("is_hidden"),
                                        fieldWithPath("[].is_reply").type(JsonFieldType.NUMBER).description("is_reply"),
                                        fieldWithPath("[].extra").type(JsonFieldType.STRING).description("extra")
//                                        fieldWithPath("data.person.firstName").type(JsonFieldType.STRING).description("이름"),
//                                        fieldWithPath("data.person.lastName").type(JsonFieldType.STRING).description("성"),
//                                        fieldWithPath("data.person.age").type(JsonFieldType.NUMBER).description("나이"),
//                                        fieldWithPath("data.person.birthDate").type(JsonFieldType.STRING).description("생년월일"),
//                                        fieldWithPath("data.person.gender").type(JsonFieldType.STRING).description("성별"),
                                )
                        )
                )
        ;
    }
}
