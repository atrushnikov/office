SELECT
       BIN_TO_UUID(`o`.`id`, true) AS id,
       `o`.city, o.address,
       `oc`.`name` AS office_category,
       `o`.`value`,
       `opt`.`name` AS 'office property',
       `d`.`name` AS 'department'
FROM
       office o
              JOIN office_category oc
                   ON o.`office_category_id` = oc.id
              JOIN office_property_type opt
                   ON o.`office_property_type_id` = opt.id
              INNER JOIN office_department od
                         ON o.id = od.office_id
              INNER JOIN department d
                         ON od.department_id = d.id;
