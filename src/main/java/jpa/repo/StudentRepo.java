package jpa.repo;

import jpa.entity.Student;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author yaodw
 */
public interface StudentRepo extends JpaRepositoryImplementation<Student, UUID> {
}
