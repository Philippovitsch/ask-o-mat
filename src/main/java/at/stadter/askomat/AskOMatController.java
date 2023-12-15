package at.stadter.askomat;

import java.util.Optional;

public class AskOMatController {

    private final UserInputHandler inputHandler;
    private final AskOMatView askOMatView;
    private final QuestionService questionService;

    public AskOMatController(
            UserInputHandler inputHandler,
            AskOMatView askOMatView,
            QuestionService questionService
    ) {
        this.questionService = questionService;
        this.askOMatView = askOMatView;
        this.inputHandler = inputHandler;
    }

    public void start() {
        askOMatView.printWelcomeText();
        boolean isRunning = true;
        while (isRunning) {
            askOMatView.printMainMenu();
            String option = inputHandler.getUserInput();
            switch (option) {
                case "1" -> askQuestion();
                case "2" -> addQuestion();
                case "q", "Q" -> isRunning = false;
                default -> askOMatView.printMessage(Messages.UNKNOWN_OPTION);
            }
        }
        askOMatView.printMessage(Messages.GOODBYE);
    }

    private void askQuestion() {
        askOMatView.printMessage(Messages.ASK_QUESTION);
        String userQuestion = inputHandler.getUserInput();
        Optional<QuestionModel> question = questionService.getQuestionBy(userQuestion);
        if (question.isEmpty()) {
            askOMatView.printMessage(Messages.DEFAULT_ANSWER);
        } else {
            askOMatView.printAnswers(question.get());
        }
    }

    private void addQuestion() {
        askOMatView.printMessage(Messages.ADD_QUESTION);
        askOMatView.printMessage(Messages.QUESTION_SYNTAX);
        String userQuestion = inputHandler.getUserInput();
        if (!questionService.isValidFormat(userQuestion)) {
            askOMatView.printMessage(Messages.WRONG_FORMAT);
        } else if (questionService.questionAlreadyExists(userQuestion)) {
            askOMatView.printMessage(Messages.QUESTION_ALREADY_EXISTS);
        } else if (!questionService.isWithinMaxLength(userQuestion)) {
            askOMatView.printMessage(Messages.QUESTION_TOO_LONG);
        } else {
            QuestionModel question = questionService.createQuestion(userQuestion);
            questionService.addQuestion(question);
            askOMatView.printMessage(Messages.QUESTION_SAVED);
        }
    }

}
