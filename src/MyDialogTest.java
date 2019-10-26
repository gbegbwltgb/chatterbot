import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MyDialogTest {
    @Test
    void again_with_not_started_game(){
        MyDialog dialog = new MyDialog();
        dialog.game = null;
        var result = dialog.GetReaction("/again");
        var expected = "Вы еще не начали игру. Чтобы начать, введите /start.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void again_with_started_game(){
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        var result = dialog.GetReaction("/again");
        Assert.assertTrue(result, dialog.isQuestion(result));
    }

    @Test
    void start() {
        MyDialog dialog = new MyDialog();
        dialog.game= null;
        var result = dialog.GetReaction("/start");
        var expected = "Введите ваше имя.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void start_with_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        var result = dialog.GetReaction("/start");
        var expected = "Чтобы начать новый раунд, введите /again.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void input_name() {
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        dialog.name = null;
        var input = "kek";
        dialog.GetReaction(input);
        Assert.assertEquals(dialog.name, input);
    }

    @Test
    void input_yes_started_game() {
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        dialog.name = "kek";
        dialog.game.askedQuestions = 0;
        var input = "да";
        var result = dialog.GetReaction(input);
        //var expected = "Я вас не понимаю :(";
        Assert.assertTrue(result, dialog.isQuestion(result));
    }

    @Test
    void input_yes_finished_game() {
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        dialog.name = "kek";
        dialog.game.askedQuestions = 11;
        var input = "да";
        var result = dialog.GetReaction(input);
        var expected = "Для следующего раунда введите /again. Для выхода из игры введите /exit.";
        Assert.assertEquals(expected, result);
    }

    @Test
    void random_input_with_started_game(){
        MyDialog dialog = new MyDialog();
        dialog.game = new Game();
        dialog.name = "kek";
        var result = dialog.GetReaction("hhha");
        var expected = "Я вас не понимаю :(";
        Assert.assertEquals(expected, result);
    }

    @Test
    void common_phrase_not_command(){
        MyDialog dialog = new MyDialog();
        dialog.game = null;
        var result = dialog.GetReaction("greeting");
        var expected = "Такой команды не существует...";
        Assert.assertEquals(expected, result);
    }

    @Test
    void help(){
        MyDialog dialog = new MyDialog();
        var result = dialog.GetReaction("/help");
        var expected = dialog.CommonPhrases.get("/help");
        Assert.assertEquals(expected, result);
    }

    @Test
    void score(){
        MyDialog dialog = new MyDialog();
        var result = dialog.GetReaction("/score");
        var expected = String.format("Компьютер - %s : Пользователь - %d", dialog.game.compScore, dialog.game.userScore);
        Assert.assertEquals(expected, result);
    }
}
