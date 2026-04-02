package com.javarush.poroshina.quest.service;

import org.junit.jupiter.api.Test;
import static junit.framework.Assert.assertSame;

public class GameServiceTest {

    @Test
    void isReturnSameInstance() {
        GameService one = GameService.getInstance();
        GameService two = GameService.getInstance();

        assertSame(one, two);
    }
}
