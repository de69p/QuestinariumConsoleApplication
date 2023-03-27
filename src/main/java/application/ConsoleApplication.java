package application;

import model.Question;
import repository.ConnectionSingleton;
import repository.QuestionRepositoryImp;
import repository.dao.QuestionRepository;
import service.QuestionService;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleApplication {

    private static final String ADD_QUESTION_COMMAND = "add";
    private static final String DELETE_QUESTION_COMMAND = "delete";
    private static final String DISPLAY_RANDOM_QUESTION_COMMAND = "random";
    private static final String EXIT_COMMAND = "exit";

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static QuestionService questionService;

    public static void main(String[] args) {
        QuestionRepository questionRepository = new QuestionRepositoryImp(ConnectionSingleton.getConnection());
        questionService = new QuestionService(questionRepository);

        System.out.println("Welcome to Questinarium!");

        String command;
        do {
            System.out.println("Enter a command: add, delete, random, exit");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case ADD_QUESTION_COMMAND:
                    addQuestion();
                    break;
                case DELETE_QUESTION_COMMAND:
                    deleteQuestion();
                    break;
                case DISPLAY_RANDOM_QUESTION_COMMAND:
                    getRandomQuestionByTopic();
                    break;
                case EXIT_COMMAND:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown command, please try again.");
                    break;
            }
        } while (!command.equals(EXIT_COMMAND));
    }

    private static void addQuestion() {
        System.out.println("Enter the question text:");
        String text = scanner.nextLine().trim();

        System.out.println("Enter the question topic:");
        String topic = scanner.nextLine().trim();

        Question question = new Question(text,topic);
        questionService.addQuestion(question);

        System.out.println("Question added to the database.");
    }

    private static void deleteQuestion() {
        System.out.println("Enter the question id:");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Question question = questionService.getQuestionById(id);
        if (question == null) {
            System.out.println("Question not found in the database.");
        } else {
            questionService.deleteQuestion(question.getId());
            System.out.println("Question deleted from the database.");
        }
    }

    private static void getRandomQuestionByTopic() {
        System.out.println("Enter the question topic:");
        String topic = scanner.nextLine().trim();

        List<Question> questions = questionService.getQuestionByTopic(topic);
        if (questions.isEmpty()) {
            System.out.println("No questions found for the topic in the database.");
        } else {
            int randomIndex = random.nextInt(questions.size());
            Question randomQuestion = questions.get(randomIndex);
            System.out.println("Random question:\n" + randomQuestion.getText());
        }
    }

}

