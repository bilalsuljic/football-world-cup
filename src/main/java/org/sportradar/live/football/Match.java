package org.sportradar.live.football;

import java.time.Duration;
import java.time.LocalTime;

public class Match {

    private final String homeTeam;
    private final String awayTeam;
    private int scoreHome;
    private int scoreAway;
    private final LocalTime matchTime;

    public Match(String homeTeam, String awayTeam, LocalTime matchTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.scoreHome = 0;
        this.scoreAway = 0;
        this.matchTime = matchTime;
    }

    protected void updateScore(int scoreHome, int scoreAway) {
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    protected boolean startMatch(LocalTime currentTime) {
        if (matchTime == null) {
            return false;
        }
        return currentTime.isAfter(matchTime) && !endMatch(currentTime);
    }

    // Check if the match has ended (after 90 minutes)
    protected boolean endMatch(LocalTime currentTime) {
        if (currentTime == null || matchTime == null) {
            return false;
        }
        Duration matchDuration = Duration.between(matchTime, currentTime);
        return matchDuration.toMinutes() >= 90;
    }

    protected String getMatchSummary() {
        LocalTime currentTime = LocalTime.now();

        if (startMatch(currentTime)) {
            return homeTeam + " " + scoreHome + " - " + awayTeam + " " + scoreAway;
        } else if (endMatch(currentTime)) {
            return "End match: " + homeTeam + " " + scoreHome + " - " + awayTeam + " " + scoreAway;
        } else {
            long durationMatch = Duration.between(currentTime, matchTime).toMinutes();
            return "Upcoming match: " + homeTeam + " - " + awayTeam + " | Starts in: " + durationMatch + " minutes.";
        }
    }

    protected int getTotalScore() {
        return scoreHome + scoreAway;
    }

    protected LocalTime getMatchTime() {
        return matchTime;
    }
}
