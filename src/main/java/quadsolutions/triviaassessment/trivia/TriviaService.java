package quadsolutions.triviaassessment.trivia;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import quadsolutions.triviaassessment.models.AnsweredQuestion;
import quadsolutions.triviaassessment.models.ApiResponse;
import quadsolutions.triviaassessment.models.UnansweredQuestion;

import java.util.ArrayList;
import java.util.List;

@Service
public class TriviaService {
    private final RestTemplate restTemplate;
    private final TriviaRepository repository;

    public TriviaService(RestTemplateBuilder restTemplateBuilder, TriviaRepository repository) {
        this.restTemplate = restTemplateBuilder.build();
        this.repository = repository;
    }

    public List<UnansweredQuestion> getQuestions() {
        List<AnsweredQuestion> answeredQuestions = this.repository.findAll();
        List<UnansweredQuestion> questions = new ArrayList<>();
        answeredQuestions.forEach((q) -> questions.add(new UnansweredQuestion(q)));
        return questions;
    }

    public List<AnsweredQuestion> fetchQuestionsFromTriviaAPI() {
        String url = "https://opentdb.com/api.php?amount=100";
        ApiResponse apiResponse = this.restTemplate.getForObject(url, ApiResponse.class);
        List<AnsweredQuestion> answeredQuestions = apiResponse.getResults();
        for(int i = 0; i < answeredQuestions.size(); i++){
            answeredQuestions.get(i).setId(i + 1);
        }
        return this.repository.saveAll(answeredQuestions);
    }
}
