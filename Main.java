import interest.InterestFinderPage;
import college.CollegeFinder;
import career.CareerRecommendationApp;
import course.CoursePredictorApp;
import setting.Setting;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// User class to handle sign-up and login data management
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static boolean signUp(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("SignUpData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists.");
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("SignUpData.txt", true))) {
            writer.write(username + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("SignUpData.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
class OnboardingPage extends JFrame {
    public OnboardingPage() {
        setTitle("Career Lead - Onboarding");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(235, 237, 248));

        JLabel title = new JLabel("CAREER LEAD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(50, 40, 300, 40);
        add(title);

        JLabel subtitle = new JLabel("Explore your dream Career!", SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setBounds(50, 90, 300, 30);
        add(subtitle);

        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.CENTER);

        // Placeholder for image (add your own image in the final version)
        JLabel imagePlaceholder = new JLabel(new ImageIcon("test.png"));
        imagePlaceholder.setBounds(0, 150, 400, 300);
        add(imagePlaceholder);
        //ImageIcon originalIcon = new ImageIcon("career.png");
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("career.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(300,125 , Image.SCALE_SMOOTH);
        logo.setIcon(new ImageIcon(scaledImage));
        add(logo, BorderLayout.CENTER);
        setVisible(true);

        /*URL imageUrl = getClass().getResource("/career.png");
        if (imageUrl != null) {
            ImageIcon originalIcon = new ImageIcon(imageUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(400, 125, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } else {
            System.out.println("Image not found!");
        }*/

        JButton loginButton = new JButton("Get Started");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setBackground(new Color(47, 71, 140));
        loginButton.setBounds(120, 450, 150, 40);
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        //JButton signUpButton = new JButton("Sign Up");
        //signUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        //signUpButton.setBackground(new Color(240, 248, 255));
        //signUpButton.setBounds(120, 410, 150, 40);
        //signUpButton.setForeground(Color.BLUE);
        //add(signUpButton);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(139,158,207));
        headerPanel.setBounds(0, 0, 400,40);
        add(headerPanel);

        JPanel headerP = new JPanel();
        headerP.setBackground(new Color(139,158,207));
        headerP.setBounds(0, 630, 400, 60);
        add(headerP);

        loginButton.addActionListener(e -> {
            //setVisible(false);
            new LoginSignupGUI().setVisible(true);
        });

       // signUpButton.addActionListener(e -> {
            //setVisible(false);
           // new LoginSignupGUI().setVisible(true);

      //  });

        setVisible(true);
    }
}


// Class to collect additional user details
// Class for collecting additional user details after sign-up
class DetailsWindow extends JFrame {
    private JTextField nameField;
    private JTextField educationField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JTextField emailField;
    private JTextField phoneField;

    public DetailsWindow(String username) {
        setTitle("Let Us Know About You");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE); // Set background color to white

        // Create a panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE); // Same background color as main frame

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Heading
        JLabel headingLabel = new JLabel("Let Us Know A Bit About You...");
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20)); // Set font and size
        headingLabel.setForeground(new Color(0, 51, 153)); // Blue color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(headingLabel, gbc);

        // Name Label and Field
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width
        panel.add(nameLabel, gbc);

        nameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Education Label and Field
        JLabel educationLabel = new JLabel("Education:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(educationLabel, gbc);

        educationField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(educationField, gbc);

        // Gender Label and Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(genderLabel, gbc);

        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        gbc.gridx = 1;
        panel.add(maleButton, gbc);
        gbc.gridx = 2;
        panel.add(femaleButton, gbc);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Phone Label and Field
        JLabel phoneLabel = new JLabel("Phone:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(phoneLabel, gbc);

        phoneField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(phoneField, gbc);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        styleButton(submitButton); // Apply style method
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(submitButton, gbc);

        add(panel); // Add the panel to the frame

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDetails(username); // Call the method to save details
            }
        });
    }

    // Method to save details to a file
    private void saveDetails(String username) {
        String name = nameField.getText();
        String education = educationField.getText();
        String gender = maleButton.isSelected() ? "Male" : "Female";
        String email = emailField.getText();
        String phone = phoneField.getText();

        // Save details to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UserDetails.txt", true))) {
            writer.write(username + "," + name + "," + education + "," + gender + "," + email + "," + phone);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Details saved successfully!");
            new HomePage(username);
            //dispose(); // Closes the sign-up window
            dispose(); // Close the window
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving details.");
        }
    }

    // Helper method to style buttons
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 51, 153)); // Dark blue color
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for better visibility
    }
}

// Main application GUI class for login and signup functionality
class LoginSignupGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginSignupGUI() {
        setTitle("Career Lead - Onboarding");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // Set background color to white

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for flexible alignment
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Logo section
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Center the logo
        // Place the path to your logo here
        ImageIcon originalIcon = new ImageIcon("newlogo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(400, 125, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaledImage));
        add(logoLabel, BorderLayout.NORTH); // Add logo to the top

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        gbc.gridy = 3;
        panel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        gbc.gridy = 4;
        panel.add(loginButton, gbc);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        styleButton(signUpButton);
        gbc.gridy = 5;
        panel.add(signUpButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (User.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    new HomePage(username);
                    dispose();
                    // Proceed to the next screen or application
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                String confirmPassword = JOptionPane.showInputDialog("Confirm Password:");
                if (User.signUp(username, password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Sign-up successful!");
                    // Open the details window after successful sign-up
                    new DetailsWindow(username).setVisible(true);
                }
            }
        });
    }

    // Helper method to style buttons
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 51, 153)); // Dark blue color
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for better visibility
    }
}

//home button
class HomePage extends JFrame {
    public HomePage(String username) {
        setTitle("Career Lead - Home");
        setSize(400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        // Create a top panel for the greeting and settings button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(240, 248, 255));

        // Greeting label
        JLabel greetingLabel = new JLabel("Hey there, " + username, JLabel.CENTER);
        greetingLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        greetingLabel.setForeground(new Color(0, 51, 153)); // Dark blue color
        topPanel.add(greetingLabel, BorderLayout.CENTER);

        // Settings button (small square)
        JButton settingsButton = new JButton("⚙"); // Using gear symbol for settings
        settingsButton.setPreferredSize(new Dimension(50, 40));
        settingsButton.setFocusPainted(false);
        topPanel.add(settingsButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Main panel with navigation buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows with spacing
        buttonPanel.setBackground(new Color(240, 248, 255));

        // Create buttons
        JButton interestFinderButton = createStyledButton("Go to Interest Finder");
        JButton careerButton = createStyledButton("Get Career Advice");
        JButton courseButton1 = createStyledButton("Find My Course");
        JButton courseButton2 = createStyledButton("Search Colleges");

        // Add buttons to panel
        buttonPanel.add(interestFinderButton);
        buttonPanel.add(careerButton);
        buttonPanel.add(courseButton1);
        buttonPanel.add(courseButton2);

        add(buttonPanel, BorderLayout.CENTER);

        // Action listeners for buttons (you can add actions here)
        interestFinderButton.addActionListener(e -> openInterestFinder());
        careerButton.addActionListener(e -> Careerrec());
        courseButton1.addActionListener(e -> Courserrec());
        courseButton2.addActionListener(e -> openCollegeFinder());

        settingsButton.addActionListener(e -> settingsPage());

        setVisible(true);
    }
    private void openInterestFinder() {
        // Code to navigate to the Interest Finder page or window
        InterestFinderPage interestFinderPage = new InterestFinderPage();
        interestFinderPage.showAgeSelectionPage();
        //this.dispose(); // Close the HomePage if needed
    }
    private void settingsPage() {
        // Code to navigate to the Interest Finder page or window
        Setting set = new Setting();
        Setting.showSettingsPage();
        //this.dispose(); // Close the HomePage if needed
    }

    private void Careerrec(){
        // Code to navigate to the Interest Finder page or window
        new CareerRecommendationApp();
        //this.dispose(); // Close the HomePage if needed
    }
    private void openCollegeFinder() {
        // Code to navigate to the Interest Finder page or window
        CollegeFinder collegeFinder = new CollegeFinder();
        collegeFinder.initializeData();
        collegeFinder.showCollege();
        //this.dispose(); // Close the HomePage if needed
    }
    private void Courserrec(){
        // Code to navigate to the Interest Finder page or window
        new CoursePredictorApp();
        //this.dispose(); // Close the HomePage if needed
    }


    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 51, 153)); // Dark blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }
}

// Main class to launch the application
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new LoginSignupGUI().setVisible(true);
                SwingUtilities.invokeLater(OnboardingPage::new);
            }
        });
    }
}
