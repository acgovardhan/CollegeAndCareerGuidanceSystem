package course;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class RoundedButton extends JButton {
    int radius;

    public RoundedButton(String label, int radius) {
        super(label);
        this.radius = radius;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 40); // Set preferred size if needed
    }
}

public class CoursePredictorApp {

    private JFrame frame;
    private JFrame resultFrame;
    private JPanel interestPanel;
    private RoundedButton predictButton;

    // Map of interests to a list of courses with assigned points
    private static final Map<String, Map<String, Integer>> interestToCoursePointsMap = new HashMap<>();
    private static final Map<String, String> courseToLinkMap = new HashMap<>();

    static {
        // Example mapping of interests to courses with points
        interestToCoursePointsMap.put("Mathematics", Map.of("Data Science", 5, "Statistics", 4, "Engineering", 3));
        interestToCoursePointsMap.put("Programming", Map.of("Computer Science", 5, "Software Engineering", 4, "Data Science", 3,"Animation",2));
        interestToCoursePointsMap.put("Biology", Map.of("Medicine", 5, "Biology", 4, "Biomedical Engineering", 3));
        interestToCoursePointsMap.put("Physics", Map.of("Physics", 5, "Mechanical Engineering", 4, "Aerospace Engineering", 3));
        interestToCoursePointsMap.put("Art", Map.of("Fine Arts", 5, "Graphic Design", 4, "Animation", 3));
        interestToCoursePointsMap.put("Writing", Map.of("Journalism", 5, "Creative Writing", 4, "Communication Studies", 3));
        interestToCoursePointsMap.put("Business", Map.of("Business Administration", 5, "Marketing", 4, "Finance", 3));
        interestToCoursePointsMap.put("Music", Map.of("Music Theory", 5, "Sound Engineering", 4, "Performing Arts", 3));
        interestToCoursePointsMap.put("Farming", Map.of("Agriculture", 5, "Dairy", 4, "Environmental Science", 3));

        // Additional example interests
        interestToCoursePointsMap.put("Psychology", Map.of("Psychology", 5, "Counseling", 4, "Social Work", 3));
        interestToCoursePointsMap.put("History", Map.of("History", 5, "Archaeology", 4, "Political Science", 3));
        interestToCoursePointsMap.put("Chemistry", Map.of("Chemistry", 5, "Pharmacy", 4, "Chemical Engineering", 3));

        // Example course links
        courseToLinkMap.put("Data Science", "https://en.wikipedia.org/wiki/Data_science");
        courseToLinkMap.put("Statistics", "https://en.wikipedia.org/wiki/Statistics");
        courseToLinkMap.put("Engineering", "https://en.wikipedia.org/wiki/Engineering");
        courseToLinkMap.put("Computer Science", "https://en.wikipedia.org/wiki/Computer_science");
        courseToLinkMap.put("Software Engineering", "https://en.wikipedia.org/wiki/Software_engineering");
        courseToLinkMap.put("Medicine", "https://en.wikipedia.org/wiki/Medicine");
        courseToLinkMap.put("Biology", "https://en.wikipedia.org/wiki/Biology");
        courseToLinkMap.put("Psychology", "https://en.wikipedia.org/wiki/Psychology");
        courseToLinkMap.put("Physics", "https://en.wikipedia.org/wiki/Physics");
        courseToLinkMap.put("Fine Arts", "https://en.wikipedia.org/wiki/Fine_art");
        courseToLinkMap.put("Graphic Design", "https://en.wikipedia.org/wiki/Graphic_design");
        courseToLinkMap.put("Animation", "https://en.wikipedia.org/wiki/Animation");
        courseToLinkMap.put("Journalism", "https://en.wikipedia.org/wiki/Journalism");
        courseToLinkMap.put("Creative Writing", "https://en.wikipedia.org/wiki/Creative_writing");
        courseToLinkMap.put("Communication Studies", "https://en.wikipedia.org/wiki/Communication_studies");
        courseToLinkMap.put("Business Administration", "https://en.wikipedia.org/wiki/Business_administration");
        courseToLinkMap.put("Marketing", "https://en.wikipedia.org/wiki/Marketing");
        courseToLinkMap.put("Finance", "https://en.wikipedia.org/wiki/Finance");
        courseToLinkMap.put("Music Theory", "https://en.wikipedia.org/wiki/Music_theory");
        courseToLinkMap.put("Sound Engineering", "https://en.wikipedia.org/wiki/Audio_engineering");
        courseToLinkMap.put("Performing Arts", "https://en.wikipedia.org/wiki/Performing_arts");
        courseToLinkMap.put("Agriculture", "https://en.wikipedia.org/wiki/Agriculture");
        courseToLinkMap.put("Dairy", "https://en.wikipedia.org/wiki/Dairy");
        courseToLinkMap.put("Environmental Science", "https://en.wikipedia.org/wiki/Environmental_science");
        courseToLinkMap.put("Counseling", "https://en.wikipedia.org/wiki/Counseling");
        courseToLinkMap.put("Social Work", "https://en.wikipedia.org/wiki/Social_work");
        courseToLinkMap.put("Archaeology", "https://en.wikipedia.org/wiki/Archaeology");
        courseToLinkMap.put("Political Science", "https://en.wikipedia.org/wiki/Political_science");
        courseToLinkMap.put("Pharmacy", "https://en.wikipedia.org/wiki/Pharmacy");
        courseToLinkMap.put("Chemical Engineering", "https://en.wikipedia.org/wiki/Chemical_engineering");
    }

    public CoursePredictorApp() {
        // Set up main frame
        frame = new JFrame("Interest-Based Course Predictor");
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(139,158, 207));

        // Title
        JLabel title = new JLabel("Select Your Interests", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        // Panel for interest checkboxes
        interestPanel = new JPanel();
        interestPanel.setLayout(new GridLayout(interestToCoursePointsMap.size(), 1, 5, 5)); // Add vertical spacing
        interestPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Set padding around the panel
        interestPanel.setBackground(new Color(228, 240, 255)); // Light background for better visibility
        List<JCheckBox> interestCheckBoxes = new ArrayList<>();

        for (String interest : interestToCoursePointsMap.keySet()) {
            JCheckBox checkBox = new JCheckBox(interest);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            interestCheckBoxes.add(checkBox);
            interestPanel.add(checkBox);
        }
        frame.add(interestPanel, BorderLayout.CENTER);

        // Predict button
        predictButton = new RoundedButton("Predict Courses", 30);
        predictButton.setFont(new Font("Arial", Font.BOLD, 16));
        predictButton.setBackground(new Color(139,158,207));
        predictButton.setFocusPainted(false);
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

    // Method to display the recommended courses in a separate result page (new JFrame)
    private void showResultPage(List<String> selectedInterests) {
        resultFrame = new JFrame("Course Recommendations");
        resultFrame.setSize(400, 700);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(new BorderLayout());
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel resultLabel = new JLabel("Recommended Courses", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultFrame.add(resultLabel, BorderLayout.NORTH);
        JPanel headerP = new JPanel();
        headerP.setBackground(new Color(139,158,207));
        headerP.setBounds(0, 610, 390, 80);

        RoundedButton bButton = new RoundedButton("Go Back",40);
        //interestButton.setBounds(100, yOffset, 200, 40);
        bButton.setFont(new Font("Arial", Font.PLAIN, 18));
        bButton.setBackground(Color.decode("#e9f4f5")); // Light blue buttons
        bButton.setFocusPainted(false);
        bButton.setBorder(BorderFactory.createLineBorder(Color.decode("#1e90ff"), 1));
        headerP.add(bButton);
        resultFrame.add(headerP);
        bButton.addActionListener(e -> {
            frame.setVisible(true); // Make the main frame visible again
            resultFrame.dispose();
        });


        JPanel coursesPanel = new JPanel();
        coursesPanel.setLayout(new BoxLayout(coursesPanel, BoxLayout.Y_AXIS));
        coursesPanel.setBackground(Color.decode("#e9f4f5"));

        Map<String, Integer> courseScores = new HashMap<>();
        for (String interest : selectedInterests) {
            Map<String, Integer> courses = interestToCoursePointsMap.get(interest);
            if (courses != null) {
                for (Map.Entry<String, Integer> entry : courses.entrySet()) {
                    courseScores.put(entry.getKey(), courseScores.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedCourses = new ArrayList<>(courseScores.entrySet());
        sortedCourses.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        if (selectedInterests.isEmpty()) {
            JLabel noSelectionLabel = new JLabel("             Please select at least one interest!", SwingConstants.CENTER);
            noSelectionLabel.setFont(new Font("times new roman", Font.PLAIN, 20));
            noSelectionLabel.setForeground(Color.RED);
            coursesPanel.add(Box.createVerticalStrut(200)); // Add vertical space to center the label
            coursesPanel.add(noSelectionLabel);
        } else {
            for (Map.Entry<String, Integer> entry : sortedCourses) {
                String course = entry.getKey();
                int points = entry.getValue();

                JPanel coursePanel = new JPanel();
                coursePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                coursePanel.setBackground(new Color(230, 240, 255));
                coursePanel.setMaximumSize(new Dimension(350, 40));
                coursePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                JLabel courseLabel = new JLabel("<html><a href=''>" + course +"                  ->                    ("+points+"/10)"+ "</a></html>");
                courseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                courseLabel.setForeground(Color.BLACK);
                courseLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                String courseLink = courseToLinkMap.get(course);
                if (courseLink != null) {
                    courseLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                Desktop.getDesktop().browse(new URI(courseLink));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                coursePanel.add(courseLabel);
                coursesPanel.add(Box.createVerticalStrut(10));
                coursesPanel.add(coursePanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(coursesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        resultFrame.add(scrollPane, BorderLayout.CENTER);



        resultFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CoursePredictorApp::new);
    }
}