package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.StatusRepository;
import com.od.eisgroup.domain.dto.StatusDTO;
import com.od.eisgroup.domain.entity.Status;
import com.od.eisgroup.service.api.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link StatusService} to work with
 * {@link com.od.eisgroup.domain.entity.Status} entities
 * and {@link com.od.eisgroup.domain.dto.StatusDTO}.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */
@Slf4j
@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    /**
     * {@link org.modelmapper.ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method implemented from {@link com.od.eisgroup.service.api.StatusService} to get all
     * {@link com.od.eisgroup.domain.entity.Status}
     * and convert to {@link com.od.eisgroup.domain.dto.StatusDTO}.
     */
    @Override
    public List<StatusDTO> findAll() {
        log.debug("Searching for all TemplateTypes in database.");
        List<StatusDTO> statuses = new ArrayList<>();
        statusRepository.findAll().forEach(status -> statuses.add(convertToDTO(status)));
        return statuses;
    }

    /**
     * Method to convert {@link com.od.eisgroup.domain.entity.Status} Entity
     * to {@link com.od.eisgroup.domain.dto.StatusDTO} via {@link org.modelmapper.ModelMapper}
     */
    private StatusDTO convertToDTO(Status status) {
        log.debug("Converting {} to DTO.", status);
        return modelMapper.map(status, StatusDTO.class);
    }
}