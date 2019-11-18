package bot;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestionFactory {

    public static ArrayList<Question> makeQuestions(HashMap<Category, String[]> params) {
        var questions = new ArrayList<Question>();
        var categories = params.keySet();
        for (var category : categories) {
            var features = params.get(category);
            for (var feature : features) {
                questions.add(new Question(category, feature));
            }
        }
        return questions;
    }

    public static String makeTextQuestion(Category category, String feature) {
        return String.format("%s этого животного - %s?", category.getName(), feature);
    }
}

