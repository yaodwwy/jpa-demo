package jpa.repo;

import jpa.entity.Class;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.UUID;

/**
 * @author yaodw
 */
public interface ClassRepo extends JpaRepositoryImplementation<Class, UUID> {
}
