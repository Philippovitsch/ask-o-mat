package at.stadter.askomat;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserInputHandlerTest {

    private final Scanner scanner;
    private final UserInputHandler inputHandler;

    public UserInputHandlerTest() {
        scanner = mock(Scanner.class);
        inputHandler = new UserInputHandler(scanner);
    }

    @Test
    public void getUserInput_ReturnsValidString() {
        String userInput = "TestUserInput";
        when(scanner.nextLine()).thenReturn(userInput);
        assertEquals(userInput, inputHandler.getUserInput());
    }

}
