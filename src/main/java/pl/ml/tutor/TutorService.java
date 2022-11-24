package pl.ml.tutor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorService {
    private TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public void save(Tutor tutor) {
        tutorRepository.save(tutor);
    }

    public List<Tutor> findAllByStudentId(Long id) {
        return tutorRepository.findAllByStudentsId(id);
    }

    public Optional<Tutor> findById(Long id) {
        return tutorRepository.findById(id);
    }

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public List<Tutor> findAll(String name, String sort) {
        if (name == null && sort == null) {
            return tutorRepository.findAll();
        }

        List<Tutor> sortedTutors = findAllAndOrderBy(sort);
        List<Tutor> result = filterByName(sortedTutors, name);
        return result;
    }

    public List<Tutor> filterByName(List<Tutor> tutors, String name) {
        return tutors.stream().filter(s -> s.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                s.getLastName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Tutor> findAllAndOrderBy(String sort) {
        switch (sort) {
            case "firstNameDesc":
                return tutorRepository.findAllByOrderByFirstNameDesc();
            case "lastNameAsc":
                return tutorRepository.findAllByOrderByLastNameAsc();
            case "lastNameDesc":
                return tutorRepository.findAllByOrderByLastNameDesc();
            default:
                return tutorRepository.findAllByOrderByFirstNameAsc();
        }
    }

    public void delete(Tutor tutor) {
        tutorRepository.delete(tutor);
    }

}
