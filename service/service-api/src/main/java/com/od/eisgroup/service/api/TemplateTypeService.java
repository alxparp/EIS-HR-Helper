package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.TemplateTypeDTO;

import java.util.List;

/**
 * Interface to be implemented by Services that wish
 * to work with {@link com.od.eisgroup.domain.entity.TemplateType} entity and {@link com.od.eisgroup.domain.dto.TemplateTypeDTO}.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
public interface TemplateTypeService {
    /**
     * Method to be implemented to get all {@link com.od.eisgroup.domain.entity.TemplateType}
     * and convert to {@link com.od.eisgroup.domain.dto.TemplateTypeDTO}
     */
    List<TemplateTypeDTO> findAll();
}
