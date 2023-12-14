package at.stadter.askomat;

public class AskOMatView {

    public void printWelcomeMessage() {
        System.out.println();
        System.out.println("╔════════════════════════╗");
        System.out.println("║  Welcome to ASK-O-MAT  ║");
        System.out.println("╚════════════════════════╝");
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("Please choose an option:");
        System.out.println("-> 1    Ask a question");
        System.out.println("-> 2    Add a question");
        System.out.println("-> Q    Exit");
        System.out.print("Option: ");
    }

    public void printAnswers(QuestionModel question) {
        for (String answer : question.answers()) {
            System.out.println("- " + answer);
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
