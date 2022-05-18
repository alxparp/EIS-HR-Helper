package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.StatusDTO;

import java.util.List;

/**
 * Interface to be implemented by Services that wish
 * to work with {@link com.od.eisgroup.domain.entity.Status} entity and {@link com.od.eisgroup.domain.dto.StatusDTO}.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */
public interface StatusService {
    /**
     * Method to be implemented to get all {@link com.od.eisgroup.domain.entity.Status}
     * and convert to {@link com.od.eisgroup.domain.dto.StatusDTO}
     */

    List<StatusDTO> findAll();
}
