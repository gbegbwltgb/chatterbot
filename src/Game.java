import java.util.*;

public class Game { //на вход подается животное с набором качеств и в зависимости от этого составляется словарь вопросов и ответов
    public static int compScore;
    public static int userScore;
    public static boolean isBegan = false;
    public String currentQuestion = "";
    public static String Rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.\nЧтобы выйти из игры, введите /quit";
    public static Animal[] animals = {
        new Animal("ворон", "черный", "лес", "маленький"),
        new Animal("белка", "оранжевый", "лес", "маленький"),
        new Animal("кит", "синий", "вода", "огромный"),
        //new Animal(),
    };
    public static Animal myAnimal = new Animal("myAnimal","", "", "");
    public Random rnd = new Random();
    static String[] colors = {"белый", "черный", "синий", "оранжевый"};
    static String[] area = {"джунгли", "лес", "пустыня", "вода"};
    static String[] size = {"огромный", "большой", "средний", "маленький"};
    public ArrayList Questions;
    public static HashMap<String, String> Answers = new HashMap<String, String>();

    public Game(){
        isBegan = true;
        Questions = new ArrayList();
        MakeQuestions();
    }

    public void MakeQuestions(){
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

    public String GetRandomQuestion() {
        return (String) Questions.remove(rnd.nextInt(Questions.toArray().length));
    }

    public static void PutAnswer(String question, String answer){
        Answers.put(question, answer);
    }

    public static String GuessAnimal(){
        for (String question : Answers.keySet()){
            if (Answers.get(question).equals("yes")){
                String[] temp = question.split(" ");
                if (question.split(":")[0].equals("area")){
                    myAnimal.area = temp[temp.length - 1];
                }
                else if (question.split(":")[0].equals("color")){
                    myAnimal.color = temp[temp.length - 1];
                }
                else if (question.split(":")[0].equals("size")){
                    myAnimal.size = temp[temp.length - 1];
                }
            }
        }

        for (Animal animal : animals){
            if (Animal.equals(animal, myAnimal)){
                compScore++;
                return String.format("Загаданное животное - %s.", animal.name);
            }
        }
        userScore++;
        return "Я не знаю такое животное :(";
    }
}
