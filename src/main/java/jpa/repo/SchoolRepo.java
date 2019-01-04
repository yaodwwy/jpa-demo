package jpa.repo;

import jpa.entity.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author yaodw
 */
public interface SchoolRepo extends JpaRepositoryImplementation<School, UUID> {
}
