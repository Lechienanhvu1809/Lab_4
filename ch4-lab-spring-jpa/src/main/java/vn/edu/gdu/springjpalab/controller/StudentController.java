package vn.edu.gdu.springjpalab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.gdu.springjpalab.entity.Student;
import vn.edu.gdu.springjpalab.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. Lấy danh sách toàn bộ sinh viên (READ)
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 2. Lấy thông tin 1 sinh viên theo ID (READ)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
    }

    // 3. Thêm một sinh viên mới (CREATE)
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // 4. Cập nhật thông tin sinh viên (UPDATE)
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));
    }

    // 5. Xóa một sinh viên (DELETE)
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return "Không tìm thấy sinh viên để xóa!";
        }
        studentRepository.deleteById(id);
        return "Đã xóa sinh viên thành công!";
    }
}