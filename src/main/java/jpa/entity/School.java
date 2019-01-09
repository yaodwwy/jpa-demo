package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"classes", "students"})
@ToString(exclude = {"classes", "students"})
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "school.classes",
        attributeNodes = @NamedAttributeNode("classes"))
@NamedEntityGraph(name = "school.students",
        attributeNodes = @NamedAttributeNode("students"))
public class School {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "school")
    private Set<Class> classes;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "school")
    private Set<Student> students;
}

