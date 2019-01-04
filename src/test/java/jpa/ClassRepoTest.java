package jpa;

import jpa.entity.Class;
import jpa.entity.School;
import jpa.factory.PredicateFactory;
import jpa.repo.ClassRepo;
import jpa.repo.SchoolRepo;
import jpa.repo.StudentRepo;
import jpa.repo.TeacherRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.JoinType;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class ClassRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ClassRepo classRepo;

    @Test
    public void testClassRepo() {
        log.debug("查出所有班级的信息包含老师、学生、学校信息");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        classRepo.findAll(PageRequest.of(1, 20)).forEach(aClass -> {
            aClass.getTeachers().forEach(teacher ->
                    log.debug(aClass.getSchool().getName() + " : " + aClass.getName() + " : " + teacher.getName()));
            aClass.getStudents().forEach(student ->
                    log.debug(aClass.getSchool().getName() + " : " + aClass.getName() + " : " + student.getName()));
        });
        log.debug("---------------" + (System.currentTimeMillis() - start) + " 毫秒" + "----------------");
    }

    @Test
    public void testClassRepoBySpecFactory() {
        log.debug("查出所有班级的信息包含老师、学生、学校信息, 工厂优化版");
        log.debug("-------------------------------");

        Specification<Class> specification = (Specification<Class>) (root, query, criteriaBuilder) -> {
            root.joinSet("teachers");
            root.joinSet("students");
            return PredicateFactory.getClassPredicate(root, null, query, criteriaBuilder, null);
        };

        long start = System.currentTimeMillis();
        classRepo.findAll(specification,PageRequest.of(1, 20)).forEach(aClass -> {
            aClass.getTeachers().forEach(teacher ->
                    log.debug(aClass.getSchool().getName() + " : " + aClass.getName() + " : " + teacher.getName()));
            aClass.getStudents().forEach(student ->
                    log.debug(aClass.getSchool().getName() + " : " + aClass.getName() + " : " + student.getName()));
        });
        log.warn(System.currentTimeMillis() - start + "毫秒");

        log.debug("-------------------------------");
    }

}