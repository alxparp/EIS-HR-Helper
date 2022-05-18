package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Template;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented repository of {@link com.od.eisgroup.domain.entity.Template}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface TemplateRepository extends PagingAndSortingRepository<Template, Long> {
}
