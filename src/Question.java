public class Question {
    public String category;
    public String feature;
    public String question;

    public Question(String iCategory, String iFeature) {
        category = iCategory;
        feature = iFeature;
        question = makeTextQuestion();
    }

    public String makeTextQuestion() {
        var questionFactory = new QuestionFactory();
        if (questionFactory.matching.containsKey(category)) {
            return String.format("%s этого животного - %s?", questionFactory.matching.get(category), feature);
        }
        else {
            return String.format("%s этого животного - %s?", category, feature);
        }
    }
}
