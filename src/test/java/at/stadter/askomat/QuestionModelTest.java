package at.stadter.askomat;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionModelTest {

    private final String question;
    private final List<String> answers;
    private final QuestionModel questionModel;

    public QuestionModelTest() {
        this.question = "TestQuestion";
        this.answers = List.of("TestAnswer1", "TestAnswer2");
        this.questionModel = new QuestionModel(question, answers);
    }

    @Test
    public void checkIfPropertiesAreReturnedCorrectly() {
        assertEquals(question, questionModel.question());
        assertEquals(answers, questionModel.answers());
    }

}
