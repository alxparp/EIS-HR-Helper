package com.od.eisgroup.domain.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for Template Entity.
 * *
 * * @author Alice Klochkova
 * * @since 1.1
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString
public class TemplateDTO implements Serializable {
    private static final long serialVersionUID = 4L;

    private Long id;

    private TemplateTypeDTO templateType;

    @Size(max = 255, message = "255 symbols are allowed to be entered to the field")
    @Pattern(regexp = "^[a-zA-Z0-9.,'!#$%&’*+/=?^_` {|}~-]+$",
            message = "Salutation: Your input is not valid")
    private String salutation;

    @NotNull(message = "This field is required.")
    @Size(max = 255, message = "255 symbols are allowed to be entered to the field")
    @Pattern(regexp = "^[a-zA-Z0-9.,'!#$%&’*+/=?^_` {|}~-]+$",
            message = "Summary: Your input is not valid")
    private String summary;

    @NotNull(message = "This field is required.")
    @Size(max = 255, message = "255 symbols are allowed to be entered to the field")
    @Pattern(regexp = "^[a-zA-Z0-9.,'!#$%&’*+/=?^_` {|}~-]+$",
            message = "First Words: Your input is not valid")
    private String firstWords;

    @Size(max = 255, message = "255 symbols are allowed to be entered to the field")
    @Pattern(regexp = "^[a-zA-Z0-9.,'!#$%&’*+/=?^_` {|}~-]+$",
            message = "Main Words: Your input is not valid")
    private String mainWords;

    @NotNull(message = "This field is required.")
    @Size(max = 255, message = "255 symbols are allowed to be entered to the field")
    @Pattern(regexp = "^[a-zA-Z0-9.,'!#$%&’*+/=?^_` {|}~-]+$",
            message = "Signature: Your input is not valid")
    private String signature;
}
