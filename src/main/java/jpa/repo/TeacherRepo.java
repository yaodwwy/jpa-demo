package jpa.repo;

import jpa.entity.Teacher;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.UUID;

/**
 * @author yaodw
 */
public interface TeacherRepo extends JpaRepositoryImplementation<Teacher, UUID> {
}
