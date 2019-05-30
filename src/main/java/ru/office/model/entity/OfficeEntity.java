package ru.office.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "office")
@Table(name = "office")
public class OfficeEntity {

    public OfficeEntity() {
    }

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String city;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_category_id")
    private OfficeCategoryEntity officeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_property_type_id")
    private OfficePropertyTypeEntity officePropertyType;

    @ManyToMany
    @JoinTable(name = "office_department",
            joinColumns = { @JoinColumn(name = "office_id") },
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private Set<DepartmentEntity> departments = new HashSet();

    private Long value;

}
