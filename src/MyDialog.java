import java.util.HashMap;
import java.util.Scanner;

public class MyDialog {
    public static String name;
    public static Game game;
    public static HashMap<String, String> CommonPhrases = new HashMap<String, String>();
    public static Scanner newscan= new Scanner(System.in);//.useDelimiter(" ");

    public MyDialog(){
        CommonPhrases.put("greeting", "Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное." );
        CommonPhrases.put("/start", "Введите ваше имя.");
        CommonPhrases.put("/help", Game.Rules);
        CommonPhrases.put("/score", String.format("Компьютер - %s : Пользователь - %d", 0, 0));
        CommonPhrases.put("/again", "Что ж, попробуем ещё раз.");

        Program.PrintOut(CommonPhrases.get("greeting"));
        Program.PrintOut(CommonPhrases.get("/help"));
    }

    public static String GetReaction(String command){
        if (command.equals("/start")){
            game = new Game();
            return CommonPhrases.get("/start");
        }
        else if (command.equals("/again")){
            if (game!=null){
                game.UpdateGame();
                return game.PlayGame();
            }
            else {
                return ("Вы еще не начали игру. Чтобы начать, введите /start.");
            }
        }
        else if (command.equals("/exit")){
            Program.PrintOut("До свидания.");
            System.exit(0);
        }
        else if (CommonPhrases.containsKey(command)){
            return (CommonPhrases.get(command));
        }
        else if (game!=null && game.askedQuestions!=game.questionCount){
            if (!command.equals("да")&&!command.equals("нет")){
                if (name==null){
                    name=command;
                    Program.PrintOut(String.format("Игра началась, %s.\nЗагадайте животное.", name));
                }
                else {
                    Program.PrintOut("Я вас не понимаю :(");
                }
                return game.PlayGame();
            }
            else {
                game.PutAnswer(game.currentQuestion, command);
                game.Questions.remove(game.currentQuestion);
                game.askedQuestions++;
                return game.PlayGame();
            }
        }
        else if (game.askedQuestions==game.questionCount){
            return ("Для следующего раунда введите /again. Для выхода из игры введите /exit.");
        }
        return("Такой команды не существует...");
    }


}
