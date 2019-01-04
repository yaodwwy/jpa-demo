package jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * @author yaodw
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"classes", "students"})
@ToString(exclude = {"classes", "students"})
@NoArgsConstructor
@AllArgsConstructor
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

