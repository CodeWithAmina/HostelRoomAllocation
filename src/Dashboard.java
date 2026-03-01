import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    public Dashboard() {
        setTitle("Hostel System - Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setOpaque(false);

        JLabel title = new JLabel("Hostel Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton allocBtn = new JButton("Allocate Room");
        JButton viewBtn = new JButton("View Allocations");
        JButton logoutBtn = new JButton("Logout");

        Dimension btnSize = new Dimension(250, 40);
        for (JButton b : new JButton[]{allocBtn, viewBtn, logoutBtn}) {
            b.setMaximumSize(btnSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setFont(new Font("Segoe UI", Font.BOLD, 14));
            b.setFocusPainted(false);
            b.setForeground(Color.WHITE);
        }

        allocBtn.setBackground(new Color(30, 144, 255));
        viewBtn.setBackground(new Color(40, 167, 69));
        logoutBtn.setBackground(new Color(220, 53, 69));

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        panel.add(allocBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(viewBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(logoutBtn);

        allocBtn.addActionListener(e -> {
            new RoomAllocationForm().setVisible(true);
            this.dispose();
        });
        viewBtn.addActionListener(e -> {
            new ViewAllocations().setVisible(true);
            this.dispose();
        });
        logoutBtn.addActionListener(e -> {
            new LoginForm().setVisible(true);
            this.dispose();
        });

        add(panel);
    }
}
