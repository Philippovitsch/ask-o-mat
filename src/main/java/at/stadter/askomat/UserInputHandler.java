package at.stadter.askomat;

import java.util.Scanner;

public class UserInputHandler {

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
