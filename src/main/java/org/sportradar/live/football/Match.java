package org.sportradar.live.football;

import java.time.Duration;
import java.time.LocalTime;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int scoreHome;
    private int scoreAway;
    private final boolean endMatch;
    private final boolean startMatch;
    private final LocalTime matchTime = LocalTime.of(16, 0);;
    private final LocalTime currentTime = LocalTime.now();
    private final Duration durationMatch = Duration.between(matchTime, currentTime);

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scoreHome = 0;
        this.scoreAway = 0;
        this.startMatch = startMatch();
        this.endMatch = endMatch();
    }

    protected void updateScore(int scoreHome, int scoreAway) {
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    protected boolean endMatch() {
        return startMatch && durationMatch.toMinutes() >= 1;
    }

    protected boolean startMatch() {
        return currentTime.isAfter(matchTime);
    }

    protected String getMatchSummary() {
        if (endMatch) {
            return homeTeam + " " + scoreHome + " - " + awayTeam + " " + scoreAway;
        }
        return "Match does not finish yet";
    }

    protected int getTotalScore() {
        return scoreHome + scoreAway;
    }

    protected long getMatchTime() {
        return matchTime.getHour();
    }
}
