package dao;

import models.Answer;
import repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {

    private final AnswerRepository answerRepository;

    public AnswerDAOImpl() {
        this.answerRepository = AnswerRepository.getInstance();
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer getById(Long id) {
        return answerRepository.getById(id);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.getAll();
    }

    @Override
    public List<Answer> getAllPaginated(int page, int size) {
        List<Answer> allAnswers = answerRepository.getAll();
        int start = page * size;
        int end = Math.min(start + size, allAnswers.size());

        if (start >= allAnswers.size()) {
            return new ArrayList<>();
        }

        return allAnswers.subList(start, end);
    }

    @Override
    public List<Answer> getAnswersForQuestion(Long questionId) {
        return answerRepository.getAnswersForQuestion(questionId);
    }

    @Override
    public Answer update(Answer answer) {
        return answerRepository.update(answer);
    }

    @Override
    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public long count() {
        return answerRepository.getAll().size();
    }
}