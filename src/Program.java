import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner newscan= new Scanner(System.in);//.useDelimiter(" ");
        MyDialog dialog = new MyDialog();
        while (true) {
            var command = newscan.next();
            dialog.GetReaction(command);
        }
        //newscan.close();
        }
    }

