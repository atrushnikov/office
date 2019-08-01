package ru.office;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.office.model.entity.DepartmentEntity;
import ru.office.model.entity.OfficeCategoryEntity;
import ru.office.model.entity.OfficeEntity;
import ru.office.model.entity.OfficePropertyTypeEntity;
import ru.office.service.DepartmentService;
import ru.office.service.OfficeCategoryService;
import ru.office.service.OfficePropertyTypeService;
import ru.office.service.OfficeService;

import java.util.*;

@Transactional()
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
    //@Commit
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
                "Елисеевский переулок", "Живописная", "Заводской", "Звёздный бульвар",
                "Икшинская", "Илимская", "Иловайская", "Ильинка", "Ильменский", "Индустриальный", "Кабельная 2-я", "Кадашёвская набережная", "Кадашёвский тупик", "Казакова",
                "Калибровская", "Калмыков", "Калужская", "Камергерский", "Канатчиковский проезд", "Кандинского", "Капельский переулок", "Капотнинский 1-й проезд",
                "Коккинаки", "Коленчатый переулок", "Колодезный переулок", "Коломенская");

        Random random = new Random();

        List<OfficeEntity> officeEntities = new ArrayList<>();

        for (String street : streats) {

            Set<String> apartments = new HashSet<>();
            while (apartments.size() < 200) {
                apartments.add(String.valueOf(random.nextInt(250)));
            }

            for (String building : apartments) {
                StringBuilder address = new StringBuilder(street).append(" д. ").append(building);
                OfficeEntity officeEntity = new OfficeEntity();
                officeEntity.setCity("Москва");
                officeEntity.setAddress(address.toString());
                officeEntity.setOfficeCategory(officeCategoryEntities.get(random.nextInt(4)));
                officeEntity.setOfficePropertyType(officePropertyTypeEntities.get(random.nextInt(2)));
                officeEntity.setValue(random.nextInt(999_999_999) + 10_000_000_000L);

                Set<DepartmentEntity> departments = new HashSet<>();
                for (int i = 0; i < random.nextInt(5); i++) {
                    departments.add(departmentEntities.get(i));
                }

                officeEntity.setDepartments(departments);

                officeEntities.add(officeEntity);
                officeService.save(officeEntity);
            }
        }

        System.out.println("");

    }


}
