package at.stadter.askomat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionRepositoryTest {

    private final QuestionRepository questionRepository;
    private final QuestionModel questionModel;

    public QuestionRepositoryTest() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        this.questionModel = new QuestionModel(question, answers);

        List<QuestionModel> questions = new ArrayList<>();
        questions.add(questionModel);
        this.questionRepository = new QuestionRepository(questions);
    }

    @Test
    public void getQuestionBy_ValidQuestionFound() {
        String question = questionModel.question();
        assertTrue(questionRepository.getQuestionBy(question).isPresent());
        assertEquals(questionModel, questionRepository.getQuestionBy(question).get());
    }

    @Test
    public void getQuestionBy_NoQuestionFound() {
        String question = "AnotherQuestion";
        assertTrue(questionRepository.getQuestionBy(question).isEmpty());
    }

    @Test
    public void addQuestion_Test() {
        String question = "AnotherTestQuestion";
        List<String> answers = List.of("AnotherTestAnswer1", "AnotherTestAnswer2");
        QuestionModel newQuestionModel = new QuestionModel(question, answers);
        questionRepository.addQuestion(newQuestionModel);
        assertTrue(questionRepository.getQuestionBy(question).isPresent());
        assertEquals(newQuestionModel, questionRepository.getQuestionBy(question).get());
    }

}
