package com.od.eisgroup.service.impl;

import com.od.eisgroup.dao.repository.LetterRepository;
import com.od.eisgroup.domain.dto.LetterDTO;
import com.od.eisgroup.domain.entity.Letter;
import com.od.eisgroup.service.api.LetterService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service which implements {@link LetterService} to work with Letters entities
 * and LetterDTOs.
 *
 * @author Yevhen Berezhnyi
 * @since 1.1
 */
@Slf4j
@Service
@Transactional
public class LetterServiceImpl implements LetterService {

    /**
     * {@link ModelMapper} helps to convert Entity to DTO and vice versa.
     */
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterRepository letterRepository;

    /**
     * Method implemented from {@link LetterService} to get all Letters
     * and convert to RoleDROs
     */
    public List<LetterDTO> findAll() {
        log.debug("Searching for all Letters in database.");
        List<LetterDTO> letters = new ArrayList<>();
        letterRepository.findAll().forEach(letter -> letters.add(convertToDTO(letter)));
        return letters;
    }

    /**
     * Method implemented from {@link LetterService} to edit Letters
     */
    public void editLetter(LetterDTO letterDTO) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method implemented from {@link LetterService} to edit Letters
     */
    public void approveLetter(LetterDTO letterDTO) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method to convert Letter Entity to LetterDTO via {@link ModelMapper}
     */
    private LetterDTO convertToDTO(Letter letter) {
        log.debug("Converting {} to DTO.", letter);
        return modelMapper.map(letter, LetterDTO.class);
    }

}
