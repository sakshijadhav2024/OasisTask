import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExamSystem {
    private static String username = "user";
    private static String password = "password";

    private JFrame loginFrame;
    private JFrame examFrame;

    public OnlineExamSystem() {
        createLoginUI();
    }

    private void createLoginUI() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);
        loginFrame.setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    loginFrame.dispose();
                    createExamUI();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password. Please try again.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginFrame.add(userLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel()); // Empty label for layout
        loginFrame.add(loginButton);

        loginFrame.setVisible(true);
    }

    private void createExamUI() {
        examFrame = new JFrame("Online Exam");
        examFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        examFrame.setSize(400, 300);
        examFrame.setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel("What is the capital of France?");
        JRadioButton option1 = new JRadioButton("Paris");
        JRadioButton option2 = new JRadioButton("London");
        JRadioButton option3 = new JRadioButton("Berlin");
        JRadioButton option4 = new JRadioButton("Madrid");

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAnswer = "";
                if (option1.isSelected()) {
                    selectedAnswer = "Paris";
                } else if (option2.isSelected()) {
                    selectedAnswer = "London";
                } else if (option3.isSelected()) {
                    selectedAnswer = "Berlin";
                } else if (option4.isSelected()) {
                    selectedAnswer = "Madrid";
                }

                // Process the answer, and you can add more questions similarly
                JOptionPane.showMessageDialog(examFrame, "Selected Answer: " + selectedAnswer,
                        "Answer Submitted", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(5, 1));
        questionPanel.add(questionLabel);
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);

        examFrame.add(questionPanel, BorderLayout.CENTER);
        examFrame.add(submitButton, BorderLayout.SOUTH);

        examFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OnlineExamSystem();
            }
        });
    }
}
