package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"classes", "students"})
@ToString(exclude = {"id","classes", "students"})
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "School.classes",
        attributeNodes = @NamedAttributeNode("classes"))
@NamedEntityGraph(name = "School.students",
        attributeNodes = @NamedAttributeNode("students"))
public class School {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "school")
    private Set<Class> classes = new HashSet<>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "school")
    private Set<Student> students = new HashSet<>();

    public int classesSize() {
        return getClasses().size();
    }
    public int studentsSize() {
        return getStudents().size();
    }
}

