package jpa.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"teachers","students","teachersSize","studentsSize"})
@ToString(exclude = {"id","teachers","students"})
@NoArgsConstructor
@AllArgsConstructor
public class Class {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "class_teacher",
            joinColumns = {@JoinColumn(name = "class_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")})
    private Set<Teacher> teachers = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "aClass")
    private Set<Student> students = new HashSet<>();

    public Class(String name, School school, Set<Teacher> teachers, Set<Student> students) {
        this.name = name;
        this.school = school;
        this.teachers = teachers;
        this.students = students;
    }

    public int getTeachersSize() {
        return getTeachers().size();
    }

    public int getStudentsSize() {
        return getTeachers().size();
    }
}

