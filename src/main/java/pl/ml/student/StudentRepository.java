package pl.ml.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByTutorsId(Long id);

    List<Student> findAllByOrderByFirstNameAsc();

    List<Student> findAllByOrderByFirstNameDesc();

    List<Student> findAllByOrderByLastNameAsc();

    List<Student> findAllByOrderByLastNameDesc();

}
