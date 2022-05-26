package quadsolutions.triviaassessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionToAsk extends Question {
    private List<String> answers;

    public QuestionToAsk(QuestionWithAnswers questionWithAnswers) {
        super(questionWithAnswers.getId(), questionWithAnswers.getCategory(), questionWithAnswers.getType(), questionWithAnswers.getDifficulty(), questionWithAnswers.getQuestion());
        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add(questionWithAnswers.getCorrect_answer());
        questionWithAnswers.getIncorrect_answers().forEach(possibleAnswers::add);
        this.answers = possibleAnswers;
    }
}
