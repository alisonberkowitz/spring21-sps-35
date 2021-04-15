package com.google.sps.data;

import java.util.List;

/** An item on a todo list. */
public final class Quiz {

  private final long quizId;
  private final List<Question> questions;
  private final long timestamp;

  public Quiz(long quizId, List<Question> questions, long timestamp) {
    this.quizId = quizId;
    this.questions = questions;
    this.timestamp = timestamp;
  }
}
