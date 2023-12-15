package at.stadter.askomat;

public enum Messages {

    ADD_QUESTION("\nPlease enter your question in the following format:"),
    ASK_QUESTION("\nPlease ask your question:"),
    DEFAULT_ANSWER("\nThe answer to life, universe and everything is 42. :)"),
    GOODBYE("\nBye bye!"),
    QUESTION_ALREADY_EXISTS("\nYour question already exists!"),
    QUESTION_SAVED("\nYour questions has been saved!"),
    QUESTION_SYNTAX("<question>? \"<answer1>\" \"<answer2>\" \"<answerX>\""),
    QUESTION_TOO_LONG("\nYour question/ answer is too long - try again!"),
    UNKNOWN_OPTION("\nUnknown option - try again!"),
    WRONG_FORMAT("\nWrong format - try again!");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
