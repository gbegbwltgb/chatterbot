package tests;

import bot.Category;
import bot.Question;
import bot.QuestionFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class QuestionFactoryTest {

    @Test
    void makeQuestions() {
        var params = new HashMap<Category, String[]>();
        params.put(Category.COLOR, new String[]{"белый", "черный"});
        params.put(Category.AREA, new String[]{"джунгли", "лес"});
        var expected = new ArrayList<Question>();
        expected.add(new Question(Category.AREA, "джунгли"));
        expected.add(new Question(Category.AREA, "лес"));
        expected.add(new Question(Category.COLOR, "белый"));
        expected.add(new Question(Category.COLOR, "черный"));
        var result = QuestionFactory.makeQuestions(params);
        boolean similar = true;
        for (var i = 0; i < expected.size(); i++) {
            if (!compareQuestions(expected.get(i), result.get(i))) {
                similar = false;
            }
        }
        Assert.assertTrue(similar);
    }

    @Test
    void makeGoodTextQuestion() {
        var expected = "Среда обитания этого животного - лес?";
        var result = QuestionFactory.makeTextQuestion(Category.AREA, "лес");
        Assert.assertEquals(expected, result);
    }

    private boolean compareQuestions(Question q1, Question q2) {
        return q1.category==q2.category && q1.feature.equals(q2.feature);
    }
}