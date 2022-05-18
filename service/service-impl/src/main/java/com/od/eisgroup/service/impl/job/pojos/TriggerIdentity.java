package com.od.eisgroup.service.impl.job.pojos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * The class contains basic information about Triggers
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TriggerIdentity {

    @NotNull
    String triggerName;

    @NotNull
    String triggerGroup;
}
