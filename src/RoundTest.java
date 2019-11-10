import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    Animal[] Animals = {new Animal("animal", "белый", "лес", "big")};

    @Test
    void guessAnimal_null_withoutAnswers() throws NoSuchFieldException, IllegalAccessException {
        var round = createRound();
        round.Answers = new HashMap<Question, String>();
        Animal expected = null;
        var result = round.guessAnimal(round.Answers, Animals);
        Assert.assertEquals(expected, result);
    }

    @Test
    void guessAnimal_animal_withAnswers() throws NoSuchFieldException, IllegalAccessException {
        var round = createRound();
        round.Answers.put(new Question("area", "лес"), "да");
        round.Answers.put(new Question("color", "белый"), "да");
        round.Answers.put(new Question("size", "big"), "да");
        var expected = Animals[0];
        var result = round.guessAnimal(round.Answers, Animals);
        Assert.assertEquals(expected, result);
    }

    @Test
    void guessAnimal_null_withAnswers() throws NoSuchFieldException, IllegalAccessException {
        var round = createRound();
        round.Answers.put(new Question("area", "лес"), "да");
        round.Answers.put(new Question("color", "белый"), "нет");
        round.Answers.put(new Question("color", "черный"), "да");
        round.Answers.put(new Question("size", "big"), "да");
        var result = round.guessAnimal(round.Answers, Animals);
        Assert.assertEquals(null, result);
    }

    private Round createRound(){
        var params = new HashMap<String, String[]>();
        params.put("color", new String[]{"белый", "черный"});
        params.put("area", new String[]{"джунгли", "лес"});
        params.put("size", new String[]{"big"});
        var questions = QuestionFactory.makeQuestions(params);
        return new Round(questions);
    }
}
