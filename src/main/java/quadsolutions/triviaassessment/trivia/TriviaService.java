package quadsolutions.triviaassessment.trivia;

import org.springframework.stereotype.Service;
import quadsolutions.triviaassessment.models.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class TriviaService {

    public List<Question> fetchQuestionsFromOpenAPI() {
        List<Question> questions = new ArrayList<>();
        return questions;
    }
}
