package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import bot.*;

class GameTest {

    @Test
    void makeScore_oneRoundFinished() {
        Game game = new Game();
        game.currentRound = new Round(game.Questions);
        game.currentRound.isFinished = true;
        game.currentRound.winner = "user";
        game.makeScore();
        var expected = "Компьютер - 0 : Пользователь - 1";
        var result = game.getScore();
        Assert.assertEquals(expected, result);
    }

    @Test
    void makeScore_oneRoundNotFinished() {
        Game game = new Game();
        game.currentRound = new Round(game.Questions);
        game.currentRound.winner = "user";
        game.makeScore();
        var expected = "Компьютер - 0 : Пользователь - 0";
        var result = game.getScore();
        Assert.assertEquals(expected, result);
    }
}