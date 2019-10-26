import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {
    public static int questionCount = 11;
    public static String Rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.\nЧтобы начать сначала, введите /again.\nЧтобы выйти из игры, введите /exit";
    public static Animal[] animals = {
            new Animal("ворон", "черный", "лес", "маленький"),
            new Animal("белка", "оранжевый", "лес", "маленький"),
            new Animal("кит", "синий", "вода", "большой"),
            //new Animal(),
    };
    public static ArrayList<String> Questions;
    private static Random rnd = new Random();
    private static String[] colors = {"белый", "черный", "синий", "оранжевый"};
    private static String[] area = {"джунгли", "лес", "пустыня", "вода"};
    private static String[] size = {"большой", "средний", "маленький"};
    public int compScore;
    public int userScore;
    public boolean isBegan = false;
    public String currentQuestion;
    public int askedQuestions = 0;
    public Animal myAnimal = new Animal("myAnimal", "", "", "");
    private HashMap<String, String> Answers = new HashMap<String, String>();

    public Game() {
        isBegan = true;
        Questions = new ArrayList<String>();
        MakeQuestions();
    }

    private static void MakeQuestions() {
        for (String x : area) {
            Questions.add(String.format("area: Среда обитания этого животного - %s", x));
        }
        for (String x : size) {
            Questions.add(String.format("size: Размер этого животного - %s", x));
        }
        for (String x : colors) {
            Questions.add(String.format("color: Цвет этого животного - %s", x));
        }
    }

    public String GetRandomQuestion() {
        return (String) Questions.get(rnd.nextInt(Questions.toArray().length));
    }

    public void PutAnswer(String question, String answer) {
        Answers.put(question, answer);
    }

    public void MakeAnimal() {
        if (!Answers.isEmpty()) {
            for (String question : Answers.keySet()) {
                if (question != null) {
                    if (Answers.get(question).equals("да")) {
                        String[] temp = question.split(" ");
                        if (question.split(":")[0].equals("area")) {
                            myAnimal.area = temp[temp.length - 1];
                        } else if (question.split(":")[0].equals("color")) {
                            myAnimal.color = temp[temp.length - 1];
                        } else if (question.split(":")[0].equals("size")) {
                            myAnimal.size = temp[temp.length - 1];
                        }
                    }
                }
            }
        }
    }

    public Animal GuessAnimal() {
        MakeAnimal();
        for (Animal animal : animals) {
            if (Animal.equals(animal, myAnimal)) {
                return animal;
            }
        }
        return null;
    }

    public void UpdateGame() {
        Questions = new ArrayList();
        MakeQuestions();
        Answers = new HashMap<String, String>();
        myAnimal = new Animal("myAnimal", "", "", "");
        askedQuestions = 0;
    }

    public String PlayGame() {
        if (askedQuestions < questionCount) {
            String question = GetRandomQuestion();
            currentQuestion = question;
            Animal animal = GuessAnimal();
            if (animal != null) {
                compScore++;
                MyDialog.CommonPhrases.replace("/score", String.format("Компьютер - %s : Пользователь - %d", compScore, userScore));
                Program.PrintOut("Дайте-ка подумать...");
                //return GuessAnimal();
                return String.format("Загаданное животное - %s.", animal.name);
            } else {
                return (currentQuestion.split(": ")[1]);
            }
        }
        Animal animal = GuessAnimal(); //проверка последнего вопроса
        if (animal != null) {
            compScore++;
            return String.format("Загаданное животное - %s.", animal.name);
        }
        userScore++;
        MyDialog.CommonPhrases.replace("/score", String.format("Компьютер - %s : Пользователь - %d", compScore, userScore));
        return "Я не знаю такое животное :(";
    }
}
