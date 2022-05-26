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
        List<String> answers = new ArrayList<>();
        answers.add(questionWithAnswers.getCorrect_answer());
        questionWithAnswers.getIncorrect_answers().forEach(a -> answers.add(a));
        this.answers = answers;
    }
}
