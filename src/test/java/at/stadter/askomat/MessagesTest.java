package at.stadter.askomat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MessagesTest {

    @Test
    public void checkIfAllMessagesArePresent() {
        assertFalse(Messages.ADD_QUESTION.getMessage().isEmpty());
        assertFalse(Messages.ASK_QUESTION.getMessage().isEmpty());
        assertFalse(Messages.DEFAULT_ANSWER.getMessage().isEmpty());
        assertFalse(Messages.GOODBYE.getMessage().isEmpty());
        assertFalse(Messages.QUESTION_ALREADY_EXISTS.getMessage().isEmpty());
        assertFalse(Messages.QUESTION_SAVED.getMessage().isEmpty());
        assertFalse(Messages.QUESTION_SYNTAX.getMessage().isEmpty());
        assertFalse(Messages.QUESTION_TOO_LONG.getMessage().isEmpty());
        assertFalse(Messages.UNKNOWN_OPTION.getMessage().isEmpty());
        assertFalse(Messages.WRONG_FORMAT.getMessage().isEmpty());
    }

}
