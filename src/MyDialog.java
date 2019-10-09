import java.util.HashMap;
import java.util.Scanner;

public class MyDialog {
    public static String name;
    public static Game game;

    public static String rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.";
    public static HashMap<String, String> CommonPhrases = new HashMap<String, String>();
    public static Scanner newscan= new Scanner(System.in);//.useDelimiter(" ");

    public MyDialog(){

        CommonPhrases.put("greeting", "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное." );
        CommonPhrases.put("/start", "Введите ваше имя.");
        CommonPhrases.put("/help", rules);
        CommonPhrases.put("/score", String.format("Компьютер - %s : Пользователь - %d", 0, 0));

        System.out.println(CommonPhrases.get("greeting"));
        System.out.println(CommonPhrases.get("/help"));
    }

    public static void GetReaction(String command){
        if (command.equals("/start")){
            System.out.println(CommonPhrases.get("/start"));
            name = newscan.next();
            StartGame(name);
        }
        else if (CommonPhrases.containsKey(command)){
            System.out.println(CommonPhrases.get(command));
        }
        else if (game!=null){
            //if
        }
        else {//подавать слово в какой-то метод из класса игры
            System.out.println("Такой команды не существует...");
        }
    }

    public static void StartGame(String name){
        game = new Game(name);
        for (var i = 0; i<12; i++){
            String question = game.GetRandomQuestion();
            System.out.println(question);
            String answer = newscan.next();
            game.PutAnswer(question, answer);
        }
        System.out.println("Дайте-ка подумать...");
        //System.out.print(game.Answers);
        System.out.println(game.GuessAnimal());
    }
}
