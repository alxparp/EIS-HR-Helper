package com.od.eisgroup.ui.beans;

import com.od.eisgroup.domain.dto.LetterDTO;
import com.od.eisgroup.domain.dto.StatusDTO;
import com.od.eisgroup.service.api.LetterService;
import com.od.eisgroup.service.api.StatusService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller which dispatches UI requests to application to work with Letters.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */
@Slf4j
@Getter
@Setter
@Controller
public class LetterController {

    /**
     * Field which is setted when user press
     * Edit or Approve button in DataTable.
     */
    private LetterDTO selectedLetter;

    /**
     * Field that is initialized after Bean creation with method
     *
     * @see #init
     * using
     * @see #letterService
     */
    private List<LetterDTO> letterDTOS;

    /**
     * Field that is initialized after Bean creation with method
     *
     * @see #init
     * using
     * @see #statusService
     */
    private List<StatusDTO> statusDTOS;

    /**
     * {@link LetterService} autowired Bean
     */
    @Autowired
    private LetterService letterService;

    /**
     * {@link StatusService} autowired Bean
     */
    @Autowired
    private StatusService statusService;

    /**
     * Method to get all Letters (DTOs).
     */
    @PostConstruct
    public void init() {
        letterDTOS = letterService.findAll();
        statusDTOS = statusService.findAll();
    }

    /**
     * Method to get List of Letters filtered by type.
     */
    public List<LetterDTO> filterLetters(String statusName) {
        return letterDTOS.stream()
                .filter(letter -> letter.getStatusName().equals(statusName))
                .collect(Collectors.toList());
    }

    /**
     * Method to call after clicking Edit button.
     */
    public void editButtonAction(ActionEvent actionEvent) {
        log.debug("Calling method editLetter: {}", actionEvent);
        letterService.editLetter(selectedLetter);
    }

    /**
     * Method to call after clicking Approve button.
     */
    public void approveButtonAction(ActionEvent actionEvent) {
        log.debug("Calling method approveLetter: {}", actionEvent);
        letterService.approveLetter(selectedLetter);
    }
}
