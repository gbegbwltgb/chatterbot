import java.util.ArrayList;
import java.util.HashMap;

public class Round {
    public boolean isFinished;
    public int askedQuestionCount;
    public int questionCount;
    public Question currentQuestion;
    public ArrayList<Question> Questions;
    public HashMap<Question, String> Answers = new HashMap<>();
    public String winner;

    public Round(ArrayList<Question> questions) {
        Questions = questions;
        questionCount = Questions.size();
    }

    public Animal guessAnimal(HashMap<Question, String> answers, Animal[] animals) throws NoSuchFieldException, IllegalAccessException {
        Animal myAnimal = AnimalFactory.makeAnimal(answers);
        for (Animal animal : animals) {
            if (animal.isSimilar(myAnimal)) {
                return animal;
            }
        }
        return null;
    }

    public Question getNextQuestion(ArrayList<Question> questions, int number) {
        return questions.get(number);
    }

    public String play() throws NoSuchFieldException, IllegalAccessException {
        if (askedQuestionCount < questionCount) {
            currentQuestion = getNextQuestion(Questions, askedQuestionCount);
            Animal animal = guessAnimal(Answers, Game.Animals);
            if (animal != null) {
                isFinished = true;
                winner = "computer";
                return String.format("Дайте-ка подумать...\nЗагаданное животное - %s.", animal.name);
            } else {
                return (currentQuestion.question);
            }
        }
        Animal animal = guessAnimal(Answers, Game.Animals); //проверка последнего вопроса
        if (animal != null) {
            isFinished = true;
            winner = "computer";
            return String.format("Загаданное животное - %s.", animal.name);
        }
        isFinished = true;
        winner = "user";
        return "Я не знаю такое животное :(";
    }
}
