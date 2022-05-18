package com.od.eisgroup.service.impl.job.pojos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * The class contains basic information about Jobs. This information is significant for running jobs.
 *
 * @author Vadim Martsun
 * @version since 1.1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class JobIdentity {

    @NotNull
    Class jobClass;

    @NotNull
    String group;
}
