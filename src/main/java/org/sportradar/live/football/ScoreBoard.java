package org.sportradar.live.football;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches;

    public ScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public void startMatch(final String home, final String away) {
        matches.add(new Match(home, away));
    }

    public void updateScore(final int scoreHome, final int scoreAway) {
        for (Match match : matches) {
            if (match.startMatch()) {
                match.updateScore(scoreHome, scoreAway);
            }
        }
    }

    public void finishMatch( String home, String away) {
        matches.removeIf(Match::endMatch);
    }

    public List<String> getSummary() {
        return matches.stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparingLong(Match::getMatchTime).reversed())
                .map(Match::getMatchSummary)
                .toList();
    }
}
