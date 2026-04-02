package com.javarush.poroshina.quest.entity;

public class Answer {

    public long id;
    public String text;
    public Long nextQuestionId;

    public Long getNextQuestionId() {
        return nextQuestionId;
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }
}
