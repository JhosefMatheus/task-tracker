package ui;

import javax.swing.JFrame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

public class MainFrame extends JFrame {
    public MainFrame() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        int width = gd.getDisplayMode().getWidth() / 2;
        int height = gd.getDisplayMode().getHeight() / 2;

        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().setBackground(new Color(53, 47, 68));

        this.setLayout(new GridBagLayout());

        SideBar sideBar = new SideBar();

        GridBagConstraints sideBarConstraints = new GridBagConstraints();

        sideBarConstraints.gridx = 0;
        sideBarConstraints.gridy = 0;
        sideBarConstraints.weightx = 1;
        sideBarConstraints.weighty = 1;
        sideBarConstraints.fill = GridBagConstraints.BOTH;

        this.add(sideBar, sideBarConstraints);

        ViewManager viewManager = new ViewManager();

        GridBagConstraints viewManagerConstraints = new GridBagConstraints();

        viewManagerConstraints.gridx = 1;
        viewManagerConstraints.gridy = 0;
        viewManagerConstraints.weightx = 6;
        viewManagerConstraints.weighty = 1;

        this.add(viewManager, viewManagerConstraints);

        this.setVisible(true);
    }
}