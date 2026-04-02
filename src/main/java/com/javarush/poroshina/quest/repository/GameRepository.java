package com.javarush.poroshina.quest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.poroshina.quest.constants.AppConstants;
import com.javarush.poroshina.quest.entity.Answer;
import com.javarush.poroshina.quest.entity.Game;
import com.javarush.poroshina.quest.entity.Question;

import java.io.InputStream;
import java.util.Arrays;

public class GameRepository {

    private static GameRepository instance;

    public static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }

    public Question[] getAllQuestion() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream = GameRepository.class
                    .getClassLoader()
                    .getResourceAsStream("questionsAnswers.json");

            if (inputStream == null) {
                throw new RuntimeException(AppConstants.FILE_NOT_FOUND_ERROR);
            }

            Game game = mapper.readValue(inputStream, Game.class);

            Question[] questions = game.getQuestions();
            Answer[] answers = game.getAnswers();

            for (Question question : questions) {
                Answer[] questionAnswers = Arrays.stream(question.getAnswerIds())
                        .mapToObj(answerId ->
                                Arrays.stream(answers)
                                        .filter(answer -> answer.getId() == answerId)
                                        .findFirst()
                                        .orElse(null))
                        .filter(answer -> answer != null)
                        .toArray(Answer[]::new);

                question.setAnswers(questionAnswers);
            }
            return questions;
        } catch (Exception e) {
            throw new RuntimeException(AppConstants.READING_FILE_ERROR, e);
        }
    }
}
