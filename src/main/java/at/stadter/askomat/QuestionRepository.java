package at.stadter.askomat;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private final List<QuestionModel> questions;

    public QuestionRepository() {
        this.questions = new ArrayList<>();
    }

    public List<QuestionModel> getAllQuestions() {
        return questions;
    }

    public QuestionModel getQuestionBy(String wording) throws QuestionNotFoundException {
        return questions.stream()
                .filter(question -> question.question().equals(wording))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException("Couldn't find: " + wording));
    }

    public void addQuestion(QuestionModel question) {
        questions.add(question);
    }

}
