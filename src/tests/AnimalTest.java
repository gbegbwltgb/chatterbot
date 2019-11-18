package tests;

import bot.Animal;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class AnimalTest {

    @Test
    void isSimilar() {
        Animal animal1 = new Animal("animal1", "yellow", "forest", "big");
        Animal animal2 = new Animal("animal2", "yellow", "forest", "big");
        var result = animal1.isSimilar(animal2);
        Assert.assertTrue(result);
    }

    @Test
    void isNotSimilar() {
        Animal animal1 = new Animal("animal1", "yellow", "forest", "big");
        Animal animal2 = new Animal("animal2", "red", "forest", "big");
        var result = animal1.isSimilar(animal2);
        Assert.assertFalse(result);
    }
}