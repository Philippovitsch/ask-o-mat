package at.stadter.askomat;

public class AskOMatController {

    private final UserInputHandler inputHandler;
    private final AskOMatView askOMatView;
    private final QuestionRepository questionRepository;

    public AskOMatController(
            UserInputHandler inputHandler,
            AskOMatView askOMatView,
            QuestionRepository questionRepository
    ) {
        this.questionRepository = questionRepository;
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

    }

    private void addQuestion() {

    }

}
