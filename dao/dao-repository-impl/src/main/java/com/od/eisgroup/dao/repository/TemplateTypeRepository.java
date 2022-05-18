package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.TemplateType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p>
 * This class is represented repository of {@link com.od.eisgroup.domain.entity.TemplateType}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
public interface TemplateTypeRepository extends PagingAndSortingRepository<TemplateType, Long> {
}
