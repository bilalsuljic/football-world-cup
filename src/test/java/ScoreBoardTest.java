import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sportradar.live.football.ScoreBoard;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        LocalTime currentTime = LocalTime.now();
        scoreBoard = new ScoreBoard();
        scoreBoard.addMatch("Brazil", "Argentina", currentTime.minusMinutes(10));
        scoreBoard.addMatch("Austria", "Germany", currentTime.minusMinutes(90));
    }

    @Test
    void testAddMatch() {
        List<String> summary = scoreBoard.getSummary();

        assertEquals(2, summary.size());
        assertTrue(summary.get(0).contains("Brazil"));
        assertTrue(summary.get(1).contains("Austria"));
    }

    @Test
    void testUpdateScore() {
        scoreBoard.updateScore("Brazil", "Argentina", 3, 1);

        List<String> summary = scoreBoard.getSummary();
        assertEquals(2, summary.size());
        assertTrue(summary.get(0).contains("Brazil 3 - Argentina 1"));
    }

    @Test
    void testGetSummarySortingByScoreAndTime() {
        scoreBoard.updateScore("Brazil", "Argentina", 2, 1);
        scoreBoard.updateScore("Austria", "Germany", 3, 2);

        List<String> summary = scoreBoard.getSummary();

        assertEquals(2, summary.size());
        assertTrue(summary.get(0).contains("Argentina"));
        assertTrue(summary.get(1).startsWith("End match:"));
    }

    @Test
    void testGetAndRemoveFinishedMatches() {
        scoreBoard.updateScore("Brazil", "Argentina", 2, 1);
        scoreBoard.updateScore("Austria", "Germany", 3, 2);

        List<String> finishedMatches = scoreBoard.getAndRemoveFinishedMatches();

        assertEquals(1, finishedMatches.size());
        assertTrue(finishedMatches.get(0).startsWith("End match:"));

        List<String> summary = scoreBoard.getAndRemoveFinishedMatches();
        assertTrue(summary.isEmpty());
    }
}
