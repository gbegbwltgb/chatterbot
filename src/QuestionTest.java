import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void makeGoodTextQuestion() {
        Question question = new Question("area", "лес");
        var expected = "Среда обитания этого животного - лес?";
        var result = question.makeTextQuestion();
        Assert.assertEquals(expected, result);
    }

    @Test
    void makeBadTextQuestion() {
        Question question = new Question("Любимая еда", "рыба");
        var expected = "Любимая еда этого животного - рыба?";
        var result = question.makeTextQuestion();
        Assert.assertEquals(expected, result);
    }

}