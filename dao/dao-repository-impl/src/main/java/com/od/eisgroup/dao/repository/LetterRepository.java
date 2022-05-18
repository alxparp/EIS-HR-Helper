package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Letter;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represent repository for {@link com.od.eisgroup.domain.entity.Letter}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface LetterRepository extends PagingAndSortingRepository<Letter, Long> {
}
