package pl.ml.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public List<Student> findAllAndOrderBy(String sort) {
        switch (sort) {
            case "firstNameDesc":
                return studentRepository.findAllByOrderByFirstNameDesc();
            case "lastNameAsc":
                return studentRepository.findAllByOrderByLastNameAsc();
            case "lastNameDesc":
                return studentRepository.findAllByOrderByLastNameDesc();
            default:
                return studentRepository.findAllByOrderByFirstNameAsc();
        }
    }

    public List<Student> findAll(String name, String sort) {
        if (name == null && sort == null) {
            return studentRepository.findAll();
        }
        List<Student> sortedStudents = findAllAndOrderBy(sort);
        List<Student> result = filterByName(sortedStudents, name);
        return result;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> filterByName(List<Student> students, String name) {
        return students.stream().filter(s -> s.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                s.getLastName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAllByTutorId(Long id) {
        return studentRepository.findAllByTutorsId(id);
    }
}
