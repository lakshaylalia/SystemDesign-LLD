package dao;

import models.Question;

import java.util.List;

public interface QuestionDAO {
    Question save(Question question);
    Question getById(Long id);
    List<Question> getAll();
    List<Question> getAllPaginated(int page, int size);
    List<Question> getUserQuestions(Long authorId);
    List<Question> getQuestionsByTag(String tag);
    List<Question> searchByKeyword(String keyword);
    Question update(Question question);
    void deleteById(Long id);
    long count();
}