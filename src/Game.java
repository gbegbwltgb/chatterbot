import java.util.*;

public class Game {
    public static int compScore;
    public static int userScore;
    public static boolean isBegan = false;
    public static String currentQuestion = "";
    public static int questionCount = 11;
    public static int askedQuestions = 0;
    public static String Rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.\nЧтобы начать сначала, введите /again.\nЧтобы выйти из игры, введите /exit";
    public static Animal[] animals = {
        new Animal("ворон", "черный", "лес", "маленький"),
        new Animal("белка", "оранжевый", "лес", "маленький"),
        new Animal("кит", "синий", "вода", "большой"),
        //new Animal(),
    };
    public static Animal myAnimal = new Animal("myAnimal","", "", "");
    private static Random rnd = new Random();
    static String[] colors = {"белый", "черный", "синий", "оранжевый"};
    static String[] area = {"джунгли", "лес", "пустыня", "вода"};
    static String[] size = {"большой", "средний", "маленький"};
    public static ArrayList Questions;
    public static HashMap<String, String> Answers = new HashMap<String, String>();

    public Game(){
        isBegan = true;
        Questions = new ArrayList();
        MakeQuestions();
    }

    private static void MakeQuestions(){
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

    public int getCompScore(){
        return compScore;
    }

    public int getUserScore(){
        return userScore;
    }

    public static String GetRandomQuestion() {
        return (String) Questions.get(rnd.nextInt(Questions.toArray().length));
    }

    public void PutAnswer(String question, String answer){
        Answers.put(question, answer);
    }

    public static void MakeAnimal(){
        for (String question : Answers.keySet()) {
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

    public static String GuessAnimal(){
        for (Animal animal : animals){
            if (Animal.equals(animal, myAnimal)){
                return String.format("Загаданное животное - %s.", animal.name);
            }
        }
        return "Я не знаю такое животное :(";
    }

    public void UpdateGame(){
        Questions = new ArrayList();
        MakeQuestions();
        Answers = new HashMap<String, String>();
        myAnimal = new Animal("myAnimal","", "", "");
        askedQuestions = 0;
    }

    public static String PlayGame() {
        if (askedQuestions < questionCount) {
            String question = GetRandomQuestion();
            currentQuestion = question;
            MakeAnimal();

            if (!GuessAnimal().equals("Я не знаю такое животное :(")) {
                compScore++;
                MyDialog.CommonPhrases.replace("/score", String.format("Компьютер - %s : Пользователь - %d", compScore, userScore));
                Program.PrintOut("Дайте-ка подумать...");
                return GuessAnimal();
            } else {
                return (currentQuestion.split(": ")[1]);
            }
        } else {
            userScore++;
        }
        MyDialog.CommonPhrases.replace("/score", String.format("Компьютер - %s : Пользователь - %d", compScore, userScore));
        return (GuessAnimal());
    }
}
