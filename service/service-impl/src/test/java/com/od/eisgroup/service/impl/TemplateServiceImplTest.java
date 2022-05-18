package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.TemplateRepository;
import com.od.eisgroup.domain.dto.TemplateDTO;
import com.od.eisgroup.domain.entity.Template;
import com.od.eisgroup.service.api.TemplateService;
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
public class TemplateServiceImplTest {

    @InjectMocks
    private TemplateService templateService = new TemplateServiceImpl();

    @Mock
    private TemplateRepository templateRepository;
    @Spy
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void findAll() {
        Template template = mock(Template.class);
        when(template.getSummary()).thenReturn("Summary");
        when(template.getFirstWords()).thenReturn("FirstWords");
        when(template.getMainWords()).thenReturn("MainWords");
        when(template.getSignature()).thenReturn("Signature");
        when(templateRepository.findAll()).thenReturn(Collections.singletonList(template));
        List<TemplateDTO> templateDTOS = templateService.findAll();
        TemplateDTO templateDTO = templateDTOS.get(0);

        Assert.assertEquals(templateDTO.getSummary(), template.getSummary());
        Assert.assertEquals(templateDTO.getFirstWords(), template.getFirstWords());
        Assert.assertEquals(templateDTO.getMainWords(), template.getMainWords());
        Assert.assertEquals(templateDTO.getSignature(), template.getSignature());
    }
}