package com.sa.healntrack.employee_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@WebMvcTest
public class CommonMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    @TestConfiguration
    static class TestConfig {
        @Bean
        public MockMvcBuilderCustomizer defaultMockMvcBuilderCustomizer() {
            return builder -> {
                builder.defaultRequest(delete("")

                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON));
                builder.alwaysDo(print());
            };
        }
    }
}
