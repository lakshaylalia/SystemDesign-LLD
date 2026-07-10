import models.*;
import services.*;
import dao.*;
import repository.*;
import dto.*;
import strategy.*;
import events.*;

import java.util.List;

 void main() {

        // Initialize DAOs
        UserDAO userDAO = new UserDAOImpl();
        QuestionDAO questionDAO = new QuestionDAOImpl();
        AnswerDAO answerDAO = new AnswerDAOImpl();

        // Initialize Services
        UserService userService = new UserService();
        QuestionService questionService = new QuestionService(questionDAO, userService);
        AnswerService answerService = new AnswerService(answerDAO, questionDAO, userService);

        // Setup event system for reputation
        ReputationService reputationService = new ReputationService(userService);
        questionService.addObserver(reputationService);
        answerService.addObserver(reputationService);

        // Set sort strategy
        questionService.setQuestionSortStrategy(new SortByVotesStrategy<>());
        answerService.setAnswerSortStrategy(new SortByVotesStrategy<>());

        System.out.println("=== Stack Overflow Demo ===\n");

        // 1. Create Users
        System.out.println("1. Creating users...");
        User lakshay = userService.addUser(new User("Lakshay"));
        User onam = userService.addUser(new User("Onam"));
        User rajat = userService.addUser(new User("Rajat"));
        System.out.println("Created: " + lakshay);
        System.out.println("Created: " + onam);
        System.out.println("Created: " + rajat);

        // 2. Create a Question
        System.out.println("\n2. Lakshay asks a question...");
        Question q1 = new Question(lakshay, "How do I use HashMap in Java?");
        q1.addTag("java");
        q1.addTag("hashmap");
        q1 = questionService.addQuestion(q1);
        System.out.println("Question created: ID=" + q1.getId());
        System.out.println("Tags: " + q1.getTags());

        // 3. Onam answers the question
        System.out.println("\n3. Onam answers the question...");
        Answer a1 = new Answer(q1, onam, "You can use HashMap like this: Map<String, Integer> map = new HashMap<>();");
        a1 = answerService.addAnswer(a1);
        System.out.println("Answer created: ID=" + a1.getId());

        // 4. Rajat answers too
        System.out.println("\n4. Rajat answers the question...");
        Answer a2 = new Answer(q1, rajat, "Also check out ConcurrentHashMap for thread safety.");
        a2 = answerService.addAnswer(a2);
        System.out.println("Answer created: ID=" + a2.getId());

        // 5. Voting
        System.out.println("\n5. Voting on question...");
        questionService.upvoteQuestion(q1.getId(), onam.getId());
        questionService.upvoteQuestion(q1.getId(), rajat.getId());
        System.out.println("Question upvotes: " + q1.getUpvotes());

        System.out.println("\n6. Voting on answers...");
        answerService.upvoteAnswer(a1.getId(), lakshay.getId());
        answerService.upvoteAnswer(a1.getId(), rajat.getId());
        System.out.println("Answer 1 upvotes: " + a1.getUpvotes());

        // 6. Check reputation after voting
        System.out.println("\n7. User reputations after voting:");
        System.out.println(lakshay);
        System.out.println(onam);
        System.out.println(rajat);

        // 7. Lakshay accepts Onam's answer
        System.out.println("\n8. Lakshay accepts Onam's answer...");
        answerService.acceptAnswer(a1.getId(), q1.getId(), lakshay.getId());
        System.out.println("Answer accepted: " + a1.isAccepted());

        // 8. Check reputation after accepting
        System.out.println("\n9. User reputations after answer accepted:");
        System.out.println(lakshay);
        System.out.println(onam);
        System.out.println(rajat);

        // 9. Try to vote again (should fail)
        System.out.println("\n10. Testing vote tracking (trying to vote twice)...");
        try {
            questionService.upvoteQuestion(q1.getId(), onam.getId());
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        // 10. Get answers sorted by votes
        System.out.println("\n11. Answers sorted by votes:");
        List<Answer> sortedAnswers = answerService.getAllAnswersSorted(q1.getId());
        for (Answer a : sortedAnswers) {
            System.out.println("  - Answer " + a.getId() + ": " + a.getUpvotes() + " upvotes, accepted=" + a.isAccepted());
        }

        // 11. Get all questions
        System.out.println("\n12. All questions:");
        List<Question> allQuestions = questionService.getAllQuestions();
        for (Question q : allQuestions) {
            System.out.println("  - Q" + q.getId() + ": " + q.getStatement() + " (votes: " + q.getUpvotes() + ")");
        }

        // 12. Get questions by tag
        System.out.println("\n13. Questions by tag 'java':");
        List<Question> javaQuestions = questionService.getQuestionsByTag("java");
        for (Question q : javaQuestions) {
            System.out.println("  - Q" + q.getId() + ": " + q.getStatement());
        }

        // 13. Search questions
        System.out.println("\n14. Search by keyword 'HashMap':");
        List<Question> searchResults = questionService.searchByKeyword("HashMap");
        for (Question q : searchResults) {
            System.out.println("  - Q" + q.getId() + ": " + q.getStatement());
        }

        // 14. Get top users
        System.out.println("\n15. Top users by reputation:");
        List<User> topUsers = userService.getTopUsersByReputation(3);
        for (User u : topUsers) {
            System.out.println("  - " + u.getUsername() + ": " + u.getReputation() + " (" + u.getBadgeTier() + ")");
        }

        // 15. Add comment to question
        System.out.println("\n16. Adding comment to question...");
        Comment comment = new Comment(onam, "Great question! I'm also interested in this.");
        q1.addComment(comment);
        System.out.println("Comment added: " + comment.getContent());
        System.out.println("Comments on question: " + q1.getComments().size());

        System.out.println("\n=== Demo Complete ===");
 }