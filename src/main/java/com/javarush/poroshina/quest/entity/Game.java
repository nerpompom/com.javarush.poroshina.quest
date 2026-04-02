package com.javarush.poroshina.quest.entity;

public class Game {

    private int gameCount = 0;
    private boolean isStart;
    private Question[] questions;
    private Answer[] answers;
    private Question currentQuestion;

    public Game(Question[] questions, boolean isStart) {
        this.questions = questions;
        this.isStart = isStart;
    }

    public Game() {
    }

    public int getGameCount() {
        return gameCount;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void incrementGameCount() {
        gameCount++;
    }

    public String getCurrentQuestion() {
        if (isStart) {
            for (Question q : questions) {
                if (q.getId() == 1) {
                    currentQuestion = q;
                    break;
                }
            }
            isStart = false;
        }

        if (currentQuestion.isFinish()) {
            this.setStart(true);
            this.incrementGameCount();
        }
        return currentQuestion.getText();
    }

    public String[] getCurrentAnswers() {
        if (currentQuestion == null) {
            return new String[0];
        }

        Question question = null;
        for (Question current : questions) {
            if (current.getId() == currentQuestion.getId()) {
                question = current;
                break;
            }
        }

        if (question == null || question.getAnswers() == null) {
            return new String[0];
        }

        String[] currentAnswers = new String[question.getAnswers().length];
        for (int i = 0; i < question.getAnswers().length; i++) {
            currentAnswers[i] = question.getAnswers()[i].getText();
        }
        return currentAnswers;
    }

    public Answer findAnswerByText(String text) {
        if (currentQuestion == null || currentQuestion.getAnswers() == null) {
            return null;
        }

        for (Answer answer : currentQuestion.getAnswers()) {
            if (answer.getText().equals(text)) {
                return answer;
            }
        }
        return null;
    }

    public void setCurrentQuestionById(Long questionId) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                currentQuestion = question;
                break;
            }
        }
    }

}
