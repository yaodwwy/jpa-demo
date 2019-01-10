package jpa;

import jpa.entity.Class;
import jpa.entity.School;
import jpa.entity.Student;
import jpa.entity.Teacher;
import jpa.repo.SchoolRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

/**
 * @author yaodw
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner init(SchoolRepo schoolRepo) {
        return (args) -> {
            // 初始化测试数据

            //初始化10个学校
            Set<School> schools = new HashSet<>();
            for (int i = 0; i < 20; i++) {
                School school = new School();
                //初始化10个班每校
                Set<Class> classes = new HashSet<>();
                for (int j = 0; j < 10; j++) {
                    Class aClass = new Class();
                    //初始化10个老师每班
                    Set<Teacher> teachers = new HashSet<>();
                    for (int t = 0; t < 5; t++) {
                        Teacher teacher = new Teacher("第" + i + j + t + "号老师", school);
                        teachers.add(teacher);
                    }
                    //初始化10个学生每班
                    Set<Student> students = new HashSet<>();
                    for (int s = 0; s < 1; s++) {
                        Student student = new Student("第" + i + j + s + "名学生", aClass, school);
                        students.add(student);
                    }

                    aClass.setName("第" + i + j + "班");
                    aClass.setSchool(school);
                    aClass.setTeachers(teachers);
                    aClass.setStudents(students);
                    classes.add(aClass);
                }
                school.setName("双叶幼儿园 第" + i + "分校");
                school.setClasses(classes);
                schools.add(school);
            }

            schoolRepo.saveAll(schools);

        };
    }

}
