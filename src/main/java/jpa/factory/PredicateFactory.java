package jpa.factory;


import jpa.entity.Class;
import jpa.entity.School;
import jpa.entity.Student;
import jpa.entity.Teacher;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yaodw
 */
@SuppressWarnings("Duplicates")
public class PredicateFactory {

    private static String pattern(String param) {
        return "%" + param.toUpperCase() + "%";
    }

    public static Predicate getSchoolPredicate(From<?, School> root,
                                               Fetch<?, School> parent,
                                               CriteriaQuery<?> query,
                                               CriteriaBuilder cb,
                                               School school) {
        List<Predicate> predicates = new ArrayList<>();

        if (school == null) {
            school = new School();
        }
        predicates = getNameLike(predicates, cb, root, school.getName());
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public static Predicate getClassPredicate(From<?, Class> root,
                                              Fetch<?, Class> parent,
                                              CriteriaQuery<?> query,
                                              CriteriaBuilder cb,
                                              Class aClass) {
        List<Predicate> predicates = new ArrayList<>();
        Fetch<Class, School> fetchSchool = null;
        if (!Long.class.equals(query.getResultType())) {
            if (parent != null) {
                fetchSchool = parent.fetch("school");
            } else {
                fetchSchool = root.fetch("school");
            }
        }
        Join<Class, School> joinSchool = root.join("school");

        if (aClass == null) {
            aClass = new Class();
        }
        predicates.add(getSchoolPredicate(joinSchool, fetchSchool, query, cb, aClass.getSchool()));
        predicates = getNameLike(predicates, cb, root, aClass.getName());

        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public static Predicate getTeacherPredicate(From<?, Teacher> root,
                                                Fetch<?, Teacher> parent,
                                                CriteriaQuery<?> query,
                                                CriteriaBuilder cb,
                                                Teacher teacher) {
        List<Predicate> predicates = new ArrayList<>();

        Fetch<Teacher, School> fetchSchool = null;

        if (!Long.class.equals(query.getResultType())) {
            if (parent != null) {
                fetchSchool = parent.fetch("school");
            } else {
                fetchSchool = root.fetch("school");
            }
        }

        Join<Teacher, School> joinSchool = root.join("school");

        if (teacher == null) {
            teacher = new Teacher();
        }
        predicates.add(getSchoolPredicate(joinSchool, fetchSchool, query, cb, teacher.getSchool()));
        predicates = getNameLike(predicates, cb, root, teacher.getName());
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }


    public static Predicate getStudentPredicate(From<?, Student> root,
                                                Fetch<?, Student> parent,
                                                CriteriaQuery<?> query,
                                                CriteriaBuilder cb,
                                                Student student) {
        List<Predicate> predicates = new ArrayList<>();

        Fetch<Student, Class> fetchClass = null;
        Fetch<Student, School> fetchSchool = null;

        if (!Long.class.equals(query.getResultType())) {
            if (parent != null) {
                fetchClass = parent.fetch("aClass");
                fetchSchool = parent.fetch("school");
            } else {
                fetchClass = root.fetch("aClass");
                fetchSchool = root.fetch("school");
            }
        }

        Join<Student, Class> joinClass = root.join("aClass");
        Join<Student, School> joinSchool = root.join("school");

        if (student == null) {
            student = new Student();
        }
        predicates.add(getClassPredicate(joinClass, fetchClass, query, cb, student.getAClass()));
        predicates.add(getSchoolPredicate(joinSchool, fetchSchool, query, cb, student.getSchool()));
        predicates = getNameLike(predicates, cb, root, student.getName());
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private static List<Predicate> getNameLike(List<Predicate> predicates, CriteriaBuilder cb, From<?, ?> root, String name) {
        if (name != null) {
            predicates.add(cb.like(cb.upper(root.get("name")), pattern(name)));
        }
        return predicates;
    }
}
