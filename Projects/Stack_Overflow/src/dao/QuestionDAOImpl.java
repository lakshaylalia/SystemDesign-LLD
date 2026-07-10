package dao;

import models.Question;
import repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDAOImpl implements QuestionDAO {

    private final QuestionRepository questionRepository;

    public QuestionDAOImpl() {
        this.questionRepository = QuestionRepository.getInstance();
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.getById(id);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public List<Question> getAllPaginated(int page, int size) {
        List<Question> allQuestions = questionRepository.getAll();
        int start = page * size;
        int end = Math.min(start + size, allQuestions.size());

        if (start >= allQuestions.size()) {
            return new ArrayList<>();
        }

        return allQuestions.subList(start, end);
    }

    @Override
    public List<Question> getUserQuestions(Long authorId) {
        return questionRepository.getUserQuestions(authorId);
    }

    @Override
    public List<Question> getQuestionsByTag(String tag) {
        String normalizedTag = tag.toLowerCase();
        List<Question> result = new ArrayList<>();
        for (Question q : questionRepository.getAll()) {
            if (q.getTags().contains(normalizedTag)) {
                result.add(q);
            }
        }
        return result;
    }

    @Override
    public List<Question> searchByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        List<Question> result = new ArrayList<>();
        for (Question q : questionRepository.getAll()) {
            if (q.getStatement().toLowerCase().contains(lowerKeyword)) {
                result.add(q);
            }
        }
        return result;
    }

    @Override
    public Question update(Question question) {
        return questionRepository.update(question);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public long count() {
        return questionRepository.getAll().size();
    }
}