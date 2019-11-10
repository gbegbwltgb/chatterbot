import java.util.ArrayList;
import java.util.HashMap;

public class QuestionFactory {
    public static HashMap<String, String> matching = new HashMap();

    public QuestionFactory() {
        matching.put("area", "Среда обитания");
        matching.put("color", "Цвет");
        matching.put("size", "Размер");
    }

    public static ArrayList<Question> makeQuestions(HashMap<String, String[]> params) {
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
}
