package tests;

import bot.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class RoundTest {
    Animal[] Animals = {new Animal("animal", "белый", "лес", "big")};

    @Test
    void putAnswer() {
        var round = createRound();
        var question = new Question(Category.AREA, "feature");
        round.putAnswer(question, "да");
        var result = round.answers.get(question);
        Assert.assertTrue(result);
    }
    @Test
    void guessAnimal_null_withoutAnswers() {
        var round = createRound();
        round.answers = new HashMap<Question, Boolean>();
        Animal expected = null;
        var result = round.guessAnimal(round.answers, Animals);
        Assert.assertEquals(expected, result);
    }

    @Test
    void guessAnimal_animal_withAnswers() throws NoSuchFieldException, IllegalAccessException {
        var round = createRound();
        round.answers.put(new Question(Category.AREA, "лес"), Boolean.TRUE);
        round.answers.put(new Question(Category.COLOR, "белый"), Boolean.TRUE);
        round.answers.put(new Question(Category.SIZE, "big"), Boolean.TRUE);
        var expected = Animals[0];
        var result = round.guessAnimal(round.answers, Animals);
        Assert.assertEquals(expected, result);
    }

    @Test
    void guessAnimal_null_withAnswers() throws NoSuchFieldException, IllegalAccessException {
        var round = createRound();
        round.answers.put(new Question(Category.AREA, "лес"), Boolean.TRUE);
        round.answers.put(new Question(Category.COLOR, "белый"), Boolean.FALSE);
        round.answers.put(new Question(Category.COLOR, "черный"), Boolean.TRUE);
        round.answers.put(new Question(Category.SIZE, "big"), Boolean.TRUE);
        var result = round.guessAnimal(round.answers, Animals);
        Assert.assertEquals(null, result);
    }

    private Round createRound() {
        var params = new HashMap<Category, String[]>();
        params.put(Category.COLOR, new String[]{"белый", "черный"});
        params.put(Category.AREA, new String[]{"джунгли", "лес"});
        params.put(Category.SIZE, new String[]{"big"});
        var questions = QuestionFactory.makeQuestions(params);
        return new Round(questions);
    }
}
