package controllers;

import dto.QuestionDTO;
import dto.PagedResult;
import mapper.DTOMapper;
import models.Question;
import services.QuestionService;
import services.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionController {
    private QuestionService questionService;
    private AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public QuestionDTO addQuestion(Question question) {
        Question saved = questionService.addQuestion(question);
        return DTOMapper.toQuestionDTO(saved, 0);
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionService.getQuestionById(id);
        int answerCount = answerService.getAnswersForQuestion(id).size();
        return DTOMapper.toQuestionDTO(question, answerCount);
    }

    public List<QuestionDTO> getUserQuestions(Long userId) {
        List<Question> questions = questionService.getUserQuestions(userId);
        return questions.stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());
    }

    public PagedResult<QuestionDTO> getQuestionsPaginated(int page, int size) {
        PagedResult<Question> pagedQuestions = questionService.getQuestionsPaginated(page, size);

        List<QuestionDTO> dtos = pagedQuestions.getData().stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());

        return new PagedResult<>(dtos, page, size, pagedQuestions.getTotalItems());
    }

    public QuestionDTO updateQuestion(Question question) {
        Question updated = questionService.updateQuestion(question);
        int answerCount = answerService.getAnswersForQuestion(updated.getId()).size();
        return DTOMapper.toQuestionDTO(updated, answerCount);
    }

    public void deleteQuestion(Long questionId, Long requestingUserId) {
        questionService.deleteQuestion(questionId, requestingUserId);
    }

    public void upvoteQuestion(Long questionId, Long userId) {
        questionService.upvoteQuestion(questionId, userId);
    }

    public void downvoteQuestion(Long questionId, Long userId) {
        questionService.downvoteQuestion(questionId, userId);
    }

    public void addNewTag(Long questionId, String tag) {
        questionService.addNewTag(questionId, tag);
    }

    public void removeTag(Long questionId, String tag) {
        questionService.removeTag(questionId, tag);
    }

    public List<QuestionDTO> getQuestionsByTag(String tag) {
        List<Question> questions = questionService.getQuestionsByTag(tag);
        return questions.stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> searchByKeyword(String keyword) {
        List<Question> questions = questionService.searchByKeyword(keyword);
        return questions.stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());
    }

    public List<QuestionDTO> getAllQuestionsSorted() {
        List<Question> questions = questionService.getAllQuestionsSorted();
        return questions.stream()
                .map(q -> {
                    int count = answerService.getAnswersForQuestion(q.getId()).size();
                    return DTOMapper.toQuestionDTO(q, count);
                })
                .collect(Collectors.toList());
    }

}