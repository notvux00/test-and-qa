public class PlayerRanker {

    public enum Rank {
        INVALID, RESTRICTED, UNRANKED, C, B, A, S, SS
    }

    /**
     * Calculates the player rank based on various performance metrics.
     *
     * @param score      0 to 1000
     * @param winRate    0 to 100
     * @param matches    >= 0
     * @param winStreak  >= 0
     * @param honorLevel 0 to 5
     * @return The calculated rank as a String.
     */
    public String calculateRank(int score, double winRate, int matches, int winStreak, int honorLevel) {
        // Validation
        if (score < 0 || score > 1000 || winRate < 0 || winRate > 100 || matches < 0 || winStreak < 0 || honorLevel < 0
                || honorLevel > 5) {
            return Rank.INVALID.name();
        }

        if (honorLevel == 0) {
            return Rank.RESTRICTED.name();
        }

        if (matches < 10) {
            return Rank.UNRANKED.name();
        }

        // Base Ranking based on Revised Rules
        Rank baseRank;
        if (score > 950 && winRate >= 80) {
            baseRank = Rank.SS;
        } else if (score >= 800 && winRate >= 80) {
            baseRank = Rank.S;
        } else if (score >= 600 && winRate >= 70) {
            baseRank = Rank.A;
        } else if (score >= 400 && winRate >= 50) {
            baseRank = Rank.B;
        } else {
            baseRank = Rank.C;
        }

        // Adjustments
        int rankIndex = baseRank.ordinal(); // Index in enum (C=3, B=4, A=5, S=6, SS=7)

        // winStreak >= 5: +1
        if (winStreak >= 5) {
            rankIndex++;
        }

        // winRate < 30: -1
        if (winRate < 30) {
            rankIndex--;
        }

        // honorLevel >= 4: +rank (Based on TC20, TC21)
        // Level 3: +0, Level 4: +1, Level 5: +2
        if (honorLevel == 4) {
            rankIndex++;
        } else if (honorLevel == 5) {
            rankIndex += 2;
        }

        // Clamp values within C to SS (index 3 to 7)
        if (rankIndex < Rank.C.ordinal()) {
            rankIndex = Rank.C.ordinal();
        }
        if (rankIndex > Rank.SS.ordinal()) {
            rankIndex = Rank.SS.ordinal();
        }

        return Rank.values()[rankIndex].name();
    }

    public static void main(String[] args) {
        PlayerRanker ranker = new PlayerRanker();

        // Test examples
        System.out.println("Score: 1000, WinRate: 90, Matches: 50, Streak: 0, Honor: 3 -> "
                + ranker.calculateRank(1000, 90, 50, 0, 3)); // Expected: SS
        System.out.println("Score: 500, WinRate: 60, Matches: 20, Streak: 5, Honor: 2 -> "
                + ranker.calculateRank(500, 60, 20, 5, 2)); // Expected: A (Base B + 1)
        System.out.println("Score: 700, WinRate: 20, Matches: 30, Streak: 0, Honor: 5 -> "
                + ranker.calculateRank(700, 20, 30, 0, 5)); // Expected: S (Base A - 1 + 2)
        System.out.println("Honor 0 -> " + ranker.calculateRank(1000, 90, 50, 0, 0)); // Expected: RESTRICTED
        System.out.println("Matches 5 -> " + ranker.calculateRank(1000, 90, 5, 0, 3)); // Expected: UNRANKED
    }
}
