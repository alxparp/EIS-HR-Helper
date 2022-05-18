package com.od.eisgroup.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity that keep data about letters
 *
 * @author Parpalak Oleksandr
 * @since 1.1
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
@Entity
@Table(name = "Letters")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "statusId")
    private Status status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "templateTypeId")
    private TemplateType templateType;

    private String content;

    private String email;

    private Date dueDate;
}
