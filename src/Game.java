import java.util.*;

public class Game { //на вход подается животное с набором качеств и в зависимости от этого составляется словарь вопросов и ответов
    public static int compScore;
    public static int userScore;
    public static String userName;
    public static boolean isBegan = false;
    public static Animal[] animals = {
        new Animal("ворон", "черный", "лес", "маленький"),
        new Animal("белка", "оранжевый", "лес", "маленький"),
        new Animal("кит", "синий", "вода", "огромный"),
        //new Animal(),
    };
    public static Animal myAnimal = new Animal("myAnimal","", "", "");
    public Random rnd = new Random();
    static String[] colors = {
            "белый",
            "черный",
            "синий",
            "оранжевый"};
    static String[] area = {
            "джунгли",
            "лес",
            "пустыня",
            "вода"};

    static String[] size = {
            "огромный",
            "большой",
            "средний",
            "маленький"};

    public Game(String name){
        userName = name;
        isBegan = true;
        for (String x : area) {
            Questions.add(String.format("area: Среда обитания этого животного - %s", x));
        }
        for (String x : size) {
            Questions.add(String.format("size: Размер этого животного - %s", x));
        }
        for (String x : colors) {
            Questions.add(String.format("color: Цвет этого животного - %s", x));
        }
        System.out.printf("Игра началась, %s.....\nЗагадайте животное.\n", userName);
    }
    /*final HashMap<String, String> AllQuestions = new HashMap<String, String>() {{
        for (String x : area) {
            if (x.equals(animal.area)) {
                put(String.format("\nСреда обитания этого животного - %s?", x), "да");
            } else {
                put(String.format("\nСреда обитания этого животного - %s?", x), "нет");
            }
        }
        for (String x : size) {
            if (x.equals(animal.size)) {
                put(String.format("\nРазмер этого животного - %s?", x), "да");
            } else {
                put(String.format("\nРазмер этого животного - %s?", x), "нет");
            }
        }
        for (String x : colors) {
            if (x.equals(animal.color)) {
                put(String.format("\nЦвет этого животного - %s?", x), "да");
            } else {
                put(String.format("\nЦвет этого животного - %s?", x), "нет");
            }
        }
    }};*/
    public static ArrayList Questions = new ArrayList();
    /*public String[] GetRandomQuestions() {
        String[] questions = new String[3];
        Map<String, String> list = AllQuestions;
        questions[0] = "1) " + QuestionsForColors.keySet().toArray()[rnd.nextInt(4)].toString();
        questions[1] = "2) " + QuestionsForArea.keySet().toArray()[rnd.nextInt(4)].toString();
        questions[2] = "3) " + QuestionsForSize.keySet().toArray()[rnd.nextInt(4)].toString();
        for (int i=0; i < questions.length; i++){
            String question = AllQuestions.keySet().toArray()[rnd.nextInt(list.size())].toString();
            questions[i] = (i+1) +") " + question;
            list.remove(question);
            RandomQuestions[i] = question;
            userQuestions.put(question, AllQuestions.get(question));
        }
        RandomQuestions = questions;
        return questions;
    }*/
    public static HashMap<String, String> Answers = new HashMap<String, String>();

    public String GetRandomQuestion() {
        //return AllQuestions.keySet().toArray()[rnd.nextInt(12)].toString();
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
        //System.out.print(myAnimal.area);
        //System.out.print(myAnimal.color);
        //System.out.print(myAnimal.size);

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

