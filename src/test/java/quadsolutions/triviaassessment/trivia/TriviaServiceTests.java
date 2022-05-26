package quadsolutions.triviaassessment.trivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import quadsolutions.triviaassessment.models.AnsweredQuestion;
import quadsolutions.triviaassessment.models.QuestionToAsk;
import quadsolutions.triviaassessment.models.QuestionWithAnswers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TriviaServiceTests {

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private TriviaRepository repository;

    @InjectMocks
    private TriviaService triviaService;

    private QuestionWithAnswers questionWithAnswers;


    @BeforeEach
    public void setup() {
        List<String> answers = new ArrayList<>();
        answers.add("False");
        questionWithAnswers = new QuestionWithAnswers(1, "Science & Nature", "boolean", "medium",
                "Shrimp can swim " + "backwards", "True", answers);
        repository.save(questionWithAnswers);
    }

    @Test
    public void givenNoQuestions_whenFetchQuestionsFromTriviaAPI_thenReturnQuestionWithAnswersList() {
        // given
        repository.deleteAll();
        given(repository.findAll()).willReturn(List.of());

        // when
        List<QuestionWithAnswers> questionsWithAnswers = triviaService.fetchQuestionsFromTriviaAPI();

        // then
        assertThat(questionsWithAnswers).isNotNull();
        assertThat(questionsWithAnswers.size()).isEqualTo(100);
    }

    @Test
    public void givenQuestionWithAnswersList_whenGetQuestions_thenReturnQuestionToAskList() {
        // given
        List<String> answers = new ArrayList<>();
        answers.add("Tornados");
        answers.add("Economic depression");
        answers.add("Samurai");
        QuestionWithAnswers questionWithAnswers1 = new QuestionWithAnswers(2, "History", "multiple", "medium",
                "During the Mongolian invasions of Japan, what were the Mongol boats mostly stopped by?", "Typhoons",
                answers);

        given(repository.findAll()).willReturn(List.of(questionWithAnswers, questionWithAnswers1));

        // when
        List<QuestionToAsk> questionsToAsk = triviaService.getQuestions();

        // then
        assertThat(questionsToAsk).isNotNull();
        assertThat(questionsToAsk.size()).isEqualTo(2);
    }

    @Test
    public void givenCorrectAnswer_whenAnswerQuestion_thenReturnAnsweredQuestionWithTrue() {
        // given
        given(repository.findById(1)).willReturn(Optional.ofNullable(questionWithAnswers));

        // when
        AnsweredQuestion answeredQuestion = triviaService.checkAnswer(1, "True");

        // then
        assertThat(answeredQuestion).isNotNull();
        assertThat(answeredQuestion).isInstanceOf(AnsweredQuestion.class);
        assertThat(answeredQuestion.getCorrect()).isTrue();
    }

    @Test
    public void givenWrongAnswer_whenAnswerQuestion_thenReturnAnsweredQuestionWithFalse() {
        // given
        given(repository.findById(1)).willReturn(Optional.ofNullable(questionWithAnswers));

        // when
        AnsweredQuestion answeredQuestion = triviaService.checkAnswer(1, "False");

        // then
        assertThat(answeredQuestion).isNotNull();
        assertThat(answeredQuestion).isInstanceOf(AnsweredQuestion.class);
        assertThat(answeredQuestion.getCorrect()).isFalse();
    }

    @Test
    public void givenNonExistingId_whenAnswerQuestion_thenThrowException() {
        // given when then
        assertThrows(RuntimeException.class, () ->
                triviaService.checkAnswer(2, "True"));
    }
}
