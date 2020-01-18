package ru.office.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.office.model.dto.OfficeDto;
import ru.office.model.entity.DepartmentEntity;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.model.entity.OfficeEntity;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.service.OfficeService;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(MockitoJUnitRunner.class)
public class OfficeControllerTest {

    @Mock
    private OfficeService service;

    @InjectMocks
    private OfficeController officeController;

    private MockMvc mockMvc;
    private JacksonTester<OfficeDto> jsonOffice;

    @Before
    public void setUp() throws Exception {
        officeController.setModelMapper(new ModelMapper());
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(officeController).build();

    }


    @Test
    public void getOneOkTest() throws Exception {
        String officeEntitiesJson = "{\"id\":\"0483a853-f78e-4203-b930-179d9cfe30a4\",\"city\":\"Москва\",\"address\":\"Вересаева д. 36\",\"officeCategory\":{\"id\":1,\"name\":\"A\"},\"officePropertyType\":{\"id\":1,\"name\":\"private\"},\"value\":5000000.0,\"departments\":[{\"id\":\"1392bd54-5905-11e9-b572-0242ac130002\",\"name\":\"Пенсионный фонд\"}]}";

        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setId(UUID.fromString("0483a853-f78e-4203-b930-179d9cfe30a4"));
        officeEntity.setAddress("Вересаева д. 36");
        officeEntity.setCity("Москва");
        officeEntity.setDepartments(Set.of(new DepartmentEntity(UUID.fromString("1392bd54-5905-11e9-b572-0242ac130002"), "Пенсионный фонд")));
        officeEntity.setOfficeCategory(new OfficeCategoryEntity(1L, "A"));
        officeEntity.setOfficePropertyType(new OfficePropertyTypeEntity(1L, "private"));
        officeEntity.setValue(5000000D);

        given(service.findById(UUID.fromString("0483a853-f78e-4203-b930-179d9cfe30a4"))).willReturn(officeEntity);

        MockHttpServletResponse response = mockMvc.perform(get("/api/rest/offices/0483a853-f78e-4203-b930-179d9cfe30a4").accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(officeEntitiesJson);
    }

}