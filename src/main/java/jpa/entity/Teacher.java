package jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Data
@Entity
@ToString(exclude = {"id","classes", "students"})
@Table(name = "teacher")
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "class_teacher",
            joinColumns = {@JoinColumn(name = "teacher_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "class_id", referencedColumnName = "id")})
    private Set<Class> classes = new HashSet<>();

    public Teacher(String name, School school) {
        this.name = name;
        this.school = school;
    }
}
