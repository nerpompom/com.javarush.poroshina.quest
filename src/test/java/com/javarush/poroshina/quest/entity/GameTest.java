package com.javarush.poroshina.quest.entity;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class GameTest {

    @Test
    void isReturnEmptyAnswersWhenCurrentQuestionIsNull() {
        Game game = new Game();

        String[] answers = game.getCurrentAnswers();

        assertNotNull(answers);
        assertEquals(0, answers.length);
    }

    @Test
    void isIncrementGameCount() {
        Game game = new Game();
        assertEquals(0, game.getGameCount());

        game.incrementGameCount();
        assertEquals(1, game.getGameCount());
    }
}
