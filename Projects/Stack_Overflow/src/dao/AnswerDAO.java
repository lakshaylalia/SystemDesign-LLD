package dao;

import models.Answer;

import java.util.List;

public interface AnswerDAO {
    Answer save(Answer answer);
    Answer getById(Long id);
    List<Answer> getAll();
    List<Answer> getAllPaginated(int page, int size);
    List<Answer> getAnswersForQuestion(Long questionId);
    Answer update(Answer answer);
    void deleteById(Long id);
    long count();
}