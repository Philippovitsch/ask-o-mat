package at.stadter.askomat;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AskOMatControllerTest {

    private final UserInputHandler inputHandler;
    private final AskOMatView askOMatView;
    private final QuestionService questionService;
    private final AskOMatController askOMatController;

    public AskOMatControllerTest() {
        this.inputHandler = mock(UserInputHandler.class);
        this.askOMatView = mock(AskOMatView.class);
        this.questionService = mock(QuestionService.class);
        this.askOMatController = new AskOMatController(inputHandler, askOMatView, questionService);
    }

    @Test
    public void simulateExitProgram() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("Q");
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(1)).printMainMenu();
        verify(askOMatView, times(1)).printMessage(any());
        verify(inputHandler,times(1)).getUserInput();
    }

    @Test
    public void simulateUnknownOption() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("U", "Q");
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(2)).printMessage(any());
        verify(inputHandler,times(2)).getUserInput();
    }

    @Test
    public void simulateAskQuestion_ValidQuestionFound() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        QuestionModel questionModel = new QuestionModel(question, answers);
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        doNothing().when(askOMatView).printAnswers(any());
        when(inputHandler.getUserInput()).thenReturn("1", "Question", "Q");
        when(questionService.getQuestionBy(any())).thenReturn(Optional.of(questionModel));
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(2)).printMessage(any());
        verify(askOMatView, times(1)).printAnswers(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).getQuestionBy(any());
    }

    @Test
    public void simulateAskQuestion_NoQuestionFound() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("1", "Question", "Q");
        when(questionService.getQuestionBy(any())).thenReturn(Optional.empty());
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(3)).printMessage(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).getQuestionBy(any());
    }

    @Test
    public void simulateAddQuestion_IsNoValidFormat() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("2", "Question", "Q");
        when(questionService.isValidFormat(any())).thenReturn(false);
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(4)).printMessage(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).isValidFormat(any());
    }

    @Test
    public void simulateAddQuestion_QuestionAlreadyExists() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("2", "Question", "Q");
        when(questionService.isValidFormat(any())).thenReturn(true);
        when(questionService.questionAlreadyExists(any())).thenReturn(true);
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(4)).printMessage(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).isValidFormat(any());
        verify(questionService, times(1)).questionAlreadyExists(any());
    }

    @Test
    public void simulateAddQuestion_IsNotWithinMaxLength() {
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        when(inputHandler.getUserInput()).thenReturn("2", "Question", "Q");
        when(questionService.isValidFormat(any())).thenReturn(true);
        when(questionService.questionAlreadyExists(any())).thenReturn(false);
        when(questionService.isWithinMaxLength(any())).thenReturn(false);
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(4)).printMessage(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).isValidFormat(any());
        verify(questionService, times(1)).questionAlreadyExists(any());
        verify(questionService, times(1)).isWithinMaxLength(any());
    }

    @Test
    public void simulateAddQuestion_FulfillsAllRules() {
        String question = "TestQuestion";
        List<String> answers = List.of("TestAnswer1", "TestAnswer2");
        QuestionModel questionModel = new QuestionModel(question, answers);
        doNothing().when(askOMatView).printWelcomeText();
        doNothing().when(askOMatView).printMainMenu();
        doNothing().when(askOMatView).printMessage(any());
        doNothing().when(questionService).addQuestion(any());
        when(inputHandler.getUserInput()).thenReturn("2", "Question", "Q");
        when(questionService.isValidFormat(any())).thenReturn(true);
        when(questionService.questionAlreadyExists(any())).thenReturn(false);
        when(questionService.isWithinMaxLength(any())).thenReturn(true);
        when(questionService.createQuestion(any())).thenReturn(questionModel);
        askOMatController.start();
        verify(askOMatView, times(1)).printWelcomeText();
        verify(askOMatView, times(2)).printMainMenu();
        verify(askOMatView, times(4)).printMessage(any());
        verify(inputHandler,times(3)).getUserInput();
        verify(questionService, times(1)).isValidFormat(any());
        verify(questionService, times(1)).questionAlreadyExists(any());
        verify(questionService, times(1)).isWithinMaxLength(any());
        verify(questionService, times(1)).createQuestion(any());
        verify(questionService,times(1)).addQuestion(any());
    }

}
