package com.od.eisgroup.service.api;

import com.od.eisgroup.domain.dto.LetterDTO;

import java.util.List;

/**
 * Interface to be implemented by Services that wish
 * to work with Role entity and LetterDTO.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */

public interface LetterService {
    /**
     * Method to be implemented to get all Letters
     * and convert to LetterDTOs
     */
    List<LetterDTO> findAll();

    /**
     * Method to be implemented to edit Letter after clicking "Edit" button
     */
    void editLetter(LetterDTO letterDTO);

    /**
     * Method to be implemented to edit Letter after clicking "Approve" button
     */
    void approveLetter(LetterDTO letterDTO);
}
