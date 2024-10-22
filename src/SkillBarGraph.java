import javax.swing.*;
import java.awt.*;

public class SkillBarGraph extends JPanel {
    private String[] skills = {"Java", "SQL", "AI", "ML"};
    private int[] values = {80, 60, 50, 85}; // Popularity values out of 100

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barHeight = 30; // Height of each bar
        int padding = 30;   // Padding between bars to keep them spacious

        for (int i = 0; i < skills.length; i++) {
            int x = 50; // X coordinate for the start of the bar
            int y = (i + 1) * (barHeight + padding); // Y coordinate for the bar position
            int width = values[i] * 3; // Width of the bar (scale it for visibility)

            g.setColor(new Color(173, 216, 230));
            g.fillRect(x, y, width, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(skills[i], x - 30, y + barHeight / 2);
        }
    }
    public static JPanel createSkillsPanel() {
        JPanel skillsPanel = new JPanel();
        skillsPanel.setLayout(new BorderLayout());

        JLabel heading = new JLabel("Skills in Demand", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        skillsPanel.add(heading, BorderLayout.NORTH);

        SkillBarGraph barGraph = new SkillBarGraph();
        barGraph.setPreferredSize(new Dimension(600, 400));
        skillsPanel.add(barGraph, BorderLayout.CENTER);

        return skillsPanel;
    }

}
