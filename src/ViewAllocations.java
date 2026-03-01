import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewAllocations extends JFrame {
    private JTable table;

    public ViewAllocations() {
        setTitle("Hostel System - View Allocations");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);

        JLabel title = new JLabel("Allocated Rooms", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        table = new JTable(new DefaultTableModel(new String[]{"Student ID", "Student Name", "Room Type"}, 0));
        table.setRowHeight(25);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Dashboard");
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setBackground(new Color(108, 117, 125));
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            new Dashboard().setVisible(true);
            this.dispose();
        });

        loadData();
        add(panel);
    }

    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM rooms");
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("student_id"), rs.getString("student_name"), rs.getString("room_type")});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
