package com.google.sps.data;

/** An item on a todo list. */
public final class Quiz {

  private final long quizId;
  private final Question questions;
  private final long timestamp;

  public Quiz(long quizId, Question questions, long timestamp) {
    this.quizId = quizId;
    this.questions = questions;
    this.timestamp = timestamp;
  }
}