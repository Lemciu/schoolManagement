package pl.ml.tutor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ml.student.Student;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2)
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(value = 18, message = "Age should be more than 18 years old")
    private Integer age;
    @NotBlank
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private Subject subject;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "tutors")
    private Set<Student> students;

    public Tutor(String firstName, String lastName, Integer age, String email, Subject subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getTutors().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getTutors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return id.equals(tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
