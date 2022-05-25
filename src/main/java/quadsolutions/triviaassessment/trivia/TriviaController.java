package quadsolutions.triviaassessment.trivia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quadsolutions.triviaassessment.models.UnansweredQuestion;

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
    public ResponseEntity<List<UnansweredQuestion>> getQuestions() {
        List<UnansweredQuestion> questions = this.triviaService.getQuestions();
        return ResponseEntity.ok(questions);
    }
}
