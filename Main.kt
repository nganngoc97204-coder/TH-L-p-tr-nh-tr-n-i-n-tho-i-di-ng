import java.util.Scanner

// 1. Khai bao lop SinhVien
data class SinhVien(
    var studentID: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
)

fun main() {
    val scanner = Scanner(System.`in`)

    // Khai bao danh sach 5 sinh vien mau
    val danhSach = mutableListOf(
        SinhVien("SV01", "Nguyen Vu Hoang Ngan", 20, "CNTT", 9.2),
        SinhVien("SV02", "Selena Whitman", 21, "CNTT", 4.8),
        SinhVien("SV03", "Sean", 19, "Marketing", 7.5),
        SinhVien("SV04", "Martin", 22, "CNTT", 8.2),
        SinhVien("SV05", "James", 20, "Marketing", 6.0)
    )

    var luaChon = -1

    while (luaChon != 0) {
        // Hien thi menu co ban bang tieng Viet khong dau
        println("\n========== QUAN LY SINH VIEN ==========")
        println("1. Them sinh vien")
        println("2. Hien thi tat ca sinh vien")
        println("3. Tim kiem sinh vien")
        println("4. Tinh GPA trung binh")
        println("5. Tim sinh vien co GPA cao nhat")
        println("6. Xoa sinh vien")
        println("7. Chay day du 16 yeu cau chi tiet")
        println("0. Thot chuong trinh")
        println("========================================")
        print("Chon chuc nang: ")

        try {
            luaChon = scanner.nextLine().toInt()
        } catch (e: Exception) {
            luaChon = -1
        }

        when (luaChon) {
            1 -> {
                println("\n--- 1. THEM SINH VIEN ---")
                print("Nhap Ma sinh vien: ")
                val id = scanner.nextLine()

                print("Nhap Ho va ten: ")
                val name = scanner.nextLine()

                print("Nhap Tuoi: ")
                val age = scanner.nextLine().toIntOrNull() ?: 0

                print("Nhap Nganh hoc: ")
                val major = scanner.nextLine()

                print("Nhap Diem GPA (0 - 10): ")
                val gpa = scanner.nextLine().toDoubleOrNull() ?: -1.0

                // Yeu cau 14: Validation kiem tra diem
                if (gpa < 0.0 || gpa > 10.0) {
                    println("-> THONG BAO LOI: Diem GPA khong hop le! GPA phai thuoc khoang 0 den 10.")
                } else {
                    danhSach.add(SinhVien(id, name, age, major, gpa))
                    println("-> Them sinh vien thanh cong!")
                }
            }

            2 -> {
                println("\n--- 2. HIEN THI TAT CA SINH VIEN ---")
                if (danhSach.isEmpty()) {
                    println("Danh sach hien tai dang trong!")
                } else {
                    for (sv in danhSach) {
                        println("Ma SV: ${sv.studentID} | Ten: ${sv.fullName} | Tuoi: ${sv.age} | Nganh: ${sv.major} | GPA: ${sv.gpa}")
                    }
                }
            }

            3 -> {
                println("\n--- 3. TIM KIEM SINH VIEN ---")
                print("Nhap ten hoac ma sinh vien can tim: ")
                val tuKhoa = scanner.nextLine().lowercase()
                var timThay = false

                for (sv in danhSach) {
                    if (sv.studentID.lowercase().contains(tuKhoa) || sv.fullName.lowercase().contains(tuKhoa)) {
                        println("Ma SV: ${sv.studentID} | Ten: ${sv.fullName} | Tuoi: ${sv.age} | Nganh: ${sv.major} | GPA: ${sv.gpa}")
                        timThay = true
                    }
                }

                if (!timThay) {
                    println("Khong tim thay sinh vien nao phu hop!")
                }
            }

            4 -> {
                println("\n--- 4. TINH GPA TRUNG BINH ---")
                if (danhSach.isEmpty()) {
                    println("Danh sach hien tai dang trong!")
                } else {
                    var tongGPA = 0.0
                    for (sv in danhSach) {
                        tongGPA += sv.gpa
                    }
                    val gpaTB = tongGPA / danhSach.size
                    println("GPA trung binh cua tat ca sinh vien la: $gpaTB")
                }
            }

            5 -> {
                println("\n--- 5. TIM SINH VIEN CO GPA CAO NHAT ---")
                if (danhSach.isEmpty()) {
                    println("Danh sach hien tai dang trong!")
                } else {
                    var maxSV = danhSach[0]
                    for (sv in danhSach) {
                        if (sv.gpa > maxSV.gpa) {
                            maxSV = sv
                        }
                    }
                    println("Sinh vien co GPA cao nhat: Ma SV: ${maxSV.studentID} | Ten: ${maxSV.fullName} | GPA: ${maxSV.gpa}")
                }
            }

            6 -> {
                println("\n--- 6. XOA SINH VIEN ---")
                print("Nhap Ma sinh vien can xoa: ")
                val idXoa = scanner.nextLine()
                var viTriXoa = -1

                for (i in 0 until danhSach.size) {
                    if (danhSach[i].studentID.equals(idXoa, ignoreCase = true)) {
                        viTriXoa = i
                        break
                    }
                }

                if (viTriXoa != -1) {
                    danhSach.removeAt(viTriXoa)
                    println("-> Da xoa sinh vien co ma $idXoa thanh cong!")
                } else {
                    println("-> Khong tim thay sinh vien co ma $idXoa!")
                }
            }

            7 -> {
                println("\n================ KET QUA 16 YEU CAU CHI TIET ================")

                // 1. Dem so sinh vien co GPA >= 8.0
                var dem1 = 0
                for (sv in danhSach) {
                    if (sv.gpa >= 8.0) dem1++
                }
                println("1. So sinh vien co GPA >= 8.0: $dem1")

                // 2. Dem so sinh vien co GPA < 5.0
                var dem2 = 0
                for (sv in danhSach) {
                    if (sv.gpa < 5.0) dem2++
                }
                println("2. So sinh vien co GPA < 5.0: $dem2")

                // 3. Tinh GPA trung binh cua sinh vien nganh CNTT
                var tongGpaCNTT = 0.0
                var demCNTT = 0
                for (sv in danhSach) {
                    if (sv.major.equals("CNTT", ignoreCase = true)) {
                        tongGpaCNTT += sv.gpa
                        demCNTT++
                    }
                }
                val gpaTBCNTT = if (demCNTT > 0) tongGpaCNTT / demCNTT else 0.0
                println("3. GPA trung binh nganh CNTT: $gpaTBCNTT")

                // 4. Tim sinh vien co GPA cao nhat
                if (danhSach.isNotEmpty()) {
                    var maxGpaSV = danhSach[0]
                    for (sv in danhSach) {
                        if (sv.gpa > maxGpaSV.gpa) maxGpaSV = sv
                    }
                    println("4. Sinh vien co GPA cao nhat: ${maxGpaSV.fullName} - GPA: ${maxGpaSV.gpa}")
                }

                // 5. Tim sinh vien lon tuoi nhat
                if (danhSach.isNotEmpty()) {
                    var maxAgeSV = danhSach[0]
                    for (sv in danhSach) {
                        if (sv.age > maxAgeSV.age) maxAgeSV = sv
                    }
                    println("5. Sinh vien lon tuoi nhat: ${maxAgeSV.fullName} - Tuoi: ${maxAgeSV.age}")
                }

                // 6. Tim sinh vien co GPA tu 7.0 den 8.5
                println("\n6. Sinh vien co GPA tu 7.0 den 8.5:")
                for (sv in danhSach) {
                    if (sv.gpa in 7.0..8.5) {
                        println("   + ${sv.fullName} - GPA: ${sv.gpa}")
                    }
                }

                // 7. Tim tat ca sinh vien thuoc nganh Marketing
                println("\n7. Sinh vien thuoc nganh Marketing:")
                for (sv in danhSach) {
                    if (sv.major.equals("Marketing", ignoreCase = true)) {
                        println("   + ${sv.fullName}")
                    }
                }

                // 8. Tim sinh vien theo mot phan ten (vi du 'Ngan')
                println("\n8. Sinh vien co chua tu 'Ngan' trong ten:")
                for (sv in danhSach) {
                    if (sv.fullName.lowercase().contains("ngan")) {
                        println("   + ${sv.fullName}")
                    }
                }

                // 9. Sap xep sinh vien theo GPA giam dan
                println("\n9. Danh sach theo GPA giam dan:")
                val dsGpaGiam = danhSach.sortedByDescending { it.gpa }
                for (sv in dsGpaGiam) {
                    println("   + ${sv.fullName} - GPA: ${sv.gpa}")
                }

                // 10. Hien thi 3 sinh vien co GPA cao nhat
                println("\n10. Top 3 sinh vien GPA cao nhat:")
                val top3 = dsGpaGiam.take(3)
                for (sv in top3) {
                    println("   + ${sv.fullName} - GPA: ${sv.gpa}")
                }

                // 11. Sap xep sinh vien theo tuoi tang dan
                println("\n11. Danh sach theo tuoi tang dan:")
                val dsTuoiTang = danhSach.sortedBy { it.age }
                for (sv in dsTuoiTang) {
                    println("   + ${sv.fullName} - Tuoi: ${sv.age}")
                }

                // 12. Sap xep sinh vien theo ten
                println("\n12. Danh sach sap xep theo ten (A-Z):")
                val dsTen = danhSach.sortedBy { it.fullName.split(" ").last() }
                for (sv in dsTen) {
                    println("   + ${sv.fullName}")
                }

                // 13. Tim theo ma sinh vien 'SV01'
                println("\n13. Tim sinh vien theo ma 'SV01':")
                var sv01: SinhVien? = null
                for (sv in danhSach) {
                    if (sv.studentID.equals("SV01", ignoreCase = true)) {
                        sv01 = sv
                        break
                    }
                }
                if (sv01 != null) {
                    println("   + Tim thay: ${sv01.fullName}")
                } else {
                    println("   + Khong tim thay!")
                }

                // 14. Validation diem trung binh
                println("\n14. Thuc hien Kiem tra Validation voi diem kiem thu 11.5:")
                val testGpa = 11.5
                if (testGpa < 0.0 || testGpa > 10.0) {
                    println("   + THONG BAO LOI: Diem $testGpa khong hop le (phai thuoc khoang 0 den 10)")
                } else {
                    println("   + Diem $testGpa hop le")
                }

                // 15. Loc danh sach nhan hoc bong (GPA > 9.0)
                println("\n15. Danh sach sinh vien nhan hoc bong (GPA > 9.0):")
                for (sv in danhSach) {
                    if (sv.gpa > 9.0) {
                        println("   + ${sv.fullName} - GPA: ${sv.gpa}")
                    }
                }

                // 16. Sap xep danh sach theo ten ABC
                println("\n16. Sap xep danh sach theo ten bang chu cai ABC:")
                for (sv in dsTen) {
                    println("   + ${sv.fullName}")
                }
            }

            0 -> println("Da thoat chuong trinh!")
            else -> println("Lua chon khong hop le, vui long chon lai!")
        }
    }
}