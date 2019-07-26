package ru.office;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.office.model.entity.DepartmentEntity;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.service.DepartmentService;
import ru.office.service.OfficeCategoryService;
import ru.office.service.OfficePropertyTypeService;
import ru.office.service.OfficeService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class FillBDTest {

    @Autowired
    private OfficeService officeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OfficeCategoryService officeCategoryService;
    @Autowired
    private OfficePropertyTypeService officePropertyTypeService;

    //@Ignore
    @Test
    public void fillDBTest() {
        List<OfficeCategoryEntity> officeCategoryEntities = officeCategoryService.findAll();
        List<OfficePropertyTypeEntity> officePropertyTypeEntities = officePropertyTypeService.findAll();
        List<DepartmentEntity> departmentEntities = departmentService.findAll();

        List<String> streats = Arrays.asList("Абельмановская Застава", "Авиаконструктора Сухого", "Автозаводский 1-й проезд",
                "Айвазовского", "Боевская 2-я", "Болотниковская", "Большая Декабрьская", "Большая Набережная",
                "Бригадирский переулок", "Бродников переулок", "Брянский 2-й переулок",
                "Бутлерова", "Валаамская", "Василисы Кожиной", "Васильцовский Стан", "Введенского,",
                "Вертолётчиков", "Вересаева", "Верхняя аллея", "Верхняя Радищевская", "Водников",
                "Вражский 2-й переулок", "Всеволожский переулок", "Второй проспект Новогиреево",
                "Вяземская", "Вятская", "Габричевского,", "Газгольдерная", "Гарибальди", "Гвоздева",
                "Даниловская набережная", "Джавахарлала Неру", "Доктора Гааза",
                "Елисеевский переулок", "Живописная", "Заводской", "Звёздный бульвар");

        Random random = new Random();



        System.out.println("");

    }


}
