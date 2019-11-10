import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void putAnswer() {
        Game game = new Game();
        game.currentRound = new Round(game.Questions);
        var question = new Question("category", "feature");
        game.putAnswer(question, "answer");
        var expected = game.currentRound.Answers.get(question);
        var result = "answer";
        Assert.assertEquals(expected, result);
    }

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