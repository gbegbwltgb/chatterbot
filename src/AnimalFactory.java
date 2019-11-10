import java.lang.reflect.Field;
import java.util.HashMap;

public class AnimalFactory {
    public static Animal makeAnimal(HashMap<Question, String> answers) throws NoSuchFieldException, IllegalAccessException {
        var animal = new Animal("myAnimal", "", "", "");
        Class c = animal.getClass();
        if (!answers.isEmpty()) {
            for (var question : answers.keySet()) {
                if (question!=null){
                if (answers.get(question).equals("да")) {
                    Field field = c.getField(question.category);
                    field.set(animal, question.feature);

                }
                }
            }
            return animal;
        }
        return null;
    }
}
