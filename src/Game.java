import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game {
    public Round currentRound;
    public int roundCount;
    public static String Rules =
            "Для начала игры введите /start.\n" +
                    "Чтобы узнать правила, введите /help.\n" +
                    "Чтобы узнать счёт, введите /score.\n" +
                    "Чтобы начать сначала, введите /again.\n" +
                    "Чтобы выйти из игры, введите /exit";
    public static Animal[] Animals = {
            new Animal("ворон", "черный", "лес", "маленький"),
            new Animal("белка", "оранжевый", "лес", "маленький"),
            new Animal("кит", "синий", "вода", "большой"),
            //new Animal(),
    };
    public static ArrayList<Question> Questions;
    public static HashMap<String, String[]> params = new HashMap<>();
    public String userName;
    public int compScore;
    public int userScore;

    public Game() {
        params.put("color", new String[]{"белый", "черный", "синий", "оранжевый"});
        params.put("area", new String[]{"джунгли", "лес", "пустыня", "вода"});
        params.put("size", new String[]{"большой", "средний", "маленький"});

        Questions = QuestionFactory.makeQuestions(params);
        Collections.shuffle(Questions);
    }

    public void putAnswer(Question question, String answer) {
        currentRound.Answers.put(question, answer);
    }

    public void makeScore() {
        if (currentRound.isFinished) {
            if (currentRound.winner.equals("user")) {
                userScore++;
            } else if (currentRound.winner.equals("computer")) {
                compScore++;
            }
        }
    }

    public void setUserName(String name) {
        userName = name;
        Program.PrintOut(String.format("Игра началась, %s.\nЗагадайте животное.", userName));
    }

    public void updateGame() {
        Questions = QuestionFactory.makeQuestions(params);
        Collections.shuffle(Questions);
        if (currentRound.isFinished) roundCount++;
        currentRound = new Round(Questions);
    }

    public String startGame() {
        if (currentRound != null) {
            return "Чтобы начать новый раунд, введите /again.";
        }
        currentRound = new Round(Questions);
        return "Введите ваше имя.";
    }

    public String repeatGame() throws NoSuchFieldException, IllegalAccessException {
        if (currentRound != null) {
            Program.PrintOut("Что ж, попробуем ещё раз.");
            updateGame();
            return currentRound.play();
        } else {
            return "Вы еще не начали игру. Чтобы начать, введите /start.";
        }
    }

    public String playGame(String command) throws NoSuchFieldException, IllegalAccessException {
        if (userName == null) {
            setUserName(command);
            return currentRound.play();
        } else if (command.equals("да") || command.equals("нет")) {
            putAnswer(currentRound.currentQuestion, command);
            currentRound.askedQuestionCount++;
            var result = currentRound.play();
            if (currentRound.isFinished) {
                makeScore();
            }
            return result;
        }
        return "Я вас не понимаю :(";
    }

    public String getScore() {
        return String.format("Компьютер - %s : Пользователь - %d", compScore, userScore);
    }
}
