package controllers;

import dao.AnswerDAOImpl;
import dao.QuestionDAO;
import dto.AnswerDTO;
import dto.PagedResult;
import mapper.DTOMapper;
import models.Answer;
import services.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerController {
    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    public AnswerDTO addAnswer(Answer answer) {
        Answer saved = answerService.addAnswer(answer);
        return DTOMapper.toAnswerDTO(saved);
    }

    public AnswerDTO getAnswerById(Long id) {
        Answer answer = answerService.getAnswerById(id);
        return DTOMapper.toAnswerDTO(answer);
    }

    public List<AnswerDTO> getAnswersForQuestion(Long questionId) {
        List<Answer> answers = answerService.getAnswersForQuestion(questionId);
        return answers.stream()
                .map(DTOMapper::toAnswerDTO)
                .collect(Collectors.toList());
    }

    public List<AnswerDTO> getAllAnswers() {
        List<Answer> answers = answerService.getAllAnswers();
        return answers.stream()
                .map(DTOMapper::toAnswerDTO)
                .collect(Collectors.toList());
    }

    public PagedResult<AnswerDTO> getAnswersPaginated(int page, int size) {
        PagedResult<Answer> pagedAnswers = answerService.getAnswersPaginated(page, size);

        List<AnswerDTO> dtos = pagedAnswers.getData().stream()
                .map(DTOMapper::toAnswerDTO)
                .collect(Collectors.toList());

        return new PagedResult<>(dtos, page, size, pagedAnswers.getTotalItems());
    }

    public AnswerDTO updateAnswer(Answer answer) {
        Answer updated = answerService.updateAnswer(answer);
        return DTOMapper.toAnswerDTO(updated);
    }

    public void deleteAnswer(Long answerId, Long requestingUserId) {
        answerService.deleteAnswer(answerId, requestingUserId);
    }

    public void upvoteAnswer(Long answerId, Long userId) {
        answerService.upvoteAnswer(answerId, userId);
    }

    public void downvoteAnswer(Long answerId, Long userId) {
        answerService.downvoteAnswer(answerId, userId);
    }

    public List<AnswerDTO> getAnswersSorted(Long questionId) {
        List<Answer> answers = answerService.getAllAnswersSorted(questionId);
        return answers.stream()
                .map(DTOMapper::toAnswerDTO)
                .collect(Collectors.toList());
    }

    public void acceptAnswer(Long answerId, Long questionId, Long userId) {
        answerService.acceptAnswer(answerId, questionId, userId);
    }

    public void rejectAnswer(Long answerId, Long questionId, Long userId) {
        answerService.rejectAnswer(answerId, questionId, userId);
    }
}