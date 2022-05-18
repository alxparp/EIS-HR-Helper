package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented repository for {@link com.od.eisgroup.domain.entity.Role}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    /**
     * <p>
     * Searching {@link com.od.eisgroup.domain.entity.Role} by given {@code name}.
     * </p>
     *
     * @param name role name search criteria
     * @return A {@link com.od.eisgroup.domain.entity.Role} if exists or {@code null}.
     */
    Role findByName(String name);
}
