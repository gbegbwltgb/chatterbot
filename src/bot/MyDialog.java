package bot;

public class MyDialog {
    public Game game = new Game();

    public MyDialog() {
        Program.PrintOut("Добро пожаловать в игру. Игрок должен загадать животное, задача компьютера - \nугадать, что это за животное.");
        Program.PrintOut(Game.Rules);
    }

    private static boolean isCommand(String text) {
        return text.startsWith("/");
    }

    public String getReaction(String command) {
        if (command.equals("/start")) {
            return game.startGame();
        } else if (command.equals("/again")) {
            return game.repeatGame();
        } else if (command.equals("/score")) {
            return game.getScore();
        } else if (command.equals("/exit")) {
            Program.PrintOut("До свидания.");
            System.exit(0);
        } else if (command.equals("/help")) {
            return game.Rules;
        } else if (game.currentRound == null) {
            return "Такой команды не существует...";
        } else if (!game.currentRound.isFinished) {
            return game.playGame(command);
        }
        return ("Для следующего раунда введите /again. Для выхода из игры введите /exit.");
    }
}
