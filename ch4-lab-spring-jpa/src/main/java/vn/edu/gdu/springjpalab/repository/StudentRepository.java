package vn.edu.gdu.springjpalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.gdu.springjpalab.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository đã cung cấp sẵn các hàm như save(), findAll(), findById(), deleteById()...
    // Bạn không cần viết thêm code ở đây trừ khi cần query đặc biệt.
}