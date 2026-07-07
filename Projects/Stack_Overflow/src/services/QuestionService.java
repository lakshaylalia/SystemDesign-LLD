package services;

import models.Question;
import repository.QuestionRepository;

import java.util.List;

public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService() {
        this.questionRepository = QuestionRepository.getInstance();
    }

    public Question addQuestion(Question question) {
        return questionRepository.saveQuestion(question);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.getQuestionById(id);
    }

    public List<Question> getUserQuestions(Long authorId) {
        return questionRepository.getUserQuestions(authorId);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    public Question updateQuestion(Question question) {
        return questionRepository.updateQuestion(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteQuestion(id);
    }
}