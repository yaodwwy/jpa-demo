package jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString(exclude = {"id"})
public class Student {
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
