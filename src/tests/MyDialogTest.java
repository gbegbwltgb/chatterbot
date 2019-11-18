package tests;

import bot.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class MyDialogTest {
    @Test
    void again_with_not_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.getReaction("/again");
        var expected = "Вы еще не начали игру. Чтобы начать, введите /start.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void again_with_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        var questions = new ArrayList<String>();
        for (Question question : dialog.game.Questions) {
            questions.add(QuestionFactory.makeTextQuestion(question.category, question.feature));
        }

        var result = dialog.getReaction("/again").split("\n")[1];
        Assert.assertTrue(result, questions.contains(result));
    }

    @Test
    void start() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.getReaction("/start");
        var expected = "Введите ваше имя.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void start_with_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(Game.Questions);
        var result = dialog.getReaction("/start");
        var expected = "Чтобы начать новый раунд, введите /again.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void input_name() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(Game.Questions);
        dialog.game.userName = null;
        var input = "kek";
        dialog.getReaction(input);
        Assert.assertEquals(dialog.game.userName, input);
    }

    @Test
    void input_yes_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(Game.Questions);
        dialog.game.userName = "kek";
        var input = "да";
        var result = dialog.getReaction(input);
        var questions = new ArrayList<String>();
        for (Question question : dialog.game.Questions) {
            questions.add(QuestionFactory.makeTextQuestion(question.category, question.feature));
        }
        Assert.assertTrue(result, questions.contains(result));
    }

    @Test
    void input_yes_finished_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(Game.Questions);
        dialog.game.userName = "kek";
        dialog.game.currentRound.isFinished = true;
        var input = "да";
        var result = dialog.getReaction(input);
        var expected = "Для следующего раунда введите /again. Для выхода из игры введите /exit.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void random_input_with_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(Game.Questions);
        dialog.game.userName = "kek";
        var result = dialog.getReaction("hhha");
        var expected = "Я вас не понимаю :(";
        Assert.assertEquals(expected, result);
    }

    @Test
    void common_phrase_not_command() {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.getReaction("hello");
        var expected = "Такой команды не существует...";
        Assert.assertEquals(expected, result);
    }

    @Test
    void help() {
        MyDialog dialog = new MyDialog();
        var result = dialog.getReaction("/help");
        var expected = Game.Rules;
        Assert.assertEquals(expected, result);
    }

    @Test
    void score() {
        MyDialog dialog = new MyDialog();
        var result = dialog.getReaction("/score");
        var expected = String.format("Компьютер - %s : Пользователь - %d", dialog.game.compScore, dialog.game.userScore);
        Assert.assertEquals(expected, result);
    }
}
