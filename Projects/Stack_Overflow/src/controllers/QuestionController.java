package controllers;

import models.Question;
import services.QuestionService;

import java.util.List;

public class QuestionController {
    private QuestionService questionService;

    public QuestionController() {
        this.questionService = new QuestionService();
    }

    public Question addQuestion(Question question) {
        return questionService.addQuestion(question);
    }

    public Question getQuestionById(Long id) {
        return questionService.getQuestionById(id);
    }

    public List<Question> getUserQuestions(Long userId) {
        return questionService.getUserQuestions(userId);
    }

    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    public Question updateQuestion(Question question) {
        return questionService.updateQuestion(question);
    }

    public void deleteQuestion(Long id) {
        questionService.deleteQuestion(id);
    }
}
