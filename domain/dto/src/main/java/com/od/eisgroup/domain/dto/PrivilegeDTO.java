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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class PrivilegeDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;
    @Size(max = 255)
    @NotNull
    private String name;
}
