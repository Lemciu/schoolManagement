package pl.ml.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ml.tutor.Tutor;
import pl.ml.tutor.TutorService;

import javax.validation.Valid;

@Controller
public class StudentController {
    private StudentService studentService;
    private TutorService tutorService;

    public StudentController(StudentService studentService, TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("fieldsOfStudy", FieldOfStudy.values());
        model.addAttribute("student", new Student("", "", 18, "", FieldOfStudy.LAW));
        model.addAttribute("title", "Add Student");
        return "studentForm";
    }

    @GetMapping("/editStudent")
    public String editStudent(@RequestParam Long id,
                              Model model) {
        Student student = studentService.findById(id).orElseThrow();
        model.addAttribute("fieldsOfStudy", FieldOfStudy.values());
        model.addAttribute("student", student);
        model.addAttribute("title", student.getFirstName() + " " + student.getLastName());
        return "studentForm";
    }

    @GetMapping("/student")
    public String getStudent(@RequestParam Long id,
                             Model model) {
        model.addAttribute("student", studentService.findById(id).orElseThrow());
        model.addAttribute("tutors", tutorService.findAllByStudentId(id));
        model.addAttribute("tutorsList", tutorService.findAll());
        return "studentInfo";
    }

    @GetMapping("/students")
    public String showAllStudents(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String sort,
                                  Model model) {
        model.addAttribute("students", studentService.findAll(name, sort));
        model.addAttribute("name", name);
        return "students";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long id) {
        Student student = studentService.findById(id).orElseThrow();
        studentService.delete(student);
        return "redirect:/students";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@RequestParam(required = false) Long studentId,
                              @Valid Student student, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("fieldsOfStudy", FieldOfStudy.values());
            return "studentForm";
        }
        if (studentId != null) {
            student.setId(studentId);
        }
        studentService.save(student);
        return "redirect:/student?id=" + student.getId();
    }

    @PostMapping("/addTutorToStudent")
    public String addTutorToStudent(@RequestParam Long studentId, Long tutorId) {
        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Student student = studentService.findById(studentId).orElseThrow();
        student.addTutor(tutor);
        studentService.save(student);
        return "redirect:/student?id=" + studentId;
    }

    @GetMapping("/deleteTutorFromStudent")
    public String deleteTutorFromStudent(@RequestParam Long studentId, Long tutorId) {
        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Student student = studentService.findById(studentId).orElseThrow();
        student.removeTutor(tutor);
        studentService.save(student);
        return "redirect:/student?id=" + studentId;
    }

}
