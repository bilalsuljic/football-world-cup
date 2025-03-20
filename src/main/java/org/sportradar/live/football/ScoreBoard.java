package org.sportradar.live.football;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreBoard {

    private final List<Match> matches;

    public ScoreBoard() {
        this.matches = new ArrayList<>();
    }

    public void addMatch(final String home, final String away, final LocalTime matchTime) {
        matches.add(new Match(home, away, matchTime));
    }

    public void updateScore(final String home, final String away, final int scoreHome, final int scoreAway) {
        for (Match match : matches) {
            if (match.startMatch(LocalTime.now()) &&
                    match.getMatchSummary().startsWith(home + " ") &&
                    match.getMatchSummary().contains(" " + away)) {
                match.updateScore(scoreHome, scoreAway);
            }
        }
    }

    public List<String> getSummary() {
        return matches.stream()
                .sorted(Comparator.comparingInt((Match match) -> match.startMatch(LocalTime.now()) ? 0 : 1)
                        .thenComparingInt(Match::getTotalScore)
                        .thenComparing(Match::getMatchTime))
                .map(Match::getMatchSummary)
                .toList();
    }

    public List<String> getAndRemoveFinishedMatches() {
        List<Match> finishedMatches = matches.stream()
                .filter(match -> match.endMatch(LocalTime.now()))
                .toList();

        matches.removeAll(finishedMatches);

        return finishedMatches.stream()
                .map(Match::getMatchSummary)
                .toList();
    }

}
