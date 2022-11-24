package pl.ml.tutor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ml.student.Student;
import pl.ml.student.StudentService;

import javax.validation.Valid;

@Controller
public class TutorController {
    private TutorService tutorService;
    private StudentService studentService;

    public TutorController(TutorService tutorService, StudentService studentService) {
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @GetMapping("/addTutor")
    public String addTutor(Model model) {
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("tutor", new Tutor("", "", 18, "", Subject.CHEMISTRY));
        model.addAttribute("title", "Add Tutor");
        return "tutorForm";
    }

    @GetMapping("/tutor")
    public String getTutor(@RequestParam Long id,
                           Model model) {
        model.addAttribute("tutor", tutorService.findById(id).orElseThrow());
        model.addAttribute("students", studentService.findAllByTutorId(id));
        model.addAttribute("studentsList", studentService.findAll());
        return "tutorInfo";
    }

    @GetMapping("/editTutor")
    public String editTutor(@RequestParam Long id,
                            Model model) {
        Tutor tutor = tutorService.findById(id).orElseThrow();
        model.addAttribute("subjects", Subject.values());
        model.addAttribute("tutor", tutor);
        model.addAttribute("title", tutor.getFirstName() + " " + tutor.getLastName());
        return "tutorForm";
    }

    @GetMapping("tutors")
    public String showAllTutors(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String sort,
                                Model model) {
        model.addAttribute("tutors", tutorService.findAll(name, sort));
        model.addAttribute("name", name);
        return "tutors";
    }

    @GetMapping("/deleteTutor")
    public String deleteTutor(@RequestParam Long id) {
        Tutor tutor = tutorService.findById(id).orElseThrow();
        tutorService.delete(tutor);
        return "redirect:/tutors";
    }

    @PostMapping("/saveTutor")
    public String saveTutor(@RequestParam(required = false) Long tutorId,
                            @Valid Tutor tutor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subjects", Subject.values());
            return "tutorForm";
        }
        if (tutorId != null) {
            tutor.setId(tutorId);
        }
        tutorService.save(tutor);
        return "redirect:/tutor?id=" + tutor.getId();
    }

    @PostMapping("/addStudentToTutor")
    public String addStudentToTutor(@RequestParam Long tutorId, Long studentId) {
        Student student = studentService.findById(studentId).orElseThrow();
        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        tutor.addStudent(student);
        tutorService.save(tutor);
        return "redirect:/tutor?id=" + tutorId;
    }

    @GetMapping("/deleteStudentFromTutor")
    public String deleteStudentFromTutor(@RequestParam Long studentId, Long tutorId) {
        Tutor tutor = tutorService.findById(tutorId).orElseThrow();
        Student student = studentService.findById(studentId).orElseThrow();
        tutor.removeStudent(student);
        tutorService.save(tutor);
        return "redirect:/tutor?id=" + tutorId;
    }

}
