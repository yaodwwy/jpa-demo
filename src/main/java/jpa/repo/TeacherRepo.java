package jpa.repo;

import jpa.entity.Class;
import jpa.entity.Student;
import jpa.entity.Teacher;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
public interface TeacherRepo extends JpaRepositoryImplementation<Teacher, UUID> {
    Set<Teacher> findByClassesIn(Collection<Class> classes);
}
