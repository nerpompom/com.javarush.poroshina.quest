package com.javarush.poroshina.quest.controller;

import com.javarush.poroshina.quest.entity.Game;
import com.mockrunner.mock.web.MockHttpServletRequest;
import com.mockrunner.mock.web.MockHttpServletResponse;
import com.mockrunner.mock.web.MockHttpSession;
import org.junit.jupiter.api.Test;
import javax.servlet.ServletException;
import java.io.IOException;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServletTest {

    GameServlet servlet = new GameServlet();

    @Test
    void isUserNameSaveInSession() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setupAddParameter("userName", "Иван");

        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        MockHttpServletResponse response = new MockHttpServletResponse();

        servlet.doPost(request, response);

        assertEquals("Иван", session.getAttribute("userName"));
    }

    @Test
    void isGameSaveInSession() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setupAddParameter("userName", "Иван");

        MockHttpSession session = new MockHttpSession();
        request.setSession(session);

        MockHttpServletResponse response = new MockHttpServletResponse();

        servlet.doPost(request, response);

        assertNotNull(session.getAttribute("game"));
        assertTrue(session.getAttribute("game") instanceof Game);
    }
}
