package at.stadter;

import at.stadter.askomat.AskOMatController;
import at.stadter.askomat.AskOMatView;
import at.stadter.askomat.QuestionRepository;
import at.stadter.askomat.QuestionService;
import at.stadter.askomat.UserInputHandler;

public class Main {

    public static void main(String[] args) {
        UserInputHandler inputHandler = new UserInputHandler();
        AskOMatView askOMatView = new AskOMatView();
        QuestionRepository questionRepository = new QuestionRepository();
        QuestionService questionService = new QuestionService(questionRepository);

        AskOMatController askOMat = new AskOMatController(inputHandler, askOMatView, questionService);

        askOMat.start();
    }

}
