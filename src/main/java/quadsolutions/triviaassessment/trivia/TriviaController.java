package quadsolutions.triviaassessment.trivia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quadsolutions.triviaassessment.models.AnsweredQuestion;
import quadsolutions.triviaassessment.models.CheckAnswerDTO;
import quadsolutions.triviaassessment.models.QuestionToAsk;

import java.util.List;

@RestController
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("generate")
    public ResponseEntity<Boolean> generateQuestions() {
        this.triviaService.fetchQuestionsFromTriviaAPI();
        return ResponseEntity.ok(true);
    }

    @GetMapping("questions")
    public ResponseEntity<List<QuestionToAsk>> getQuestions() {
        List<QuestionToAsk> questions = this.triviaService.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("checkanswer")
    public ResponseEntity<AnsweredQuestion> answerQuestion(@RequestBody CheckAnswerDTO checkAnswerDTO) {
        AnsweredQuestion answeredQuestion =
                this.triviaService.checkAnswer(checkAnswerDTO.getQuestionId(), checkAnswerDTO.getAnswer());
        return ResponseEntity.ok(answeredQuestion);
    }
}
