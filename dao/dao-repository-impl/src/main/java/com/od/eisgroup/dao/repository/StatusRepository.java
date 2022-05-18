package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Status;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented repository for {@link com.od.eisgroup.domain.entity.Status}-es.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
