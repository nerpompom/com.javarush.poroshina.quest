package com.javarush.poroshina.quest.service;

import com.javarush.poroshina.quest.entity.Question;
import com.javarush.poroshina.quest.repository.GameRepository;

public class GameService {

    private static GameService instance;
    GameRepository gameRepository = GameRepository.getInstance();

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public Question[] getQuestions() {
        Question[] allQuestions = gameRepository.getAllQuestion();
        return allQuestions;
    }

}
