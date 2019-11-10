import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @Test
    void makeQuestions() {
        var params = new HashMap<String, String[]>();
        params.put("color", new String[]{"белый", "черный"});
        params.put("area", new String[]{"джунгли", "лес"});
        var expected = new ArrayList<Question>();
        expected.add(new Question("area", "джунгли"));
        expected.add(new Question("area", "лес"));
        expected.add(new Question("color", "белый"));
        expected.add(new Question("color", "черный"));
        var result = QuestionFactory.makeQuestions(params);
        boolean similar = true;
        for (var i=0; i<expected.size(); i++){
            if (!compareQuestions(expected.get(i), result.get(i))){
                similar = false;
            }
        }
        Assert.assertTrue(similar);
    }

    private boolean compareQuestions(Question q1, Question q2){
        return q1.category.equals(q2.category) && q1.feature.equals(q2.feature);
    }
}