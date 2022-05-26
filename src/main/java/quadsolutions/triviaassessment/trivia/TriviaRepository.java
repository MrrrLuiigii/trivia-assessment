package quadsolutions.triviaassessment.trivia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quadsolutions.triviaassessment.models.QuestionWithAnswers;

@Repository
public interface TriviaRepository extends JpaRepository<QuestionWithAnswers, Integer> {
}
