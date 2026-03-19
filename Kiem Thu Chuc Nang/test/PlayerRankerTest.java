public class PlayerRankerTest {

    public static void main(String[] args) {
        PlayerRanker ranker = new PlayerRanker();
        int passed = 0;
        int total = 0;

        System.out.println("=== Set 1: Boundary Value Analysis (BVA) ===");
        // (ID, score, winRate, matches, winStreak, honorLevel, expected)
        total++; if (test(ranker, "BVA_TC1", 500, 50, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC2", 0, 50, 50, 3, 3, "C")) passed++;
        total++; if (test(ranker, "BVA_TC3", 1, 50, 50, 3, 3, "C")) passed++;
        total++; if (test(ranker, "BVA_TC4", 999, 50, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC5", 1000, 50, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC6", 500, 0, 50, 3, 3, "C")) passed++;
        total++; if (test(ranker, "BVA_TC7", 500, 1, 50, 3, 3, "C")) passed++;
        total++; if (test(ranker, "BVA_TC8", 500, 99, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC9", 500, 100, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC10", 500, 50, 0, 3, 3, "UNRANKED")) passed++;
        total++; if (test(ranker, "BVA_TC11", 500, 50, 1, 3, 3, "UNRANKED")) passed++;
        total++; if (test(ranker, "BVA_TC12", 500, 50, 99, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC13", 500, 50, 100, 3, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC14", 500, 50, 50, 0, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC15", 500, 50, 50, 1, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC16", 500, 50, 50, 4, 3, "B")) passed++;
        total++; if (test(ranker, "BVA_TC17", 500, 50, 50, 5, 3, "A")) passed++;
        total++; if (test(ranker, "BVA_TC18", 500, 50, 50, 3, 0, "RESTRICTED")) passed++;
        total++; if (test(ranker, "BVA_TC19", 500, 50, 50, 3, 1, "B")) passed++;
        total++; if (test(ranker, "BVA_TC20", 500, 50, 50, 3, 4, "A")) passed++;
        total++; if (test(ranker, "BVA_TC21", 500, 50, 50, 3, 5, "S")) passed++;
        total++; if (test(ranker, "BVA_TC22", -1, 50, 50, 3, 5, "INVALID")) passed++;

        System.out.println("\n=== Set 2: Decision Table (DT) ===");
        total++; if (test(ranker, "DT_TC1", -1, 50, 50, 3, 3, "INVALID")) passed++;
        total++; if (test(ranker, "DT_TC2", 500, 50, 50, 3, 0, "RESTRICTED")) passed++;
        total++; if (test(ranker, "DT_TC3", 500, 50, 5, 3, 3, "UNRANKED")) passed++;
        total++; if (test(ranker, "DT_TC4", 960, 90, 50, 3, 3, "SS")) passed++;
        total++; if (test(ranker, "DT_TC5", 820, 85, 50, 3, 3, "S")) passed++;
        total++; if (test(ranker, "DT_TC6", 650, 75, 50, 3, 3, "A")) passed++;
        total++; if (test(ranker, "DT_TC7", 500, 55, 50, 3, 3, "B")) passed++;
        total++; if (test(ranker, "DT_TC8", 500, 55, 50, 5, 4, "S")) passed++;
        total++; if (test(ranker, "DT_TC9", 500, 25, 50, 3, 1, "C")) passed++;

        System.out.println("\nOverall Tests Summary: " + passed + "/" + total + " passed.");
    }

    private static boolean test(PlayerRanker ranker, String id, int score, double winRate, int matches, int winStreak, int honorLevel, String expected) {
        String result = ranker.calculateRank(score, winRate, matches, winStreak, honorLevel);
        if (result.equals(expected)) {
            System.out.println("[PASS] " + id + ": (" + score + ", " + winRate + ", " + matches + ", " + winStreak + ", " + honorLevel + ") -> Expected: " + expected + ", Got: " + result);
            return true;
        } else {
            System.out.println("[FAIL] " + id + ": (" + score + ", " + winRate + ", " + matches + ", " + winStreak + ", " + honorLevel + ") -> Expected: " + expected + ", Got: " + result);
            return false;
        }
    }
}
