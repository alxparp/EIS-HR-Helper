package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.TemplateDTO;

import java.util.List;

/**
 * Interface to be implemented by Services that wish
 * to work with Template entity and TemplateDTO.
 *
 * @author Alice Klochkova
 * @since 1.1
 */

public interface TemplateService {
    /**
     * Method to be implemented to get all Template
     * and convert to TemplateDTO
     */
    List<TemplateDTO> findAll();

    /**
     * Method to be implemented to remove selected Template via TemplateDTO
     */
    void remove(TemplateDTO templateDTO);

    /**
     * Method to be implemented to add selected Template via TemplateDTO
     */
    void addTemplate(TemplateDTO templateDTO);

    /**
     * Method to be implemented to update selected Template via TemplateDTO
     */
    void updateTemplate(TemplateDTO templateDTO);
}
