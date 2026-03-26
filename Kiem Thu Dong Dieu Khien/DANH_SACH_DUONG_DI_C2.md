# DANH SÁCH TỐI GIẢN 8 ĐƯỜNG ĐI ĐẠT ĐỘ PHỦ NHÁNH (BRANCH COVERAGE)

Đây là bộ đường đi tối ưu nhất để phủ toàn bộ các nhánh True (T) và False (F) trong đồ thị dòng điều khiển.

---

### 1. NHÓM PHỦ LỖI VÀ TRẠNG THÁI ĐẶC BIỆT
1.  **Path 01 (Phủ nhánh 1-T):** 1(T) -> 2 -> 31
2.  **Path 02 (Phủ nhánh 1-F, 3-T):** 1(F) -> 3(T) -> 4 -> 31
3.  **Path 03 (Phủ nhánh 3-F, 5-T):** 1(F) -> 3(F) -> 5(T) -> 6 -> 31

### 2. NHÓM PHỦ TẤT CẢ CÁC NHÁNH XẾP HẠNG VÀ ĐIỀU CHỈNH
4.  **Path 04 (Phủ 5-F, 8-T, 18-T, 20-T, 22-T, 26-F, 28-T):**
    1(F) -> 3(F) -> 5(F) -> 7 -> 8(T) -> 9 -> 17 -> 18(T) -> 19 -> 20(T) -> 21 -> 22(T) -> 23 -> 26(F) -> 28(T) -> 29 -> 30 -> 31

5.  **Path 05 (Phủ 8-F, 10-T, 18-F, 20-F, 22-F, 24-T, 26-F, 28-F):**
    1(F) -> 3(F) -> 5(F) -> 7 -> 8(F) -> 10(T) -> 11 -> 17 -> 18(F) -> 20(F) -> 22(F) -> 24(T) -> 25 -> 26(F) -> 28(F) -> 30 -> 31

6.  **Path 06 (Phủ 10-F, 12-T, 24-F):**
    1(F) -> 3(F) -> 5(F) -> 7 -> 8(F) -> 10(F) -> 12(T) -> 13 -> 17 -> 18(F) -> 20(F) -> 22(F) -> 24(F) -> 26(F) -> 28(F) -> 30 -> 31

7.  **Path 07 (Phủ 12-F, 14-T):**
    1(F) -> 3(F) -> 5(F) -> 7 -> 8(F) -> 10(F) -> 12(F) -> 14(T) -> 15 -> 17 -> 18(F) -> 20(F) -> 22(F) -> 24(F) -> 26(F) -> 28(F) -> 30 -> 31

8.  **Path 08 (Phủ 14-F, 20-T, 26-T):**
    1(F) -> 3(F) -> 5(F) -> 7 -> 8(F) -> 10(F) -> 12(F) -> 14(F) -> 16 -> 17 -> 18(F) -> 20(T) -> 21 -> 22(F) -> 24(F) -> 26(T) -> 27 -> 28(F) -> 30 -> 31

---

### BẢNG ĐỐI SOÁT ĐỘ PHỦ (DÀNH CHO BÁO CÁO)

| Nút quyết định | Nhánh Path đi qua (T) | Nhánh Path đi qua (F) |
| :--- | :--- | :--- |
| **Nút 1 (Validation)** | Path 01 | Path 02 |
| **Nút 3 (HonorLevel == 0)** | Path 02 | Path 03 |
| **Nút 5 (Matches < 10)** | Path 03 | Path 04 |
| **Nút 8 (Rank SS?)** | Path 04 | Path 05 |
| **Nút 10 (Rank S?)** | Path 05 | Path 06 |
| **Nút 12 (Rank A?)** | Path 06 | Path 07 |
| **Nút 14 (Rank B?)** | Path 07 | Path 08 |
| **Nút 18 (WinStreak >= 5)** | Path 04 | Path 05 |
| **Nút 20 (WinRate < 30)** | Path 08 | Path 04 |
| **Nút 22 (HonorLevel == 4)** | Path 04 | Path 05 |
| **Nút 24 (HonorLevel == 5)** | Path 05 | Path 06 |
| **Nút 26 (Kẹp dưới hạng C)** | Path 08 | Path 04 |
| **Nút 28 (Kẹp trên hạng SS)** | Path 04 | Path 05 |

---

### BẢNG TEST CASES CHI TIẾT (DÀNH CHO LẬP TRÌNH KIỂM THỬ)
*Đây là bộ dữ liệu khớp chuẩn với 8 đường đi nêu trên.*

| TC ID | Path | Bộ giá trị (score, winRate, matches, winStreak, honorLevel) | KQ Mong Đợi (Expected) | Ghi chú kỹ thuật |
| :--- | :--- | :--- | :--- | :--- |
| **TC01** | Path 01 | **(-1, 50, 20, 0, 3)** | **INVALID** | Phủ 1(T): Lỗi score âm |
| **TC02** | Path 02 | **(900, 85, 20, 0, 0)** | **RESTRICTED** | Phủ 3(T): Honor Level 0 |
| **TC03** | Path 03 | **(950, 85, 5, 0, 3)** | **UNRANKED** | Phủ 5(T): Chưa đủ 10 trận |
| **TC04** | Path 04 | **(960, 90, 30, 10, 4)** | **SS** | Phủ 8(T), 18(T), 22(T), 28(T) |
| **TC05** | Path 05 | **(850, 85, 20, 0, 5)** | **SS** | Phủ 10(T), 24(T), 18(F), 20(F) |
| **TC06** | Path 06 | **(700, 75, 20, 0, 3)** | **A** | Phủ 12(T), 24(F) |
| **TC07** | Path 07 | **(500, 60, 20, 0, 3)** | **B** | Phủ 14(T) |
| **TC08** | Path 08 | **(300, 15, 20, 0, 3)** | **C** | Phủ 14(F), 20(T), 26(T) |
