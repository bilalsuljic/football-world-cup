package org.sportradar.live.football;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        LocalTime time = LocalTime.now();

        scoreBoard.addMatch("Brazil", "Argentina", time.minusMinutes(30));
        scoreBoard.addMatch("France", "England", time.plusMinutes(30));
        scoreBoard.addMatch("BiH", "Austria", time.plusMinutes(10));

        scoreBoard.updateScore("Brazil", "Argentina", 1,0);
        scoreBoard.updateScore("BiH", "Austria", 3,3);
        scoreBoard.updateScore("France", "England", 3,2);

        // finished matches
        scoreBoard.getAndRemoveFinishedMatches()
                .forEach(System.out::println);

        scoreBoard.getSummary()
                .forEach(System.out::println);
    }
}