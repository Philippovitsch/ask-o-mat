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
        askOMatView.printWelcomeMessage();
        boolean isRunning = true;
        while (isRunning) {
            askOMatView.printMainMenu();
            String option = inputHandler.getUserInput();
            switch (option) {
                case "1" -> askQuestion();
                case "2" -> addQuestion();
                case "q", "Q" -> isRunning = false;
                default -> askOMatView.printMessage("Unknown option - try again!");
            }
        }
        askOMatView.printMessage("Bye bye!");
    }

    private void askQuestion() {
        askOMatView.printMessage("Please ask your question:");
        String userQuestion = inputHandler.getUserInput();
        Optional<QuestionModel> question = questionService.getQuestionBy(userQuestion);
        if (question.isEmpty()) {
            askOMatView.printMessage("The answer to life, universe and everything is 42.");
        } else {
            askOMatView.printAnswers(question.get());
        }
    }

    private void addQuestion() {
        askOMatView.printMessage("Please enter your question in the following format:");
        askOMatView.printMessage("<question>? \"<answer1>\" \"<answer2>\" \"<answerX>\"");
        String userQuestion = inputHandler.getUserInput();
        if (!questionService.isValidFormat(userQuestion)) {
            askOMatView.printMessage("Wrong format - try again!");
        } else if (!questionService.isWithinMaxLength(userQuestion)) {
            askOMatView.printMessage("Your question/ answer is too long - try again!");
        } else {
            QuestionModel question = questionService.createQuestion(userQuestion);
            questionService.addQuestion(question);
            askOMatView.printMessage("Your questions has been saved!");
        }
    }

}
