package com.javarush.poroshina.quest.repository;

import com.javarush.poroshina.quest.entity.Question;
import org.junit.jupiter.api.Test;
import static junit.framework.Assert.*;

public class GameRepositoryTest {

    GameRepository repository = GameRepository.getInstance();

    @Test
    void CanReturnQuestionsAndAnswers() {
        Question[] questions = repository.getAllQuestion();

        assertNotNull(questions);
        assertTrue(questions.length > 0);

        for (Question question : questions) {
            assertNotNull(question.getAnswers());
        }
    }

    @Test
    void isReturnSameInstance() {
        GameRepository one = GameRepository.getInstance();
        GameRepository two = GameRepository.getInstance();

        assertSame(one, two);
    }
}
