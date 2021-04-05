package com.google.sps.data;

/** An item on a todo list. */
public final class Question {

  private final long id;
  private final String question;
  private final String answerOne;
  private final String answerTwo;
  private final String answerThree;
  private final String correctAnswer;
  private final long timestamp;

  public Question(long id, String question, String answerOne, String answerTwo, String answerThree, String correctAnswer, long timestamp) {
    this.id = id;
    this.question = question;
    this.answerOne = answerOne;
    this.answerTwo = answerTwo;
    this.answerThree = answerThree;
    this.correctAnswer = correctAnswer;
    this.timestamp = timestamp;
  }
}