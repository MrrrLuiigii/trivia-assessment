package quadsolutions.triviaassessment.trivia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quadsolutions.triviaassessment.models.AnsweredQuestion;

@Repository
public interface TriviaRepository extends JpaRepository<AnsweredQuestion, Integer> {
}
