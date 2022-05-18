package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.TemplateTypeRepository;
import com.od.eisgroup.domain.dto.TemplateTypeDTO;
import com.od.eisgroup.domain.entity.TemplateType;
import com.od.eisgroup.service.api.TemplateTypeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link com.od.eisgroup.service.api.TemplateTypeService} to work with
 * {@link com.od.eisgroup.domain.entity.TemplateType} entities
 * and {@link com.od.eisgroup.domain.dto.TemplateTypeDTO}.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Slf4j
@Service
@Transactional
public class TemplateTypeServiceImpl implements TemplateTypeService {

    @Autowired
    private TemplateTypeRepository templateTypeRepository;

    /**
     * {@link org.modelmapper.ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.TemplateTypeService} to get all
     * {@link com.od.eisgroup.domain.entity.TemplateType}
     * and convert to {@link com.od.eisgroup.domain.dto.TemplateTypeDTO}.
     */
    @Override
    public List<TemplateTypeDTO> findAll() {
        log.debug("Searching for all TemplateTypes in database.");
        List<TemplateTypeDTO> types = new ArrayList<>();
        templateTypeRepository.findAll().forEach(templateType -> types.add(convertToDTO(templateType)));
        return types;
    }

    /**
     * Method to convert {@link com.od.eisgroup.domain.entity.TemplateType} Entity
     * to {@link com.od.eisgroup.domain.dto.TemplateTypeDTO} via {@link org.modelmapper.ModelMapper}
     */
    private TemplateTypeDTO convertToDTO(TemplateType templateType) {
        log.debug("Converting {} to DTO.", templateType);
        return modelMapper.map(templateType, TemplateTypeDTO.class);
    }
}
