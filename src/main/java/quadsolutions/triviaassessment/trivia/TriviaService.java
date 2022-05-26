package quadsolutions.triviaassessment.trivia;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import quadsolutions.triviaassessment.models.QuestionWithAnswers;
import quadsolutions.triviaassessment.models.AnsweredQuestion;
import quadsolutions.triviaassessment.models.ApiResponse;
import quadsolutions.triviaassessment.models.QuestionToAsk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TriviaService {
    private final RestTemplate restTemplate;
    private final TriviaRepository repository;

    public TriviaService(TriviaRepository repository, RestTemplateBuilder restTemplateBuilder) {
        this.repository = repository;
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<QuestionToAsk> getQuestions() {
        List<QuestionWithAnswers> questionWithAnswers = this.repository.findAll();
        List<QuestionToAsk> questions = new ArrayList<>();
        questionWithAnswers.forEach((q) -> questions.add(new QuestionToAsk(q)));
        return questions;
    }

    public List<QuestionWithAnswers> fetchQuestionsFromTriviaAPI() {
        String url = "https://opentdb.com/api.php?amount=100";
        ApiResponse apiResponse = this.restTemplate.getForObject(url, ApiResponse.class);
        List<QuestionWithAnswers> questionWithAnswers = apiResponse.getResults();
        for (int i = 0; i < questionWithAnswers.size(); i++) {
            questionWithAnswers.get(i).setId(i + 1);
        }
        return this.repository.saveAll(questionWithAnswers);
    }

    public AnsweredQuestion checkAnswer(Integer questionId, String answer) {
        Optional<QuestionWithAnswers> optionalAnsweredQuestion = this.repository.findById(questionId);
        if (optionalAnsweredQuestion.isEmpty())
            throw new RuntimeException(String.format("Question with id %d does not exist...", questionId));
        QuestionWithAnswers questionWithAnswers = optionalAnsweredQuestion.get();

        AnsweredQuestion answeredQuestionViewModel = new AnsweredQuestion(questionWithAnswers);
        if (questionWithAnswers.getCorrect_answer().equalsIgnoreCase(answer)) answeredQuestionViewModel.setCorrect(true);
        else answeredQuestionViewModel.setCorrect(false);
        return answeredQuestionViewModel;
    }
}
