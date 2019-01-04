package jpa;

import jpa.entity.Student;
import jpa.entity.Teacher;
import jpa.factory.PredicateFactory;
import jpa.repo.TeacherRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.JoinType;

/**
 * @author yaodw
 */
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class TeacherRepoTest {
    @Autowired
    TeacherRepo teacherRepo;

    @Test
    public void testClassRepo() {
        log.debug("查出所有老师信息包含学校、班级信息");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        teacherRepo.findAll(PageRequest.of(1, 20)).forEach(teacher -> {
            teacher.getClasses().forEach(aClass -> {
                log.debug(teacher.getSchool().getName() + " : " + aClass.getName() + " : " + teacher.getName());
            });
        });
        log.debug("---------------" + (System.currentTimeMillis() - start) + " 毫秒" + "----------------");
    }

    @Test
    public void testClassRepoBySpecFactory() {
        log.debug("查出所有老师信息包含学校、班级信息, 工厂优化版");
        log.debug("-------------------------------");

        Specification<Teacher> specification = (Specification<Teacher>) (root, query, criteriaBuilder) -> {
            root.joinSet("classes");
            return PredicateFactory.getTeacherPredicate(root, null, query, criteriaBuilder, null);
        };

        long start = System.currentTimeMillis();
        teacherRepo.findAll(specification, PageRequest.of(1, 20)).forEach(teacher -> {
            teacher.getClasses().forEach(aClass -> {
                log.debug(teacher.getSchool().getName() + " : " + aClass.getName() + " : " + teacher.getName());
            });
        });
        log.warn(System.currentTimeMillis() - start + "毫秒");

        log.debug("-------------------------------");
    }
}