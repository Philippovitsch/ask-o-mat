package at.stadter.askomat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class QuestionServiceTest {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    public QuestionServiceTest() {
        this.questionRepository = mock(QuestionRepository.class);
        this.questionService = new QuestionService(questionRepository);
    }

    @ParameterizedTest
    @CsvSource({
            "A? \"a\"",
            "AB? \"ab\"",
            "ABC? \"abc\"",
            "A? \"a\" \"b\"",
            "AB? \"ab\" \"bc\"",
            "ABC? \"abc\" \"bcd\""
    })
    public void isValidFormat_ReturnsTrue(String userQuestion) {
        assertTrue(questionService.isValidFormat(userQuestion));
    }

    @ParameterizedTest
    @CsvSource({
            "''",
            "' '",
            "?",
            "ABC?",
            "?abc",
            "?\"abc\"",
            "ABC?abc",
            "ABC? abc",
            "ABC?\"abc\"",
            "ABC?\"abc\"\"bcd\"",
            "ABC? \"abc\"\"bcd\""
    })
    public void isValidFormat_ReturnsFalse(String userQuestion) {
        assertFalse(questionService.isValidFormat(userQuestion));
    }

    @Test
    public void questionAlreadyExists_ReturnsTrue() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        QuestionModel questionModel = new QuestionModel(question, answers);
        when(questionRepository.getQuestionBy(any())).thenReturn(Optional.of(questionModel));
        assertTrue(questionService.questionAlreadyExists("TestQuestion? \"TestAnswer\""));
    }

    @Test
    public void questionAlreadyExists_ReturnsFalse() {
        when(questionRepository.getQuestionBy(any())).thenReturn(Optional.empty());
        assertFalse(questionService.questionAlreadyExists("TestQuestion? \"TestAnswer\""));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/maxLengthValues.csv")
    public void isWithinMaxLength_Test(boolean result, String userInput) {
        assertEquals(result, questionService.isWithinMaxLength(userInput));
    }

    @Test
    public void createQuestion_Test() {
        String question = "TestQuestion?";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        String userQuestion = String.format("%s \"%s\" \"%s\"", question, answers.get(0), answers.get(1));
        QuestionModel questionModel = questionService.createQuestion(userQuestion);
        assertEquals(question, questionModel.question());
        assertEquals(answers, questionModel.answers());
    }

    @Test
    public void addQuestion_Test() {
        doNothing().when(questionRepository).addQuestion(any());
        questionService.addQuestion(any());
        verify(questionRepository, times(1)).addQuestion(any());
    }

    @Test
    public void getQuestionBy_ValidQuestionFound() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        QuestionModel questionModel = new QuestionModel(question, answers);
        when(questionRepository.getQuestionBy(question)).thenReturn(Optional.of(questionModel));
        assertTrue(questionService.getQuestionBy(question).isPresent());
        assertEquals(questionModel, questionService.getQuestionBy(question).get());
    }

    @Test
    public void getQuestionBy_NoQuestionFound() {
        String question = "TestQuestion";
        when(questionRepository.getQuestionBy(question)).thenReturn(Optional.empty());
        assertTrue(questionService.getQuestionBy(question).isEmpty());
    }

}
