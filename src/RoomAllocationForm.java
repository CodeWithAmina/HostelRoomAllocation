import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RoomAllocationForm extends JFrame {
    private JTextField idField, nameField;
    private JComboBox<String> roomBox;

    public RoomAllocationForm() {
        setTitle("Hostel System - Allocate Room");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("Allocate Room");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; panel.add(new JLabel("Student ID:"), gbc);
        idField = new JTextField(15);
        gbc.gridx = 1; panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Student Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1; panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Room Type:"), gbc);
        roomBox = new JComboBox<>(new String[]{"Single", "Double", "Triple"});
        gbc.gridx = 1; panel.add(roomBox, gbc);

        JButton allocBtn = new JButton("Allocate");
        allocBtn.setBackground(new Color(30, 144, 255));
        allocBtn.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 4; panel.add(allocBtn, gbc);

        JButton backBtn = new JButton("Back");
        gbc.gridx = 1; panel.add(backBtn, gbc);

        allocBtn.addActionListener(e -> allocateAction());
        backBtn.addActionListener(e -> {
            new Dashboard().setVisible(true);
            this.dispose();
        });

        add(panel);
    }

    private void allocateAction() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String room = roomBox.getSelectedItem().toString();
        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO rooms (student_id, student_name, room_type) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, room);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Room Allocated Successfully!");
            idField.setText("");
            nameField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
