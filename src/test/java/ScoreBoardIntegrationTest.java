import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sportradar.live.football.ScoreBoard;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreBoardIntegrationTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
        LocalTime currentTime = LocalTime.now();

        scoreBoard.addMatch("Brazil", "Argentina", currentTime.minusMinutes(90)); // past match
        scoreBoard.addMatch("France", "England", currentTime.plusMinutes(30));    // future match
        scoreBoard.addMatch("BiH", "Austria", currentTime.plusMinutes(10));       // upcoming match
    }

    @Test
    void testIntegration() {
        // Step 1: Update scores
        scoreBoard.updateScore("Brazil", "Argentina", 1, 0);
        scoreBoard.updateScore("BiH", "Austria", 3, 3);
        scoreBoard.updateScore("France", "England", 3, 2);

        // Step 2: Get and remove finished matches
        List<String> finishedMatches = scoreBoard.getAndRemoveFinishedMatches();

        // Step 3: Check finished matches
        assertEquals(1, finishedMatches.size(), "There should be one finished match");
        assertTrue(finishedMatches.get(0).contains("Brazil"), "Finished match should be Brazil vs Argentina");

        // Step 4: Get summary of remaining (ongoing and upcoming) matches
        List<String> summary = scoreBoard.getSummary();

        // Check if remaining matches are correct
        assertEquals(2, summary.size(), "There should be two matches left");

        // The upcoming match should appear first
        assertTrue(summary.get(0).contains("BiH"), "Upcoming match should be BiH vs Austria");

        // The future matc should appear second
        assertTrue(summary.get(1).contains("France"), "The future match should be France vs England");
    }
}
