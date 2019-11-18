package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import bot.*;

import java.util.HashMap;

class AnimalFactoryTest {

    @Test
    void makeAnimal() {
        var round = createRound();
        round.answers.put(new Question(Category.AREA, "лес"), Boolean.TRUE);
        round.answers.put(new Question(Category.COLOR, "белый"), Boolean.TRUE);
        round.answers.put(new Question(Category.SIZE, "big"), Boolean.TRUE);
        var expected = new Animal("myAnimal", "белый", "лес", "big");
        var result = AnimalFactory.makeAnimal(round.answers);
        var similar = expected.isSimilar(result);
        Assert.assertTrue(similar);
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