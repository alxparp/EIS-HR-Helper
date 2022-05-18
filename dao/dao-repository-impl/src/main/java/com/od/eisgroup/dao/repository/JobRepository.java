package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented repository for {@link com.od.eisgroup.domain.entity.Job}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
}
