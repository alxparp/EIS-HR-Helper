package com.od.eisgroup.domain.dto;

import com.od.eisgroup.domain.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BirthdayDto {

    private Employee employee;

    private String email;

    private Date dueDate;
}
