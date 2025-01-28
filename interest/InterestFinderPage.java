package interest;

import java.util.*;
import javax.swing.*;

public class InterestFinderPage {
    public static JFrame frame;
    public static int currentQuestionIndex = 0;
    public static String selectedAgeGroup;

    public static final String[] questionsBelow10 = {
            "Do you enjoy solving puzzles?", "Do you like observing stars and planets?", "Do you enjoy drawing and coloring?",
            "Do you like playing with building blocks?", "Do you enjoy learning about animals?", "Do you like playing memory games?",
            "Do you enjoy reading stories?", "Do you like experimenting with mixing colors?", "Do you enjoy building things with clay or playdough?",
            "Do you like counting objects?", "Do you enjoy playing pretend games?", "Do you like nature walks?",
            "Do you enjoy playing strategy games?", "Do you like listening to music?", "Do you enjoy drawing shapes and patterns?"
    };

    public static final String[] questions10to15 = {
            "Do you enjoy science projects?", "Do you like helping others?", "Do you enjoy reading?",
            "Do you like playing sports?", "Do you enjoy technology?", "Do you like learning about space?",
            "Do you enjoy volunteering?", "Do you like participating in theater?", "Do you enjoy working in groups?",
            "Do you like creating your own games?", "Do you enjoy exploring new places?", "Do you like listening to music?",
            "Do you enjoy cooking or baking?","Do you like playing strategy games?", "Do you enjoy discussing current events?"
    };

    public static final String[] questionsAbove15 = {
            "Do you like coding?", "Do you enjoy group projects?", "Are you interested in psychology?",
            "Do you like working with numbers?","Do you enjoy creative writing?",
            "Do you like studying history?", "Do you enjoy designing websites?",
            "Do you like leading a team?", "Do you enjoy attending workshops or seminars?",
            "Are you interested in entrepreneurship?","Do you like exploring different cultures?",
            "Do you enjoy participating in debates?", "Do you like watching documentaries?",
            "Do you enjoy attending art shows?","Do you like volunteering for community service?"
    };

    public static final Map<String, Integer> categoryScores = new HashMap<>();

    static {
        categoryScores.put("Logic", 0);
        categoryScores.put("Arts", 0);
        categoryScores.put("Biology", 0);
        categoryScores.put("Engineering", 0);
        categoryScores.put("Mathematics", 0);
        categoryScores.put("Sports and Outdoors", 0);
        categoryScores.put("Science", 0);
        categoryScores.put("Social Services", 0);
        categoryScores.put("Technology", 0);
        categoryScores.put("Business", 0);
        categoryScores.put("Creativity", 0);
    }

    public static final Map<String, String> questionCategories = new HashMap<>();

    static {
        questionCategories.put("Do you enjoy solving puzzles?", "Logic");
        questionCategories.put("Do you like observing stars and planets?", "Science");
        questionCategories.put("Do you enjoy drawing and coloring?", "Arts");
        questionCategories.put("Do you like playing with building blocks?", "Engineering");
        questionCategories.put("Do you enjoy learning about animals?", "Biology");
        questionCategories.put("Do you like playing memory games?", "Logic");
        questionCategories.put("Do you enjoy reading stories?", "Arts");
        questionCategories.put("Do you like experimenting with mixing colors?", "Science");
        questionCategories.put("Do you enjoy building things with clay or playdough?", "Engineering");
        questionCategories.put("Do you like counting objects?", "Mathematics");
        questionCategories.put("Do you enjoy playing pretend games?", "Creativity");
        questionCategories.put("Do you like nature walks?", "Biology");
        questionCategories.put("Do you enjoy playing strategy games?", "Logic");
        questionCategories.put("Do you like listening to music?", "Arts");
        questionCategories.put("Do you enjoy drawing shapes and patterns?", "Mathematics");

        questionCategories.put("Do you enjoy science projects?", "Science");
        questionCategories.put("Do you like helping others?", "Social Services");
        questionCategories.put("Do you enjoy reading?", "Arts");
        questionCategories.put("Do you like playing sports?", "Sports and Outdoors");
        questionCategories.put("Do you enjoy technology?", "Technology");
        questionCategories.put("Do you like learning about space?", "Science");
        questionCategories.put("Do you enjoy volunteering?", "Social Services");
        questionCategories.put("Do you like participating in theater?", "Arts");
        questionCategories.put("Do you enjoy working in groups?", "Social Services");
        questionCategories.put("Do you like creating your own games?", "Technology");
        questionCategories.put("Do you enjoy exploring new places?", "Biology");
        questionCategories.put("Do you like listening to music?", "Arts");
        questionCategories.put("Do you enjoy cooking or baking?", "Arts");
        questionCategories.put("Do you like playing strategy games?", "Mathematics");
        questionCategories.put("Do you enjoy discussing current events?", "Social Services");

        questionCategories.put("Do you like coding?", "Technology");
        questionCategories.put("Do you enjoy group projects?", "Social Services");
        questionCategories.put("Are you interested in psychology?", "Social Services");
        questionCategories.put("Do you like working with numbers?", "Mathematics");
        questionCategories.put("Do you enjoy creative writing?", "Arts");
        questionCategories.put("Do you like studying history?", "Arts");
        questionCategories.put("Do you enjoy designing websites?", "Technology");
        questionCategories.put("Do you like leading a team?", "Business");
        questionCategories.put("Do you enjoy attending workshops or seminars?", "Science");
        questionCategories.put("Are you interested in entrepreneurship?", "Business");
        questionCategories.put("Do you like exploring different cultures?", "Social Services");
        questionCategories.put("Do you enjoy participating in debates?", "Arts");
        questionCategories.put("Do you like watching documentaries?", "Biology");
        questionCategories.put("Do you enjoy attending art shows?", "Arts");
        questionCategories.put("Do you like volunteering for community service?", "Social Services");
    }

    public static void main(String[] args) {
        showAgeSelectionPage();
    }

    public static void showAgeSelectionPage() {
        frame = new JFrame("Interest Finder");
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("Interest Finder", SwingConstants.CENTER);
        titleLabel.setBounds(100, 50, 200, 30);
        frame.add(titleLabel);

        JButton btnBelow10 = new JButton("Students below 10");
        btnBelow10.setBounds(100, 130, 200, 40);
        btnBelow10.addActionListener(e -> {
            selectedAgeGroup = "Below 10";
            currentQuestionIndex = 0; // Reset the question index
            showQuestionPage(questionsBelow10);
        });
        frame.add(btnBelow10);

        JButton btn10to15 = new JButton("Students between 10 and 15");
        btn10to15.setBounds(100, 200, 200, 40);
        btn10to15.addActionListener(e -> {
            selectedAgeGroup = "Between 10 and 15";
            currentQuestionIndex = 0; // Reset the question index
            showQuestionPage(questions10to15);
        });
        frame.add(btn10to15);

        JButton btnAbove15 = new JButton("Students above 15");
        btnAbove15.setBounds(100, 270, 200, 40);
        btnAbove15.addActionListener(e -> {
            selectedAgeGroup = "Above 15";
            currentQuestionIndex = 0; // Reset the question index
            showQuestionPage(questionsAbove15);
        });
        frame.add(btnAbove15);

        frame.setVisible(true);
    }

    public static void showQuestionPage(String[] questions) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel questionLabel = new JLabel(questions[currentQuestionIndex]);
        questionLabel.setBounds(60, 80, 300, 30);
        frame.add(questionLabel);

        String[] answers = {"Strongly Like", "Like", "Neutral", "Dislike"};
        int yOffset = 150;
        for (String answer : answers) {
            JButton answerButton = new JButton(answer);
            answerButton.setBounds(50, yOffset, 300, 30);
            frame.add(answerButton);

            answerButton.addActionListener(e -> {
                addScore(questions[currentQuestionIndex], answer);
                if (currentQuestionIndex < questions.length - 1) {
                    currentQuestionIndex++;
                    showQuestionPage(questions);
                } else {
                    showResultsPage();
                }
            });

            yOffset += 50;
        }

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(120, yOffset + 150, 150, 30);
        backButton.addActionListener(e -> showAgeSelectionPage());
        frame.add(backButton);

        frame.setVisible(true);
    }

    public static void addScore(String question, String answer) {
        String category = questionCategories.get(question);
        int points = switch (answer) {
            case "Strongly Like" -> 3;
            case "Like" -> 2;
            case "Neutral" -> 1;
            default -> 0;
        };

        categoryScores.put(category, categoryScores.get(category) + points);
    }

    public static void showResultsPage() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel resultLabel = new JLabel("Your Top Interests");
        resultLabel.setBounds(150, 50, 200, 30);
        frame.add(resultLabel);


        List<String> topInterests = getTopInterests();
        int yOffset = 100;


        for (String interest : topInterests) {
            JButton interestButton = new JButton(interest);
            interestButton.setBounds(50, yOffset, 300, 30);
            interestButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Interest: " + interest);
            });
            frame.add(interestButton);
            yOffset += 45;
        }

        JLabel thankYouLabel = new JLabel("Thank you for using Interest Finder!");
        thankYouLabel.setBounds(50, yOffset+20, 300, 40);
        frame.add(thankYouLabel);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(120, yOffset + 180, 150, 30);
        backButton.addActionListener(e -> showAgeSelectionPage());
        frame.add(backButton);

        frame.setVisible(true);
    }


    public static List<String> getTopInterests() {
        return categoryScores.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }
}