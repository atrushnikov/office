package ru.office.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.office.model.entity.OfficeEntity;
import ru.office.repository.OfficeRepo;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {

    @Mock
    private OfficeRepo repo;
    @Mock
    private OfficeCategoryService officeCategoryService;
    @Mock
    private OfficePropertyTypeService officePropertyTypeService;
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    OfficeServiceImpl officeService;


    private List<OfficeEntity> officeEntities;


    @Before
    public void setUp()  throws IOException{
        String officeEntitiesJson = "[\n" +
                "  {\n" +
                "    \"id\": \"00114a67-ab43-4e7c-af78-37e3a13f28bb\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"address\": \"Брянский 2-й переулок д. 205\",\n" +
                "    \"officeCategory\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"B\"\n" +
                "    },\n" +
                "    \"officePropertyType\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"rented\"\n" +
                "    },\n" +
                "    \"value\": 56000000,\n" +
                "    \"departments\": [\n" +
                "      {\n" +
                "        \"id\": \"1392bd54-5905-11e9-b572-0242ac130002\",\n" +
                "        \"name\": \"Миграционная полиция\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"f08b484b-8118-11e9-b7a1-0242ac120002\",\n" +
                "        \"name\": \"Пенсионный фонд\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"8a0be692-5934-11e9-b572-0242ac130002\",\n" +
                "        \"name\": \"Паспортный стол\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"60f76a5f-5934-11e9-b572-0242ac130002\",\n" +
                "        \"name\": \"ЖКХ\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"00175f63-8210-429a-ac18-fb189a7d1d38\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"address\": \"Илимская д. 22\",\n" +
                "    \"officeCategory\": {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"D\"\n" +
                "    },\n" +
                "    \"officePropertyType\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"rented\"\n" +
                "    },\n" +
                "    \"value\": 29000000,\n" +
                "    \"departments\": [\n" +
                "      {\n" +
                "        \"id\": \"1392bd54-5905-11e9-b572-0242ac130002\",\n" +
                "        \"name\": \"Миграционная полиция\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"60f76a5f-5934-11e9-b572-0242ac130002\",\n" +
                "        \"name\": \"ЖКХ\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]";

        ObjectMapper objectMapper = new ObjectMapper();
        officeEntities = objectMapper.readValue(officeEntitiesJson, new TypeReference<List<OfficeEntity>>() {});
    }


    @Test
    public void percentageReductionTest() {
        officeService.percentageReduction(officeEntities, 1);
        assertEquals( 55440000, officeEntities.get(0).getValue().longValue());
        assertEquals( 28710000, officeEntities.get(1).getValue().longValue());
    }
}