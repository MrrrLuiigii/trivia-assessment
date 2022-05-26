package quadsolutions.triviaassessment.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuestionWithAnswers extends Question {
    private String correct_answer;
    @ElementCollection
    private List<String> incorrect_answers;

    public QuestionWithAnswers(Integer id, String category, String type, String difficulty, String question,
                               String correct_answer, List<String> incorrect_answers) {
        super(id, category, type, difficulty, question);
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
}
