package ru.office.model;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "officeCategory")
@Table(name = "office_category")
public class OfficeCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

}
