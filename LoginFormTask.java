import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class User {

    private String username;
    private String password;
    private String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}

class WelcomePage extends Frame {

    WelcomePage(User user) {
        super("Welcome");
        setLayout(new GridLayout(2, 1, 10, 10));

        Label hello = new Label("Hello, " + user.getUsername(), Label.CENTER);
        hello.setFont(new Font("SansSerif", Font.BOLD, 22));

        Label role = new Label("Role: " + user.getRole(), Label.CENTER);
        role.setFont(new Font("SansSerif", Font.PLAIN, 16));

        add(hello);
        add(role);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(350, 200);
        setLocationRelativeTo(null);
    }
}

public class LoginFormTask extends Frame {

    private static final int MAX_ATTEMPTS = 3;

    private final List<User> users = new ArrayList<>();
    private int failedAttempts = 0;

    public LoginFormTask() {
        super("Login");
        loadUsers();

        setLayout(new GridBagLayout());
        Panel content = new Panel(new BorderLayout(10, 15));

        Panel top = new Panel(new GridLayout(2, 1, 0, 5));
        Label title = new Label("Welcome back", Label.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        Label message = new Label("", Label.CENTER);
        message.setForeground(Color.RED);
        top.add(title);
        top.add(message);
        content.add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 8, 8));
        form.add(new Label("Username:", Label.RIGHT));
        TextField username = new TextField(18);
        form.add(username);

        form.add(new Label("Password:", Label.RIGHT));
        TextField password = new TextField(18);
        password.setEchoChar('*');
        form.add(password);

        form.add(new Label("Role:", Label.RIGHT));
        Choice role = new Choice();
        role.add("Student");
        role.add("TA");
        role.add("Professor");
        form.add(role);

        content.add(form, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        Button cancel = new Button("Cancel");
        Button login = new Button("Login");
        buttons.add(cancel);
        buttons.add(login);
        content.add(buttons, BorderLayout.SOUTH);

        add(content);

        login.addActionListener(e -> {
            String name = username.getText().trim();
            String pass = password.getText();
            String selectedRole = role.getSelectedItem();

            if (name.isEmpty() || pass.isEmpty()) {
                message.setText("Please fill in all fields");
                return;
            }

            User user = findUser(name);
            if (user == null) {
                message.setText("User not found");
                return;
            }

            if (!user.getPassword().equals(pass)) {
                failedAttempts++;
                if (failedAttempts >= MAX_ATTEMPTS) {
                    // Bonus: disable the login button after 3 wrong attempts
                    login.setEnabled(false);
                    message.setText("Too many failed attempts. Login disabled");
                } else {
                    message.setText("Wrong password (" + (MAX_ATTEMPTS - failedAttempts) + " attempts left)");
                }
                password.setText("");
                return;
            }


            if (!user.getRole().equals(selectedRole)) {
                message.setText("Selected role does not match this user");
                return;
            }

            // Success
            new WelcomePage(user).setVisible(true);
            dispose();
        });

        cancel.addActionListener(e -> dispose());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { dispose(); }
        });
        setSize(420, 340);
        setLocationRelativeTo(null);

    }

    private void loadUsers() {
        users.add(new User("ahmed", "1234", "Student"));
        users.add(new User("sara", "abcd", "TA"));
        users.add(new User("drmohamed", "pass123", "Professor"));
    }

    private User findUser(String name) {
        for (User u : users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        new LoginFormTask().setVisible(true);

    }
}