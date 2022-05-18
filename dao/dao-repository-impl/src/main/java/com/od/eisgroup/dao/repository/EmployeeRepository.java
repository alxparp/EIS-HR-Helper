package com.od.eisgroup.dao.repository;

import com.od.eisgroup.domain.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * This class is represented implementation of repository pattern for {@link com.od.eisgroup.domain.entity.Employee}.
 * </p>
 *
 * @author <a href='mailto:aoleynik@eisgroup.com'>Alexander Oleynik</a>
 * @since 1.2
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
