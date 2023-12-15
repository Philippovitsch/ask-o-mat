package at.stadter.askomat;

import java.util.List;
import java.util.Optional;

public class QuestionRepository {

    private final List<QuestionModel> questions;

    public QuestionRepository(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public Optional<QuestionModel> getQuestionBy(String wording) {
        return questions.stream()
                .filter(question -> question.question().equals(wording))
                .findFirst();
    }

    public void addQuestion(QuestionModel question) {
        questions.add(question);
    }

}
