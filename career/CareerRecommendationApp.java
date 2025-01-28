package career;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;


public class CareerRecommendationApp {

    public JFrame frame;
    public JFrame resultFrame;
    public JPanel interestPanel;
    public JButton predictButton;

    // Map of interests to careers with points for each career based on interests
    public static final Map<String, Map<String, Integer>> interestToCareerPointsMap = new HashMap<>();

    // Map of careers to skills
    public static final Map<String, List<String>> careerToSkillsMap = new HashMap<>();

    // Map of careers to courses
    public static final Map<String, List<String>> careerToCoursesMap = new HashMap<>();

    // Map of careers to links
    public static final Map<String, String> careerToLinkMap = new HashMap<>();

    static {
        // Mapping of interests to careers with points
        // Interest to Career Points Mapping with Overlapping Careers
        interestToCareerPointsMap.put("Mathematics", Map.of("Data Scientist", 5, "Statistician", 4, "Engineer", 3, "Financial Analyst", 4));
        interestToCareerPointsMap.put("Programming", Map.of("Software Developer", 5, "Computer Scientist", 4, "Data Scientist", 4, "Cybersecurity Specialist", 3));
        interestToCareerPointsMap.put("Statistics", Map.of("Statistician", 5, "Data Scientist", 4, "Market Analyst", 3));
        interestToCareerPointsMap.put("Machine Learning", Map.of("Data Scientist", 5, "Machine Learning Engineer", 4, "AI Researcher", 4));
        interestToCareerPointsMap.put("Physics", Map.of("Physicist", 5, "Mechanical Engineer", 4, "Aerospace Engineer", 3));
        interestToCareerPointsMap.put("Chemistry", Map.of("Chemist", 5, "Chemical Engineer", 4, "Pharmacologist", 3));
        interestToCareerPointsMap.put("Biology", Map.of("Biologist", 5, "Medical Researcher", 4, "Environmental Scientist", 3));
        interestToCareerPointsMap.put("Economics", Map.of("Economist", 5, "Financial Analyst", 4, "Market Researcher", 3));
        interestToCareerPointsMap.put("Computer Science", Map.of("Software Developer", 5, "Computer Scientist", 4, "AI Researcher", 3, "Cybersecurity Specialist", 4));
        interestToCareerPointsMap.put("Engineering", Map.of("Mechanical Engineer", 5, "Civil Engineer", 4, "Electrical Engineer", 3));
        interestToCareerPointsMap.put("Art", Map.of("Graphic Designer", 5, "Animator", 4, "Art Director", 3));
        interestToCareerPointsMap.put("Music", Map.of("Music Producer", 5, "Composer", 4, "Sound Engineer", 3));

// Expanded Career to Skills Mapping for Overlapping Careers
        careerToSkillsMap.put("Data Scientist", Arrays.asList("Data Analysis", "Machine Learning", "Statistics"));
        careerToSkillsMap.put("Statistician", Arrays.asList("Mathematics", "Data Interpretation", "Probability"));
        careerToSkillsMap.put("Software Developer", Arrays.asList("Programming", "Algorithms", "Problem-Solving"));
        careerToSkillsMap.put("Computer Scientist", Arrays.asList("Data Structures", "Systems Programming", "Theory of Computation"));
        careerToSkillsMap.put("Cybersecurity Specialist", Arrays.asList("Network Security", "Encryption", "Risk Assessment"));
        careerToSkillsMap.put("AI Researcher", Arrays.asList("Machine Learning", "Neural Networks", "Python Programming"));
        careerToSkillsMap.put("Machine Learning Engineer", Arrays.asList("Machine Learning Algorithms", "Python Programming", "Data Analysis"));
        careerToSkillsMap.put("Mechanical Engineer", Arrays.asList("Design Skills", "Analytical Skills", "Project Management"));
        careerToSkillsMap.put("Chemist", Arrays.asList("Laboratory Techniques", "Chemical Analysis", "Critical Thinking"));
        careerToSkillsMap.put("Physicist", Arrays.asList("Research Skills", "Analytical Thinking", "Physics Theory"));
        careerToSkillsMap.put("Financial Analyst", Arrays.asList("Financial Modeling", "Excel Skills", "Attention to Detail"));
        careerToSkillsMap.put("Economist", Arrays.asList("Analytical Thinking", "Data Analysis", "Economic Theory"));
        careerToSkillsMap.put("Graphic Designer", Arrays.asList("Creativity", "Attention to Detail", "Software Proficiency"));
        careerToSkillsMap.put("Music Producer", Arrays.asList("Sound Editing", "Creativity", "Attention to Detail"));

// Expanded Career to Courses Mapping for Overlapping Careers
        careerToCoursesMap.put("Data Scientist", Arrays.asList("Data Science Fundamentals", "Advanced Statistics", "Machine Learning"));
        careerToCoursesMap.put("Statistician", Arrays.asList("Statistical Methods", "Probability Theory", "Applied Mathematics"));
        careerToCoursesMap.put("Software Developer", Arrays.asList("Java Programming", "Software Engineering", "Algorithms"));
        careerToCoursesMap.put("Computer Scientist", Arrays.asList("Computer Science Principles", "Data Structures", "Operating Systems"));
        careerToCoursesMap.put("Cybersecurity Specialist", Arrays.asList("Cybersecurity Basics", "Network Security", "Ethical Hacking"));
        careerToCoursesMap.put("AI Researcher", Arrays.asList("Machine Learning", "Deep Learning", "Artificial Intelligence"));
        careerToCoursesMap.put("Machine Learning Engineer", Arrays.asList("Python for Data Science", "Machine Learning Basics", "Deep Learning Techniques"));
        careerToCoursesMap.put("Mechanical Engineer", Arrays.asList("Mechanical Engineering Principles", "Project Management", "CAD Design"));
        careerToCoursesMap.put("Chemist", Arrays.asList("Organic Chemistry", "Analytical Chemistry", "Laboratory Techniques"));
        careerToCoursesMap.put("Physicist", Arrays.asList("Classical Mechanics", "Quantum Physics", "Research Methods"));
        careerToCoursesMap.put("Financial Analyst", Arrays.asList("Financial Accounting", "Investment Analysis", "Corporate Finance"));
        careerToCoursesMap.put("Economist", Arrays.asList("Microeconomics", "Macroeconomics", "Econometrics"));
        careerToCoursesMap.put("Graphic Designer", Arrays.asList("Graphic Design Basics", "Typography", "Digital Illustration"));
        careerToCoursesMap.put("Music Producer", Arrays.asList("Music Production Basics", "Sound Editing", "Audio Engineering"));

// Expanded Career to Link Mapping for Overlapping Careers
        careerToLinkMap.put("Data Scientist", "https://www.coursera.org/specializations/jhu-data-science");
        careerToLinkMap.put("Statistician", "https://www.coursera.org/specializations/statistics");
        careerToLinkMap.put("Software Developer", "https://www.udacity.com/course/full-stack-web-developer-nanodegree--nd0044");
        careerToLinkMap.put("Computer Scientist", "https://www.edx.org/professional-certificate/harvardx-computer-science");
        careerToLinkMap.put("Cybersecurity Specialist", "https://www.coursera.org/specializations/cyber-security");
        careerToLinkMap.put("AI Researcher", "https://www.coursera.org/specializations/machine-learning");
        careerToLinkMap.put("Machine Learning Engineer", "https://www.udacity.com/course/machine-learning-engineer-nanodegree--nd009t");
        careerToLinkMap.put("Mechanical Engineer", "https://www.coursera.org/specializations/mechanical-engineering");
        careerToLinkMap.put("Chemist", "https://www.edx.org/course/chemistry");
        careerToLinkMap.put("Physicist", "https://www.coursera.org/courses?query=physics");
        careerToLinkMap.put("Financial Analyst", "https://www.udemy.com/course/financial-analyst-skills/");
        careerToLinkMap.put("Economist", "https://www.coursera.org/specializations/economics");
        careerToLinkMap.put("Graphic Designer", "https://www.udemy.com/course/graphic-design-bootcamp/");
        careerToLinkMap.put("Music Producer", "https://www.coursera.org/learn/music-production");
    }

    public CareerRecommendationApp() {
        frame = new JFrame("Interest-Based Career Predictor");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(178, 215, 226));

        JLabel title = new JLabel("Select Your Interests", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(title, BorderLayout.NORTH);

        interestPanel = new JPanel();
        interestPanel.setLayout(new GridLayout(interestToCareerPointsMap.size(), 1));
        interestPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        List<JCheckBox> interestCheckBoxes = new ArrayList<>();

        for (String interest : interestToCareerPointsMap.keySet()) {
            JCheckBox checkBox = new JCheckBox(interest);
            checkBox.setBackground(Color.WHITE);
            interestCheckBoxes.add(checkBox);
            interestPanel.add(checkBox);
        }
        frame.add(interestPanel, BorderLayout.CENTER);

        predictButton = new JButton("Predict Careers");
        predictButton.setFont(new Font("Arial", Font.PLAIN, 16));
        predictButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedInterests = new ArrayList<>();
                for (JCheckBox checkBox : interestCheckBoxes) {
                    if (checkBox.isSelected()) {
                        selectedInterests.add(checkBox.getText());
                    }
                }
                showResultPage(selectedInterests);
            }
        });
        frame.add(predictButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void showResultPage(List<String> selectedInterests) {
        resultFrame = new JFrame("Career Recommendations");
        resultFrame.setSize(400, 700);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.getContentPane().setBackground(Color.WHITE);

        JLabel resultLabel = new JLabel("Recommended Careers", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultFrame.add(resultLabel, BorderLayout.NORTH);

        JPanel careersPanel = new JPanel();
        careersPanel.setLayout(new BoxLayout(careersPanel, BoxLayout.Y_AXIS));
        careersPanel.setBackground(Color.WHITE);
        careersPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (selectedInterests.isEmpty()) {
            JLabel noSelectionLabel = new JLabel("Please select at least one interest!", SwingConstants.CENTER);
            noSelectionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            careersPanel.add(noSelectionLabel);
        } else {
            Map<String, Integer> careerPointsMap = new HashMap<>();
            for (String interest : selectedInterests) {
                Map<String, Integer> careers = interestToCareerPointsMap.get(interest);
                if (careers != null) {
                    for (Map.Entry<String, Integer> entry : careers.entrySet()) {
                        String career = entry.getKey();
                        int points = entry.getValue();
                        careerPointsMap.put(career, careerPointsMap.getOrDefault(career, 0) + points);
                    }
                }
            }

            careerPointsMap = sortByValueDescending(careerPointsMap);

            for (Map.Entry<String, Integer> entry : careerPointsMap.entrySet()) {
                String career = entry.getKey();
                int points = entry.getValue();
                JPanel careerPanel = new JPanel();
                careerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                careerPanel.setBackground(new Color(230, 240, 255));
                careerPanel.setMaximumSize(new Dimension(350, 80));
                careerPanel.setLayout(new BorderLayout());

                JLabel careerLabel = new JLabel(career + " (" + points + " points)");
                careerLabel.setFont(new Font("Arial", Font.BOLD, 14));
                careerPanel.add(careerLabel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                JButton skillsButton = new JButton("Show Skills");
                skillsButton.addActionListener(e -> showSkillsPage(career));

                JButton coursesButton = new JButton("Show Courses");
                coursesButton.addActionListener(e -> showCoursesPage(career));

                JButton linkButton = new JButton("More Info");
                linkButton.addActionListener(e -> openWebPage(career));

                buttonPanel.add(skillsButton);
                buttonPanel.add(coursesButton);
                buttonPanel.add(linkButton);
                careerPanel.add(buttonPanel, BorderLayout.SOUTH);
                careersPanel.add(careerPanel);
                careersPanel.add(Box.createVerticalStrut(10));
            }
        }

        JScrollPane scrollPane = new JScrollPane(careersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resultFrame.add(scrollPane, BorderLayout.CENTER);
        resultFrame.setVisible(true);
    }

    public Map<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public void showSkillsPage(String career) {

        JFrame skillsFrame = new JFrame("Skills for " + career);
        skillsFrame.setSize(400, 700);
        skillsFrame.setLocationRelativeTo(null);
        skillsFrame.setLayout(new BorderLayout());
        skillsFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JLabel skillsLabel = new JLabel("Skills Required", SwingConstants.CENTER);
        skillsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        skillsFrame.add(skillsLabel, BorderLayout.NORTH);

        JTextArea skillsArea = new JTextArea();
        skillsArea.setEditable(false);
        skillsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        skillsArea.setWrapStyleWord(true);
        skillsArea.setLineWrap(true);

        List<String> skills = careerToSkillsMap.getOrDefault(career, List.of("No skills data available"));
        StringBuilder skillsText = new StringBuilder();
        for (String skill : skills) {
            skillsText.append("- ").append(skill).append("\n");
        }
        skillsArea.setText(skillsText.toString());

        JScrollPane scrollPane = new JScrollPane(skillsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        skillsFrame.add(scrollPane, BorderLayout.CENTER);

        skillsFrame.setVisible(true);

         /*  List<String> skills = careerToSkillsMap.get(career);
          if (skills != null) {
              String message = "Skills required for " + career + ":\n" + String.join(", ", skills);
              JOptionPane.showMessageDialog(frame, message, "Skills for " + career, JOptionPane.INFORMATION_MESSAGE);
          } else {
              JOptionPane.showMessageDialog(frame, "No skills data available for " + career, "Skills Not Found", JOptionPane.WARNING_MESSAGE);
          }*/
    }

    public void showCoursesPage(String career) {
        JFrame coursesFrame = new JFrame("Courses for " + career);
        coursesFrame.setSize(400, 700);
        coursesFrame.setLocationRelativeTo(null);
        coursesFrame.setLayout(new BorderLayout());
        coursesFrame.getContentPane().setBackground(new Color(255, 255, 255));

        JLabel coursesLabel = new JLabel("Recommended Courses", SwingConstants.CENTER);
        coursesLabel.setFont(new Font("Arial", Font.BOLD, 18));
        coursesFrame.add(coursesLabel, BorderLayout.NORTH);

        JTextArea coursesArea = new JTextArea();
        coursesArea.setEditable(false);
        coursesArea.setFont(new Font("Arial", Font.PLAIN, 14));
        coursesArea.setWrapStyleWord(true);
        coursesArea.setLineWrap(true);

        List<String> courses = careerToCoursesMap.getOrDefault(career, List.of("No courses data available"));
        StringBuilder coursesText = new StringBuilder();
        for (String course : courses) {
            coursesText.append("- ").append(course).append("\n");
        }
        coursesArea.setText(coursesText.toString());

        JScrollPane scrollPane = new JScrollPane(coursesArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        coursesFrame.add(scrollPane, BorderLayout.CENTER);

        coursesFrame.setVisible(true);
          /*
          List<String> courses = careerToCoursesMap.get(career);
          if (courses != null) {
              String message = "Recommended courses for " + career + ":\n" + String.join(", ", courses);
              JOptionPane.showMessageDialog(frame, message, "Courses for " + career, JOptionPane.INFORMATION_MESSAGE);
          } else {
              JOptionPane.showMessageDialog(frame, "No courses data available for " + career, "Courses Not Found", JOptionPane.WARNING_MESSAGE);
          }*/
    }

    public void openWebPage(String career) {
        String url = careerToLinkMap.get(career);
        if (url != null) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No link available for " + career, "Link Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CareerRecommendationApp::new);
    }
}
