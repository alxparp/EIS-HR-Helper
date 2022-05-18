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
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(max = 30)
    @NotNull
    private String name;
    @NotNull
    private Collection<PrivilegeDTO> privilegeDTOS;
}