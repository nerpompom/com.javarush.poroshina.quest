package com.javarush.poroshina.quest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

    private long id;
    private String text;
    private long[] answerIds;
    private Answer[] answers;
    @JsonProperty("isFinish")
    private boolean finish;

    public long getId() {
        return id;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public long[] getAnswerIds() {
        return answerIds;
    }

    public boolean isFinish() {
        return finish;
    }
}
