package jpa;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.criteria.*;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class SchoolRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    SchoolRepo schoolRepo;
    @Autowired
    ClassRepo classRepo;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    StudentRepo studentRepo;

    @Test
    public void testSchoolRepo() {
        log.debug("查出所有学校的信息");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        schoolRepo.findAll().forEach(school -> log.debug(school.getName()));
        log.warn(System.currentTimeMillis() - start + "毫秒");
        log.debug("-------------------------------");
    }

    @Test
    public void testSchoolRepoIncludeClass() {

        log.debug("查出所有学校的信息包含班级、学生");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        schoolRepo.findAll(PageRequest.of(3, 2)).forEach(school -> {
            log.debug("{}", school);
            log.debug("studentsSize:" + school.studentsSize());
            log.debug("; classesSize" + school.classesSize());
        });
        log.warn(System.currentTimeMillis() - start + "毫秒");
        log.debug("-------------------------------");
    }

    @Test
    public void testSchoolRepoIncludeClassOptimizationBySpecFactory() {
        log.debug("查出所有学校的信息包含班级工厂优化版");
        log.debug("-------------------------------");

        Specification<School> spec = (Specification<School>) (root, query, criteriaBuilder) -> {
            root.joinSet("classes");
            root.joinSet("students");
            return PredicateFactory.getSchoolPredicate(root, null, query, criteriaBuilder, null);
        };

        long start = System.currentTimeMillis();
        Page<School> content = schoolRepo.findAll(spec, PageRequest.of(0, 10));
        content.forEach(school -> {
            log.debug("{}", school);
            log.debug("classesSize" + school.classesSize());
            log.debug("; studentsSize" + school.studentsSize());
        });
        log.warn(System.currentTimeMillis() - start + "毫秒");

        log.debug("-------------------------------");
    }

}