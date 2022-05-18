package com.od.eisgroup.domain.dto;

import com.od.eisgroup.domain.entity.UserStatus;
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
@EqualsAndHashCode(of = "email")
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 3L;

    private Long id;
    @Size(max = 30)
    private String firstName;
    @Size(max = 30)
    private String lastName;
    @NotNull
    @Size(max = 50)
    private String email;
    @NotNull
    private RoleDTO role;
    @NotNull
    private UserStatus status;
}
