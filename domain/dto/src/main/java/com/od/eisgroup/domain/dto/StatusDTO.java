package com.od.eisgroup.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * DTO for Status Entity.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class StatusDTO implements Serializable {
    private static final long serialVersionUID = 5L;

    private Long id;

    private String statusName;
}