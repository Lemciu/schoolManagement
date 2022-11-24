package pl.ml.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    List<Tutor> findAllByStudentsId(Long id);

    List<Tutor> findAllByOrderByFirstNameDesc();

    List<Tutor> findAllByOrderByLastNameAsc();

    List<Tutor> findAllByOrderByLastNameDesc();

    List<Tutor> findAllByOrderByFirstNameAsc();

}
