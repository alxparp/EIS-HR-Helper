package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented repository of {@link com.od.eisgroup.domain.entity.User}-s.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * <p>
     * Find {@link com.od.eisgroup.domain.entity.User} by the given {@code email} address.
     * </p>
     *
     * @param email email address
     * @return {@link com.od.eisgroup.domain.entity.User} if exists or {@code null}.
     */
    User findByEmail(String email);
}
