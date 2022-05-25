package quadsolutions.triviaassessment.trivia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import quadsolutions.triviaassessment.models.Question;

import java.util.List;

@RestController
public class TriviaController {

    private final TriviaService triviaService;

    public TriviaController(TriviaService triviaService) {
        this.triviaService = triviaService;
    }

    @GetMapping("questions")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = this.triviaService.fetchQuestionsFromOpenAPI();
        return ResponseEntity.ok(questions);
    }
}
