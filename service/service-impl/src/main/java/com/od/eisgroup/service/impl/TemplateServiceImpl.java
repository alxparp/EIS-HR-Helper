package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.TemplateRepository;
import com.od.eisgroup.domain.dto.TemplateDTO;
import com.od.eisgroup.domain.entity.Template;
import com.od.eisgroup.service.api.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link com.od.eisgroup.service.api.TemplateService} to work with
 * {@link com.od.eisgroup.domain.entity.Template} entities
 * and {@link com.od.eisgroup.domain.dto.TemplateDTO}.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Slf4j
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    /**
     * {@link org.modelmapper.ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.TemplateService} to get all
     * {@link com.od.eisgroup.domain.entity.Template}
     * and convert to {@link com.od.eisgroup.domain.dto.TemplateDTO}.
     */
    @Override
    public List<TemplateDTO> findAll() {
        log.debug("Searching for all Templates in database.");
        List<TemplateDTO> templates = new ArrayList<>();
        templateRepository.findAll().forEach(template -> templates.add(convertToDTO(template)));
        return templates;
    }

    /**
     * Method to convert {@link com.od.eisgroup.domain.entity.Template} Entity
     * to {@link com.od.eisgroup.domain.dto.TemplateDTO} via {@link org.modelmapper.ModelMapper}
     */
    private TemplateDTO convertToDTO(Template template) {
        log.debug("Converting {} to DTO.", template.getId());
        return modelMapper.map(template, TemplateDTO.class);
    }

    /**
     * Method to convert {@link com.od.eisgroup.domain.dto.TemplateDTO}
     * to {@link com.od.eisgroup.domain.entity.Template} via {@link org.modelmapper.ModelMapper}
     */
    private Template convertToEntity(TemplateDTO templateDTO) {
        log.debug("Converting {} to Entity.", templateDTO.getId());
        return modelMapper.map(templateDTO, Template.class);
    }

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.TemplateService} to remove
     * {@link com.od.eisgroup.domain.entity.Template}
     * converted {@link com.od.eisgroup.domain.dto.TemplateDTO}.
     */
    @Override
    public void remove(TemplateDTO templateDTO) {
        log.debug("Removing {} from database.", templateDTO);
        templateRepository.deleteById(templateDTO.getId());
    }

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.TemplateService} to add
     * {@link com.od.eisgroup.domain.entity.Template}
     * converted {@link com.od.eisgroup.domain.dto.TemplateDTO}.
     */
    @Override
    public void addTemplate(TemplateDTO templateDTO) {
        log.debug("Adding new {} to database.", templateDTO);
        templateRepository.save(convertToEntity(templateDTO));
    }

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.TemplateService} to update
     * {@link com.od.eisgroup.domain.entity.Template}
     * converted {@link com.od.eisgroup.domain.dto.TemplateDTO}.
     */
    @Override
    public void updateTemplate(TemplateDTO templateDTO) {
        log.debug("Updating {} in database.", templateDTO);
        templateRepository.save(convertToEntity(templateDTO));
    }
}
