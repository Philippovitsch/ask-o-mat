package at.stadter.askomat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class QuestionService {

    private final String SEPARATOR = "?";
    private final String DELIMITER = "\"";
    private final String SPACE = " ";
    private final int MAX_LENGTH = 255;

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public boolean isValidFormat(String userQuestion) {
        if (!userQuestion.contains(SEPARATOR) || userQuestion.startsWith(SEPARATOR) || userQuestion.endsWith(SEPARATOR)) {
            return false;
        }
        String rawAnswers = userQuestion.substring(userQuestion.indexOf(SEPARATOR) + 1);
        return  rawAnswers.startsWith(SPACE) &&
                Arrays.stream(rawAnswers.split(SPACE))
                    .filter(answer -> !answer.isBlank())
                    .allMatch(answer ->
                            answer.startsWith(DELIMITER) &&
                            answer.endsWith(DELIMITER) &&
                            answer.length() > 2 &&
                            answer.chars().filter(character -> character == DELIMITER.charAt(0)).count() == 2
                    );
    }

    public boolean questionAlreadyExists(String userQuestion) {
        String question = userQuestion.substring(0, userQuestion.indexOf(SEPARATOR) + 1);
        return questionRepository.getQuestionBy(question).isPresent();
    }

    public boolean isWithinMaxLength(String userQuestion) {
        String question = userQuestion.substring(0, userQuestion.indexOf(SEPARATOR) + 1);
        String rawAnswers = userQuestion.substring(userQuestion.indexOf(SEPARATOR) + 1);
        return  question.length() <= MAX_LENGTH &&
                Arrays.stream(rawAnswers.split(SPACE))
                    .filter(answer -> !answer.isBlank())
                    .map(answer -> answer.substring(1, answer.length() - 1))
                    .allMatch(answer -> answer.length() <= MAX_LENGTH);
    }

    public QuestionModel createQuestion(String userQuestion) {
        String question = userQuestion.substring(0, userQuestion.indexOf(SEPARATOR) + 1);
        String rawAnswers = userQuestion.substring(userQuestion.indexOf(SEPARATOR) + 1);
        List<String> answers = Arrays.stream(rawAnswers.split(SPACE))
                .filter(answer -> !answer.isBlank())
                .map(answer -> answer.substring(1, answer.length() - 1))
                .toList();
        return new QuestionModel(question, answers);
    }

    public void addQuestion(QuestionModel question) {
        questionRepository.addQuestion(question);
    }

    public Optional<QuestionModel> getQuestionBy(String userQuestion) {
        return questionRepository.getQuestionBy(userQuestion);
    }

}
