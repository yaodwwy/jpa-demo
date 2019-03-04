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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"id"}, callSuper = true)
public class Student extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    public Student(String name, Class aClass, School school) {
        this.name = name;
        this.aClass = aClass;
        this.school = school;
    }
}
