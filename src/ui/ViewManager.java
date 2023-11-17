package ui;

import javax.swing.*;
import java.awt.*;

public class ViewManager extends JPanel {
    public ViewManager() {
        Color viewManagerBackgroundColor = new Color(53, 47, 68);

        this.setBackground(viewManagerBackgroundColor);

        JLabel label = new JLabel();

        label.setText("View Manager");
        label.setForeground(Color.WHITE);

        this.add(label);

        this.setVisible(true);
    }
}
