package com.javarush.poroshina.quest.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.javarush.poroshina.quest.constants.AppConstants;
import com.javarush.poroshina.quest.entity.Answer;
import com.javarush.poroshina.quest.entity.Game;
import com.javarush.poroshina.quest.entity.Question;
import com.javarush.poroshina.quest.service.GameService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {
    GameService gameService = GameService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");
        if (userName != null && !userName.isBlank()) {
            session.setAttribute("userName", userName);
        }

        String sessionUserName = (String) session.getAttribute("userName");

        Question[] questions = gameService.getQuestions();

        Game game = (Game) session.getAttribute("game");
        if (game == null) {
            game = new Game(questions, true);
            session.setAttribute("game", game);
        }

        String selectedAnswerText = request.getParameter("answer");
        if (selectedAnswerText != null && !selectedAnswerText.isBlank()) {
            Answer selectedAnswer = game.findAnswerByText(selectedAnswerText);
            if (selectedAnswer != null && selectedAnswer.getNextQuestionId() != null) {
                game.setCurrentQuestionById(selectedAnswer.getNextQuestionId());
            }
        }

        String question_text = game.getCurrentQuestion();
        String[] answers_text = game.getCurrentAnswers();

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h3>" + question_text + "</h3>");
        out.println("<form method='post' action='" + request.getContextPath() + "/GameServlet'>");
        for (String answer : answers_text) {
            out.println("<label>");
            out.println("<input type='radio' name='answer' value='" + answer + "' required> " + answer);
            out.println("</label><br>");
        }
        if (answers_text != null && answers_text.length > 0) {
            out.println("<button type='submit'>" + AppConstants.CONFIRM_BUTTON + "</button>");
        } else {
            out.println("<button type='submit'>" + AppConstants.RESTART_BUTTON + "</button>");
        }
        out.println("</form>");
        out.println("<h4>" + AppConstants.USER_NAME + sessionUserName + "</h4>");
        out.println("<h4>"+ AppConstants.GAME_COUNT + game.getGameCount() + "</h4>");
        out.println("</body></html>");
    }
}
