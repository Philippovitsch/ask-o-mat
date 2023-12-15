package at.stadter.askomat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AskOMatViewTest {

    private final AskOMatView askOMatView;
    private final PrintStream standardOut;
    private final ByteArrayOutputStream outputStreamCaptor;

    public AskOMatViewTest() {
        this.askOMatView = new AskOMatView();
        this.standardOut = System.out;
        this.outputStreamCaptor = new ByteArrayOutputStream();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printMessage_Test() {
        Messages message = Messages.DEFAULT_ANSWER;
        askOMatView.printMessage(message);
        assertEquals(message.getMessage() + "\n", outputStreamCaptor.toString());
    }

    @Test
    public void printAnswers_Test() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        QuestionModel questionModel = new QuestionModel(question, answers);
        StringBuilder resultBuilder = new StringBuilder();
        answers.forEach(answer -> resultBuilder.append("- ").append(answer).append("\n"));
        askOMatView.printAnswers(questionModel);
        assertEquals(resultBuilder.toString(), outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
