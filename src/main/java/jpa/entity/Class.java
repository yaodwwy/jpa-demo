package jpa.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"teachers","students"})
@ToString(exclude = {"teachers","students"})
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
    private Set<Teacher> teachers;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "aClass")
    private Set<Student> students;

    public Class(String name, School school, Set<Teacher> teachers, Set<Student> students) {
        this.name = name;
        this.school = school;
        this.teachers = teachers;
        this.students = students;
    }
}

