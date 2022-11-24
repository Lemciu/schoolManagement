package pl.ml.student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.ml.tutor.Tutor;

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
public class Student {
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
    private FieldOfStudy fieldOfStudy;
    @ManyToMany
    @JoinTable(
            name = "tutor_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "tutor_id")
    )
    private Set<Tutor> tutors;

    public Student(String firstName, String lastName, Integer age, String email, FieldOfStudy fieldOfStudy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.fieldOfStudy = fieldOfStudy;
    }

    public void addTutor(Tutor tutor) {
        this.tutors.add(tutor);
        tutor.getStudents().add(this);
    }

    public void removeTutor(Tutor tutor) {
        this.tutors.remove(tutor);
        tutor.getStudents().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
