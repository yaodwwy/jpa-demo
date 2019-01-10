package jpa;

import jpa.entity.Class;
import jpa.entity.School;
import jpa.entity.Student;
import jpa.entity.Teacher;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;
import javax.persistence.metamodel.SetAttribute;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class ClassRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ClassRepo classRepo;
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    TeacherRepo teacherRepo;

    @Test
    public void testClassRepo() {
        log.debug("查出所有班级的信息包含老师、学生、学校信息");
        log.debug("-------------------------------");
        long start = System.currentTimeMillis();
        List<Class> content = classRepo.findAll(PageRequest.of(1, 20)).getContent();
        content.forEach(System.out::println);
        log.debug("---------------" + (System.currentTimeMillis() - start) + " 毫秒" + "----------------");
    }

    @Test
    public void testClassRepoBySpecFactory() {
        log.debug("查出所有班级的信息包含老师、学生、学校信息, 工厂优化版");
        log.debug("-------------------------------");

        Specification<Class> specification = (Specification<Class>) (root, query, criteriaBuilder) -> {
            /**
             * ! 警告!警告 !:
             * firsResult / maxResults 与 JOIN FETCH 如果一起用,会在内存中分页!!
             * firstResult/maxResults specified with collection fetch; applying in memory!
             * 所以，分页不要加载集合字段！
             * 所以这里不能写 fetch
             */
            root.joinSet("teachers");
            root.joinSet("students");
            return PredicateFactory.getClassPredicate(root, null, query, criteriaBuilder, null);
        };

        long start = System.currentTimeMillis();
        List<Class> all = classRepo.findAll(specification, PageRequest.of(0, 20)).getContent();
        Set<Class> classes = new HashSet<>();
        all.forEach(aClass -> {
            log.debug("{}", aClass);
            classes.add(aClass);
        });
        log.warn(System.currentTimeMillis() - start + "毫秒");

        log.debug("-------------------------------");
    }

}