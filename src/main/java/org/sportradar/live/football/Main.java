package org.sportradar.live.football;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();
        LocalTime time = LocalTime.now();

        scoreBoard.addMatch("Brazil", "Argentina", time.minusMinutes(90));
        scoreBoard.addMatch("Slovenia", "Germany", time.minusMinutes(30));
        scoreBoard.addMatch("Uruguay", "Italy", time.minusMinutes(10));
        scoreBoard.addMatch("Mexico", "Canada", time.minusMinutes(20));
        scoreBoard.addMatch("France", "England", time.plusMinutes(30));
        scoreBoard.addMatch("BiH", "Austria", time.plusMinutes(10));

        scoreBoard.updateScore("Brazil", "Argentina", 1,0);
        scoreBoard.updateScore("Slovenia", "Germany", 1,3);
        scoreBoard.updateScore("Uruguay", "Italy", 6,6);
        scoreBoard.updateScore("Mexico", "Canada", 1,5);
        scoreBoard.updateScore("BiH", "Austria", 3,3);
        scoreBoard.updateScore("France", "England", 3,2);

        System.out.println("Finish matches");
        scoreBoard.getAndRemoveFinishedMatches()
                .forEach(System.out::println);

        System.out.println("Live or upcoming matches");
        scoreBoard.getSummary()
                .forEach(System.out::println);
    }
}