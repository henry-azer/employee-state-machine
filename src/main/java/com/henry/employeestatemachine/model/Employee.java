package com.henry.employeestatemachine.model;

import com.henry.employeestatemachine.model.base.BaseEntity;
import com.henry.employeestatemachine.util.StringDataConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Henry Azer
 * @since 17/06/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "employee", schema = "public")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_sequence")
    @SequenceGenerator(name = "employee_id_sequence", sequenceName = "employee_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Convert(converter = StringDataConverter.class)
    private List<String> status ;

    @Column(name = "events")
    @Convert(converter = StringDataConverter.class)
    private List<String> events;
}
