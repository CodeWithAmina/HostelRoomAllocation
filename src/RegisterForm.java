import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm extends JFrame {
    private JTextField idField, userField;
    private JPasswordField passField;

    public RegisterForm() {
        setTitle("Hostel System - Register");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Register");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; panel.add(new JLabel("ID:"), gbc);
        idField = new JTextField(15);
        gbc.gridx = 1; panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Username:"), gbc);
        userField = new JTextField(15);
        gbc.gridx = 1; panel.add(userField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Password:"), gbc);
        passField = new JPasswordField(15);
        gbc.gridx = 1; panel.add(passField, gbc);

        JButton regBtn = new JButton("Register");
        regBtn.setBackground(new Color(40, 167, 69));
        regBtn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(regBtn, gbc);

        JButton backBtn = new JButton("Back");
        gbc.gridx = 1; panel.add(backBtn, gbc);

        regBtn.addActionListener(e -> registerAction());
        backBtn.addActionListener(e -> {
            new LoginForm().setVisible(true);
            this.dispose();
        });

        add(panel);
    }

    private void registerAction() {
        String id = idField.getText().trim();
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();
        if (id.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, user);
            pst.setString(3, pass);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration Successful");
            new LoginForm().setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
