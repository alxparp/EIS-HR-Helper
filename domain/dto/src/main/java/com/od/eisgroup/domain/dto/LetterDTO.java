package com.od.eisgroup.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class LetterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(max = 30)
    private String templateName;
    @Size(max = 30)
    private String employeeFirstName;
    @Size(max = 30)
    private String employeeLastName;
    @NotNull
    @Size(max = 50)
    private String employeeEmail;
    @Size(max = 255)
    private String statusName;
    @Size(max = 255)
    private String userLocationCountryName;
    @Size(max = 255)
    private String userLocationCity;
    @NotNull
    private Date dueDate; //    Not know for now exact name of field
}