package jpa;

import jpa.entity.Class;
import jpa.entity.Student;
import jpa.factory.PredicateFactory;
import jpa.repo.StudentRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author yaodw
 */
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepoTest {
    @Autowired StudentRepo studentRepo;
    @Test
    public void testStudentRepo() {
        log.debug("查出所有学生信息包含学校、班级、老师、学生信息");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        studentRepo.findAll(PageRequest.of(1, 80)).forEach(student -> {
            log.debug(student.getSchool().getName() + " : " + student.getAClass().getName() + " : " + student.getName());
        });
        log.debug("---------------" + (System.currentTimeMillis() - start) + " 毫秒" + "----------------");
    }

    @Test
    public void testStudentRepoBySpecFactory() {
        log.debug("查出所有学生信息包含学校、班级、老师、学生信息, 工厂优化版");
        log.debug("-------------------------------");

        Specification<Student> specification = (Specification<Student>) (root, query, criteriaBuilder) -> {
            return PredicateFactory.getStudentPredicate(root, null, query, criteriaBuilder, null);
        };

        long start = System.currentTimeMillis();
        studentRepo.findAll(specification,PageRequest.of(1, 80)).forEach(System.out::println);
        log.warn(System.currentTimeMillis() - start + "毫秒");

        log.debug("-------------------------------");
    }
}
