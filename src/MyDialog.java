import java.util.HashMap;
import java.util.Scanner;

public class MyDialog {
    public static String name;
    public static Game game = new Game();
    //public static String rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.";
    public static HashMap<String, String> CommonPhrases = new HashMap<String, String>();
    public static Scanner newscan= new Scanner(System.in);//.useDelimiter(" ");

    public MyDialog(){

        CommonPhrases.put("greeting", "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное." );
        CommonPhrases.put("/start", "Введите ваше имя.");
        CommonPhrases.put("/help", Game.Rules);
        CommonPhrases.put("/score", String.format("Компьютер - %s : Пользователь - %d", game.getCompScore(), game.getUserScore()));

        PrintOut(CommonPhrases.get("greeting"));
        PrintOut(CommonPhrases.get("/help"));
    }

    public static void GetReaction(String command){
        if (command.equals("/start")){
            PrintOut(CommonPhrases.get("/start"));
            name = Read();
            PlayGame(name);
        }
        else if (command.equals("/quit")){
            PrintOut("До свидания.");
            System.exit(0);
        }
        else if (CommonPhrases.containsKey(command)){
            PrintOut(CommonPhrases.get(command));
        }
        else if (game!=null){
            //if (command.equals("yes")){ game.Questions.remove(game.currentQuestion);}            }
            if (!command.equals("yes")&&!command.equals("no")){
                PrintOut("Я вас не понимаю :(");
            }
            game.PutAnswer(game.currentQuestion, command);
        }
        else {//подавать слово в какой-то метод из класса игры
            PrintOut("Такой команды не существует...");
        }
    }

    public static void PlayGame(String name) {
        game = new Game();
        MyDialog.PrintOut(String.format("Игра началась, %s.\nЗагадайте животное и нажмите пробел.", name));
        //if (Read().equals(" ")) {
            for (var i = 0; i < 12; i++) {
                String question = game.GetRandomQuestion();
                game.currentQuestion = question;
                PrintOut(question);
                String answer = Read();
                GetReaction(answer);
                //game.PutAnswer(question, answer);
            }
            PrintOut("Дайте-ка подумать...");
            //System.out.print(game.Answers);
            PrintOut(game.GuessAnimal());
            CommonPhrases.replace("/score", String.format("Компьютер - %s : Пользователь - %d", game.getCompScore(), game.getUserScore()));

    }

    public static void PrintOut(String msg){
        System.out.println(msg);
    }

    public static String Read(){
        return newscan.next();
    }
}
