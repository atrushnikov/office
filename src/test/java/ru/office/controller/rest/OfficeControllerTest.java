package ru.office.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.office.model.entity.OfficeEntity;
import ru.office.repository.OfficeRepo;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OfficeController.class)
public class OfficeControllerTest {

    @MockBean
    private OfficeRepo repo;

    @Autowired
    private MockMvc mockMvc;

    @Configuration
    static class TestConfig {
        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getAll() {
    }

    @Test
    //@Ignore
    public void getOne() throws Exception {

        String officeEntitiesJson = "{\n" +
                "  \"id\": \"0483a853-f78e-4203-b930-179d9cfe30a4\",\n" +
                "  \"city\": \"Москва\",\n" +
                "  \"address\": \"Вересаева д. 36\",\n" +
                "  \"officeCategory\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"A\"\n" +
                "  },\n" +
                "  \"officePropertyType\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"private\"\n" +
                "  },\n" +
                "  \"value\": 1.0221745155E10,\n" +
                "  \"departments\": [\n" +
                "    {\n" +
                "      \"id\": \"1392bd54-5905-11e9-b572-0242ac130002\",\n" +
                "      \"name\": \"Миграционная полиция\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"f08b484b-8118-11e9-b7a1-0242ac120002\",\n" +
                "      \"name\": \"Пенсионный фонд\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"8a0be692-5934-11e9-b572-0242ac130002\",\n" +
                "      \"name\": \"Паспортный стол\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"60f76a5f-5934-11e9-b572-0242ac130002\",\n" +
                "      \"name\": \"ЖКХ\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        OfficeEntity officeEntity = objectMapper.readValue(officeEntitiesJson, OfficeEntity.class);

        given(repo.findFirstById(any())).willReturn(officeEntity);

        mockMvc.perform(get("/00175f63-8210-429a-ac18-fb189a7d1d38").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteOfficeCategory() {
    }
}