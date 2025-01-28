package setting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Setting {
    private static JFrame settingsPage;
    private static JFrame termsPage;
    private static JFrame aboutPage;

    public static void main(String[] args) {
        showSettingsPage();
    }

    public static void showSettingsPage() {
        settingsPage = new JFrame("Career Lead - Settings");
        settingsPage.setSize(400, 700);
        settingsPage.setLocationRelativeTo(null);
        settingsPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsPage.setLayout(null);
        settingsPage.getContentPane().setBackground(new Color(235, 237, 248));

        // Header with title
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139,158,207));
        headerPanel.setBounds(0, 0, 400, 60);
        settingsPage.add(headerPanel);

        JLabel titleLabel = new JLabel("Settings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Main buttons
        JButton profileButton = new JButton("My Profile");
        profileButton.setBounds(100, 150, 200, 40);
        settingsPage.add(profileButton);

        JButton privacyButton = new JButton("Privacy Policy");
        privacyButton.setBounds(100, 220, 200, 40);
        settingsPage.add(privacyButton);

        JButton aboutButton = new JButton("About");
        aboutButton.setBounds(100, 290, 200, 40);
        settingsPage.add(aboutButton);

        // Back Button
        JButton backButton = new JButton("Home");
        backButton.setBounds(100, 460, 200, 30);
        settingsPage.add(backButton);
        JPanel headerP = new JPanel();
        headerP.setBackground(new Color(139,158,207));
        headerP.setBounds(0, 630, 400, 60);
        settingsPage.add(headerP);

        // Action Listeners for navigation
        privacyButton.addActionListener(e -> {
            settingsPage.setVisible(false);
            showTermsPolicyPage();
        });
        aboutButton.addActionListener(e -> {
            settingsPage.setVisible(false);
            showAboutPage();
        });
        profileButton.addActionListener(e -> {
            settingsPage.setVisible(false);
            showProfilePage();
        });
        backButton.addActionListener(e -> {
            settingsPage.dispose();
        });

        settingsPage.setVisible(true);
    }

    private static void showTermsPolicyPage() {
        termsPage = new JFrame("Career Lead - Terms & Policy");
        termsPage.setSize(400, 700);
        termsPage.setLocationRelativeTo(null);
        termsPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        termsPage.setLayout(null);
        termsPage.getContentPane().setBackground(new Color(235, 237, 248));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139,158,207));
        headerPanel.setBounds(0, 0, 400, 60);
        termsPage.add(headerPanel);

        JLabel titleLabel = new JLabel("Terms & Policy", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Privacy Policy Section
        JLabel privacyLabel = new JLabel("Privacy Policy");
        privacyLabel.setBounds(50, 100, 300, 20);
        privacyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        termsPage.add(privacyLabel);

        JTextArea privacyText = new JTextArea("Your privacy is of utmost importance to us. This policy explains how we collect, use, and protect your personal information when using the College and Career Guidance System (CCGS).\n" + //
                "\n" + //
                "1. Information We Collect\n" + //
                "Personal Data: We collect details such as your name, contact information, educational history, skills, interests, and career aspirations to provide tailored recommendations.\n" + //
                "Academic Records: Information about your academic performance is collected to enhance our course and college recommendations.\n" + //
                "Cookies and Usage Data: Cookies are used to improve user experience, while usage data helps us enhance system functionality.\n" + //
                "2. Purpose of Collection\n" + //
                "Personalized Recommendations: We use your data to provide personalized course, college, and career recommendations based on your academic background and interests.\n" + //
                "User Experience Improvement: Collected data helps us improve and personalize the user experience within the platform.\n" + //
                "3. Data Security\n" + //
                "Your information is protected using industry-standard encryption and security protocols. Access to data is restricted to authorized personnel only.\n" + //
                "\n" + //
                "4. Data Sharing\n" + //
                "Your data will not be shared with third parties unless required by law or essential for service delivery.\n" + //
                "\n" + //
                "5. User Rights\n" + //
                "You have the right to:\n" + //
                "\n" + //
                "Modify or Delete Your Profile Information: You can modify or delete your profile information at any time.\n" + //
                "Opt Out of Notifications: You may opt out of receiving system notifications as per your preference.\n" + //
                "");
        privacyText.setLineWrap(true);
        privacyText.setWrapStyleWord(true);
        privacyText.setEditable(false);

        JScrollPane privacyScrollPane = new JScrollPane(privacyText);
        privacyScrollPane.setBounds(50, 130, 300, 100);
        termsPage.add(privacyScrollPane);

        // Terms & Conditions Section
        JLabel termsLabel = new JLabel("Terms & Conditions");
        termsLabel.setBounds(50, 250, 300, 20);
        termsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        termsPage.add(termsLabel);

        JTextArea termsText = new JTextArea("Terms and Conditions for College and Career Guidance System (CCGS)\n" + //
                "\n" + //
                "These terms govern your use of the College and Career Guidance System (CCGS). By accessing the platform, you agree to the following terms:\n" + //
                "\n" + //
                "1. User Responsibilities\n" + //
                "Accurate Information: You must provide accurate academic records and other personal information for effective recommendations.\n" + //
                "Account Security: Users are responsible for maintaining the confidentiality of their login details.\n" + //
                "2. Platform Use\n" + //
                "Educational and Career Recommendations: The platform provides personalized educational and career recommendations based on user inputs such as interests, academic history, and career goals.\n" + //
                "Informational Purpose: Recommendations are informational and do not guarantee admission or job placement.\n" + //
                "3. Account Termination\n" + //
                "CCGS reserves the right to terminate accounts that violate these terms or provide false information.\n" + //
                "\n" + //
                "4. Limitation of Liability\n" + //
                "CCGS is not responsible for decisions made based on its recommendations or any loss resulting from its use.\n" + //
                "\n" + //
                "5. Updates to Terms\n" + //
                "These terms may be updated, and continued use of the platform constitutes acceptance of the updated terms.\n" + //
                "\n" + //
                "");
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);
        termsText.setEditable(false);

        JScrollPane termsScrollPane = new JScrollPane(termsText);
        termsScrollPane.setBounds(50, 280, 300, 100);
        termsPage.add(termsScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Settings");
        backButton.setBounds(100, 480, 200, 30);
        termsPage.add(backButton);

        JPanel headerP = new JPanel();
        headerP.setBackground(new Color(139,158,207));
        headerP.setBounds(0, 630, 400, 60);
        termsPage.add(headerP);

        backButton.addActionListener(e -> {
            termsPage.setVisible(false);
            showSettingsPage();
        });

        termsPage.setVisible(true);
    }

    private static void showAboutPage() {
        aboutPage = new JFrame("Career Lead - About");
        aboutPage.setSize(400, 700);
        aboutPage.setLocationRelativeTo(null);
        aboutPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutPage.setLayout(null);
        aboutPage.getContentPane().setBackground(new Color(235, 237, 248));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139,158,207));
        headerPanel.setBounds(0, 0, 400, 60);
        aboutPage.add(headerPanel);

        JLabel titleLabel = new JLabel("About Us", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // About App Section
        JLabel aboutLabel = new JLabel("About the App");
        aboutLabel.setBounds(50, 100, 300, 20);
        aboutLabel.setFont(new Font("Arial", Font.BOLD, 16));
        aboutPage.add(aboutLabel);

        JTextArea aboutText = new JTextArea("Career Lead is a career recommendation app designed to guide users towards their dream careers by providing personalized recommendations based on their skills and interests.\n1. Creating Your Profile\n" + //
                "Profile Setup: Go to the Profile section and fill in your personal information, including academic background and career aspirations.\n" + //
                "2. Interest Finder\n" + //
                "Find Your Interests: Unsure about your interests? Use the Interest Finder to complete a questionnaire that analyzes your preferences and suggests potential fields of interest.\n" + //
                "3. Career Predictor\n" + //
                "Explore Careers: Based on your interests, the Career Predictor will suggest suitable career paths. You can explore each recommendation for further information on required skills and development courses.\n" + //
                "4. Course Finder\n" + //
                "Personalized Course Suggestions: Access personalized academic course suggestions in the Course Finder section. These are aligned with your strengths and interests to support your career ambitions.\n" + //
                "5. College Finder\n" + //
                "Discover Colleges: The College Finder helps you discover colleges that offer programs matching your preferences and goals.\n" + //
                "6. Contact Support\n" + //
                "Get Help: If you need further assistance, reach out to us at [support email] or call [support number].\n" + //
                "");
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setEditable(false);

        JScrollPane aboutScrollPane = new JScrollPane(aboutText);
        aboutScrollPane.setBounds(50, 130, 300, 200);
        aboutPage.add(aboutScrollPane);

        // Back button
        JButton backButton = new JButton("Back to Settings");
        backButton.setBounds(100, 480, 200, 30);
        aboutPage.add(backButton);

        JPanel headerP = new JPanel();
        headerP.setBackground(new Color(139,158,207));
        headerP.setBounds(0, 630, 400, 60);
        aboutPage.add(headerP);

        backButton.addActionListener(e -> {
            aboutPage.setVisible(false);
            showSettingsPage();
        });

        aboutPage.setVisible(true);
    }
    public static void showProfilePage() {
        // Placeholder for Profile Page
       //ProfileWindow pw = new ProfileWindow();
    }
}
