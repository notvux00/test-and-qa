import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerRankerTest {

    private final PlayerRanker ranker = new PlayerRanker();

    @Test
    public void testTC01_InvalidScore() {
        // Path 01: 1(T) -> 2 -> 31
        assertEquals("INVALID", ranker.calculateRank(-1, 50, 20, 0, 3));
    }

    @Test
    public void testTC02_HonorLevel0() {
        // Path 02: 1(F) -> 3(T) -> 4 -> 31
        assertEquals("RESTRICTED", ranker.calculateRank(900, 85, 20, 0, 0));
    }

    @Test
    public void testTC03_LowMatches() {
        // Path 03: 1(F) -> 3(F) -> 5(T) -> 6 -> 31
        assertEquals("UNRANKED", ranker.calculateRank(950, 85, 5, 0, 3));
    }

    @Test
    public void testTC04_RankSS_WithMaxBonus() {
        // Path 04: 1(F) -> 3(F) -> 5(F) -> ... -> 8(T) -> 18(T) -> 22(T) -> 28(T)
        // Base SS (7) + Streak (+1) + Honor 4 (+1) = 9 -> Clamp to SS (7)
        assertEquals("SS", ranker.calculateRank(960, 90, 30, 10, 4));
    }

    @Test
    public void testTC05_RankS_WithHonor5() {
        // Path 05: 1(F) -> 3(F) -> 5(F) -> ... -> 10(T) -> 18(F) -> 20(F) -> 24(T)
        // Base S (6) + Honor 5 (+2) = 8 -> Clamp to SS (7)
        assertEquals("SS", ranker.calculateRank(850, 85, 20, 0, 5));
    }

    @Test
    public void testTC06_RankA_Stable() {
        // Path 06: 1(F) -> 3(F) -> 5(F) -> ... -> 12(T) -> 24(F)
        assertEquals("A", ranker.calculateRank(700, 75, 20, 0, 3));
    }

    @Test
    public void testTC07_RankB() {
        // Path 07: 1(F) -> 3(F) -> 5(F) -> ... -> 14(T)
        assertEquals("B", ranker.calculateRank(500, 60, 20, 0, 3));
    }

    @Test
    public void testTC08_RankC_WithPenalties() {
        // Path 08: 1(F) -> 3(F) -> 5(F) -> ... -> 14(F) -> 20(T) -> 26(T)
        // Base C (3) + Low WinRate (-1) = 2 -> Clamp to C (3)
        assertEquals("C", ranker.calculateRank(300, 15, 20, 0, 3));
    }
}
