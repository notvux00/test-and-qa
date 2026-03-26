// No import needed for classes in default package if compiled together

public class TestRunner {
    public static void main(String[] args) {
        PlayerRanker ranker = new PlayerRanker();
        int passed = 0;
        int failed = 0;

        System.out.println("========== KẾT QUẢ KIỂM THỬ ĐỘ PHỦ NHÁNH (8 CASES) ==========");
        System.out.printf("%-10s | %-15s | %-12s | %-12s | %-10s\n", "ID", "EXPECTED", "ACTUAL", "RESULT", "NOTES");
        System.out.println("--------------------------------------------------------------------------------");

        // Định nghĩa 8 test cases
        Object[][] testCases = {
            {"TC01", "(-1, 50, 20, 0, 3)", "INVALID", -1, 50.0, 20, 0, 3},
            {"TC02", "(900, 85, 20, 0, 0)", "RESTRICTED", 900, 85.0, 20, 0, 0},
            {"TC03", "(950, 85, 5, 0, 3)", "UNRANKED", 950, 85.0, 5, 0, 3},
            {"TC04", "(960, 90, 30, 10, 4)", "SS", 960, 90.0, 30, 10, 4},
            {"TC05", "(850, 85, 20, 0, 5)", "SS", 850, 85.0, 20, 0, 5},
            {"TC06", "(700, 75, 20, 0, 3)", "A", 700, 75.0, 20, 0, 3},
            {"TC07", "(500, 60, 20, 0, 3)", "B", 500, 60.0, 20, 0, 3},
            {"TC08", "(300, 15, 20, 0, 3)", "C", 300, 15.0, 20, 0, 3}
        };

        for (Object[] tc : testCases) {
            String id = (String) tc[0];
            String expected = (String) tc[2];
            String actual = ranker.calculateRank((int)tc[3], (double)tc[4], (int)tc[5], (int)tc[6], (int)tc[7]);
            
            String result = expected.equals(actual) ? "PASS" : "FAIL";
            if (result.equals("PASS")) passed++; else failed++;

            System.out.printf("%-10s | %-15s | %-12s | %-12s | %-10s\n", id, expected, actual, result, tc[1]);
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("TỔNG CỘNG: " + (passed + failed) + " | THÀNH CÔNG: " + passed + " | THẤT BẠI: " + failed);
        System.out.println("===============================================================");
    }
}
