package com.base.customer.integration;

import com.base.customer.CustomerApplication;
import com.base.customer.builder.CustomerDataBuilder;
import com.base.customer.controller.endpoint.CustomerEndpoint;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CustomerApplication.class)
@AutoConfigureMockMvc
@Ignore
public class CustomerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        when(mongoTemplate.save(any(), any())).thenReturn(CustomerDataBuilder.createCustomerDocument());
        when(mongoTemplate.find(any(), any(), any())).thenReturn(Collections.singletonList(CustomerDataBuilder.createCustomerDocument()));
        when(mongoTemplate.count(any(), any(), any())).thenReturn(0L);
    }

    //@Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
        throws Exception {


        mvc.perform(get(CustomerEndpoint.CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", CustomerDataBuilder.NAME)
                .param("surname", CustomerDataBuilder.SURNAME))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.name", is(CustomerDataBuilder.NAME)));
    }
}
