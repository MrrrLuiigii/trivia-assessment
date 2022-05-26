package quadsolutions.triviaassessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnsweredQuestion extends QuestionWithAnswers {
    private Boolean correct;

    public AnsweredQuestion(QuestionWithAnswers questionWithAnswers) {
        super(questionWithAnswers.getId(), questionWithAnswers.getCategory(), questionWithAnswers.getType(),
                questionWithAnswers.getDifficulty(), questionWithAnswers.getQuestion(),
                questionWithAnswers.getCorrect_answer(), questionWithAnswers.getIncorrect_answers());
        this.correct = false;
    }
}
