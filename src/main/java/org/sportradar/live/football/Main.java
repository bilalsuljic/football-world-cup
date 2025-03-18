package org.sportradar.live.football;

public class Main {
    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch("Brazil", "Argentina");
        scoreBoard.updateScore(1,0);
        scoreBoard.getSummary()
                .forEach(System.out::println);
    }
}