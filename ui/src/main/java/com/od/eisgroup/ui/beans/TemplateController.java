package com.od.eisgroup.ui.beans;

import com.od.eisgroup.domain.dto.TemplateDTO;
import com.od.eisgroup.domain.dto.TemplateTypeDTO;
import com.od.eisgroup.service.api.TemplateService;
import com.od.eisgroup.service.api.TemplateTypeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller which dispatches UI requests to application to work with Templates.
 *
 * @author Alice Klochkova
 * @since 1.1
 */
@Getter
@Setter
@Controller
public class TemplateController {

    /**
     * Field which is setted when user press
     * Remove or Edit button in DataTable.
     */
    private TemplateDTO selected;

    /**
     * Field which is setted when user press
     * Add button in DataTable.
     */
    private TemplateTypeDTO selectedTemplateType;

    /**
     * Field that is initialized after Bean creation with method
     *
     * @see #init
     * using
     * @see #templateService
     */
    private List<TemplateDTO> templateDTOS;

    /**
     * Field that is initialized after Bean creation with method
     *
     * @see #init
     * using
     * @see #templateTypeService
     */
    private List<TemplateTypeDTO> templateTypeDTOS;

    /**
     * {@link com.od.eisgroup.service.api.TemplateService} autowired Bean
     */
    @Autowired
    private TemplateService templateService;

    /**
     * {@link com.od.eisgroup.service.api.TemplateTypeService} autowired Bean
     */
    @Autowired
    private TemplateTypeService templateTypeService;

    /**
     * Method to get all Templates (DTOs).
     */
    @PostConstruct
    public void init() {
        templateTypeDTOS = templateTypeService.findAll();
        templateDTOS = templateService.findAll();
    }

    /**
     * Method to get List of Templates filtered by type.
     */
    public List<TemplateDTO> filterTemplates(String templateTypeName) {
        return templateDTOS.stream()
                .filter(template -> template.getTemplateType().getTemplateName().equals(templateTypeName))
                .collect(Collectors.toList());
    }

    /**
     * Method to remove Template from DB.
     */
    public void remove(TemplateDTO templateDTO) {
        templateService.remove(templateDTO);
    }

    /**
     * Method to add Template to DB.
     */
    public void addTemplate(TemplateDTO templateDTO) {
        templateService.addTemplate(templateDTO);
    }

    /**
     * Method to update Template in DB.
     */
    public void updateTemplate(TemplateDTO templateDTO) {
        templateService.updateTemplate(templateDTO);
    }
}
