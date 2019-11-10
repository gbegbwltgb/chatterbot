import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MyDialogTest {
    @Test
    void again_with_not_started_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.GetReaction("/again");
        var expected = "Вы еще не начали игру. Чтобы начать, введите /start.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void again_with_started_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        var questions = new ArrayList<String>();
        for (Question question : dialog.game.Questions) {
            questions.add(question.makeTextQuestion());
        }
        var result = dialog.GetReaction("/again");
        Assert.assertTrue(result, questions.contains(result));
    }

    @Test
    void start() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.GetReaction("/start");
        var expected = "Введите ваше имя.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void start_with_started_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        var result = dialog.GetReaction("/start");
        var expected = "Чтобы начать новый раунд, введите /again.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void input_name() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        dialog.game.userName = null;
        var input = "kek";
        dialog.GetReaction(input);
        Assert.assertEquals(dialog.game.userName, input);
    }

    @Test
    void input_yes_started_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        dialog.game.userName = "kek";
        var input = "да";
        var result = dialog.GetReaction(input);
        var questions = new ArrayList<String>();
        for (Question question : dialog.game.Questions) {
            questions.add(question.makeTextQuestion());
        }
        Assert.assertTrue(result, questions.contains(result));
    }

    @Test
    void input_yes_finished_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        dialog.game.userName = "kek";
        dialog.game.currentRound.isFinished = true;
        var input = "да";
        var result = dialog.GetReaction(input);
        var expected = "Для следующего раунда введите /again. Для выхода из игры введите /exit.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void random_input_with_started_game() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = new Round(dialog.game.Questions);
        dialog.game.userName = "kek";
        var result = dialog.GetReaction("hhha");
        var expected = "Я вас не понимаю :(";
        Assert.assertEquals(expected, result);
    }

    @Test
    void common_phrase_not_command() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        dialog.game.currentRound = null;
        var result = dialog.GetReaction("hello");
        var expected = "Такой команды не существует...";
        Assert.assertEquals(expected, result);
    }

    @Test
    void help() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        var result = dialog.GetReaction("/help");
        var expected = dialog.game.Rules;
        Assert.assertEquals(expected, result);
    }

    @Test
    void score() throws NoSuchFieldException, IllegalAccessException {
        MyDialog dialog = new MyDialog();
        var result = dialog.GetReaction("/score");
        var expected = String.format("Компьютер - %s : Пользователь - %d", dialog.game.compScore, dialog.game.userScore);
        Assert.assertEquals(expected, result);
    }
}
