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
public class UnansweredQuestion extends Question {
    private List<String> answers;

    public UnansweredQuestion(AnsweredQuestion answeredQuestion) {
        super(answeredQuestion.getId(), answeredQuestion.getCategory(), answeredQuestion.getType(), answeredQuestion.getDifficulty(), answeredQuestion.getQuestion());
        List<String> answers = new ArrayList<>();
        answers.add(answeredQuestion.getCorrect_answer());
        answeredQuestion.getIncorrect_answers().forEach(a -> answers.add(a));
        this.answers = answers;
    }
}
