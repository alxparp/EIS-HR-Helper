package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.TemplateTypeRepository;
import com.od.eisgroup.domain.dto.TemplateTypeDTO;
import com.od.eisgroup.domain.entity.TemplateType;
import com.od.eisgroup.service.api.TemplateTypeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TemplateTypeServiceImplTest {

    @InjectMocks
    private TemplateTypeService templateTypeService = new TemplateTypeServiceImpl();

    @Mock
    private TemplateTypeRepository templateTypeRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void findAll() {
        TemplateType templateType = mock(TemplateType.class);
        when(templateType.getTemplateName()).thenReturn("Birthday");
        when(templateTypeRepository.findAll()).thenReturn(Collections.singletonList(templateType));
        List<TemplateTypeDTO> templateTypeDTOS = templateTypeService.findAll();
        TemplateTypeDTO templateTypeDTO = templateTypeDTOS.get(0);

        Assert.assertEquals(templateTypeDTO.getTemplateName(), templateType.getTemplateName());
    }
}