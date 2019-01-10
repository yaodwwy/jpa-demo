package jpa.repo;

import jpa.entity.Class;
import jpa.entity.Student;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
public interface StudentRepo extends JpaRepositoryImplementation<Student, UUID> {
    Set<Student> findByAClassIn(Collection<Class> classes);
}
