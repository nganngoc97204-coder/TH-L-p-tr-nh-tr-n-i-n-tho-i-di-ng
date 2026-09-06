data class Student(
    val id: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
)

fun main() {
    // 5 sinh viên mẫu
    val studentList = mutableListOf(
        Student("SV01", "Nguyễn Vũ Hoàng Ngân", 20, "IT", 8.5),
        Student("SV02", "Lê Thị Minh Thảo", 22, "IT", 4.5),
        Student("SV03", "Nguyễn Thị Thục Quyên", 21, "Marketing", 7.2),
        Student("SV04", "Phạm Minh Đức", 19, "IT", 9.0),
        Student("SV05", "Vũ Thị Giang", 23, "Marketing", 6.8)
    )

    println("=== DANH SÁCH SINH VIÊN BAN ĐẦU ===")
    displayAllStudents(studentList)

    println("\n================ KẾT QUẢ 12 YÊU CẦU ================")

    // 1. Đếm số sinh viên có GPA >= 8.0
    val countGpa8 = studentList.count { it.gpa >= 8.0 }
    println("1. Số sinh viên có GPA >= 8.0: $countGpa8")

    // 2. Đếm số sinh viên có GPA < 5.0
    val countGpa5 = studentList.count { it.gpa < 5.0 }
    println("2. Số sinh viên có GPA < 5.0: $countGpa5")

    // 3. Tính GPA trung bình của sinh viên ngành được giao (Ví dụ: IT)
    val targetMajor = "IT"
    val avgMajor = studentList.filter { it.major.equals(targetMajor, ignoreCase = true) }
                              .map { it.gpa }
                              .average()
    println("3. GPA trung bình ngành $targetMajor: %.2f".format(avgMajor))

    // 4. Tìm sinh viên có GPA cao nhất
    println("\n4. Sinh viên có GPA cao nhất:")
    studentList.maxByOrNull { it.gpa }?.let { displayAllStudents(listOf(it)) }

    // 5. Tìm sinh viên lớn tuổi nhất
    println("\n5. Sinh viên lớn tuổi nhất:")
    studentList.maxByOrNull { it.age }?.let { displayAllStudents(listOf(it)) }

    // 6. Tìm sinh viên có GPA nằm trong khoảng 7.0 -> 8.5
    println("\n6. Sinh viên có GPA từ 7.0 đến 8.5:")
    displayAllStudents(studentList.filter { it.gpa in 7.0..8.5 })

    // 7. Tìm tất cả sinh viên thuộc một ngành (Ví dụ: Marketing)
    val searchMajor = "Marketing"
    println("\n7. Sinh viên thuộc ngành $searchMajor:")
    displayAllStudents(studentList.filter { it.major.equals(searchMajor, ignoreCase = true) })

    // 8. Tìm sinh viên theo một phần tên (Ví dụ: "An")
    val namePart = "An"
    println("\n8. Sinh viên có chứa từ '$namePart' trong tên:")
    displayAllStudents(studentList.filter { it.fullName.contains(namePart, ignoreCase = true) })

    // 9. Sắp xếp sinh viên theo GPA giảm dần
    println("\n9. Sắp xếp theo GPA giảm dần:")
    displayAllStudents(studentList.sortedByDescending { it.gpa })

    // 10. Hiển thị 3 sinh viên có GPA cao nhất
    println("\n10. Top 3 sinh viên có GPA cao nhất:")
    displayAllStudents(studentList.sortedByDescending { it.gpa }.take(3))

    // 11. Sắp xếp sinh viên theo tuổi
    println("\n11. Sắp xếp theo tuổi tăng dần:")
    displayAllStudents(studentList.sortedBy { it.age })

    // 12. Sắp xếp sinh viên theo tên
    println("\n12. Sắp xếp theo tên (A-Z):")
    displayAllStudents(studentList.sortedBy { it.fullName.split(" ").last() })
}

// Hàm in danh sách ra màn hình đẹp mắt
fun displayAllStudents(list: List<Student>) {
    println("%-8s | %-18s | %-5s | %-10s | %-5s".format("ID", "Họ và Tên", "Tuổi", "Ngành", "GPA"))
    println("-".repeat(55))
    for (s in list) {
        println("%-8s | %-18s | %-5d | %-10s | %-5.2f".format(s.id, s.fullName, s.age, s.major, s.gpa))
    }
}