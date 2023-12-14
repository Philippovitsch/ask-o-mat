package at.stadter.askomat;

import java.util.List;

public record QuestionModel(
        String question,
        List<String> answers
) {}
