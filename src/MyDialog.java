import java.util.HashMap;
import java.util.Scanner;

public class MyDialog {
    public static String name;
    public static Game game;
    //public static String rules = "Для начала игры введите /start.\nЧтобы узнать правила, введите /help.\nЧтобы узнать счёт, введите /score.";
    public static HashMap<String, String> CommonPhrases = new HashMap<String, String>();
    public static Scanner newscan= new Scanner(System.in);//.useDelimiter(" ");

    public MyDialog(){

        CommonPhrases.put("greeting", "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное." );
        CommonPhrases.put("/start", "Введите ваше имя.");
        CommonPhrases.put("/help", Game.Rules);
        CommonPhrases.put("/score", String.format("Компьютер - %s : Пользователь - %d", 0, 0));
        CommonPhrases.put("/again", "Что ж, попробуем ещё раз.");

        PrintOut(CommonPhrases.get("greeting"));
        PrintOut(CommonPhrases.get("/help"));
    }

    public static void GetReaction(String command){
        if (command.equals("/start")){
            PrintOut(CommonPhrases.get("/start"));
            name = Read();
            game = new Game();
            PlayGame(name, game);
        }
        else if (command.equals("/again")){
            if (game!=null){
                game.UpdateGame();
                PlayGame(name, game);
            }
            else {
                PrintOut("Вы еще не начали игру. Чтобы начать, введите /start.");
            }
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
            if (!command.equals("да")&&!command.equals("нет")){
                PrintOut("Я вас не понимаю :(");
            }
            else {
                game.PutAnswer(game.currentQuestion, command);
                game.Questions.remove(game.currentQuestion);
            }
        }
        else {//подавать слово в какой-то метод из класса игры
            PrintOut("Такой команды не существует...");
        }
    }

    public static void PlayGame(String name, Game game) {
        //game = new Game();
        MyDialog.PrintOut(String.format("Игра началась, %s.\nЗагадайте животное.", name));
        //if (Read().equals(" ")) {
            for (var i = 0; i < 11; i++) {
                String question = game.GetRandomQuestion();
                game.currentQuestion = question;
                PrintOut(question.split(":")[1]);
                String answer = Read();
                GetReaction(answer);
                game.MakeAnimal();
                if (!game.GuessAnimal().equals("Я не знаю такое животное :(")){
                    game.compScore++;
                    break;
                }
                //game.PutAnswer(question, answer);
            }
            if (game.GuessAnimal().equals("Я не знаю такое животное :(")) {
                game.userScore++;
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
