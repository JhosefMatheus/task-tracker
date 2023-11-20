package ui;

import entities.Project;
import exceptions.CustomException;
import exceptions.DbException;
import org.jetbrains.annotations.NotNull;
import services.ProjectService;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;

public class SideBar extends JPanel {
    public SideBar() {
        try {
            Color sideBarBackgroundColor = new Color(92, 84, 112);

            this.setBackground(sideBarBackgroundColor);
            this.setLayout(new GridBagLayout());

            JLabel projectsLabel = new JLabel();

            projectsLabel.setText("Projetos");
            projectsLabel.setForeground(Color.WHITE);

            GridBagConstraints projectsLabelConstraints = new GridBagConstraints();

            projectsLabelConstraints.gridx = 0;
            projectsLabelConstraints.gridy = 0;
            projectsLabelConstraints.weightx = 1;
            projectsLabelConstraints.weighty = 1;
            projectsLabelConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
            projectsLabelConstraints.insets = new Insets(10, 10, 0, 0);

            this.add(projectsLabel, projectsLabelConstraints);

            JButton addProjectButton = getAddProjectButton(sideBarBackgroundColor);

            GridBagConstraints addProjectButtonConstraints = new GridBagConstraints();

            addProjectButtonConstraints.gridx = 1;
            addProjectButtonConstraints.gridy = 0;
            addProjectButtonConstraints.weightx = 1;
            addProjectButtonConstraints.weighty = 1;
            addProjectButtonConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
            addProjectButtonConstraints.insets = new Insets(5, 0, 0, 5);
            addProjectButtonConstraints.ipadx = 10;
            addProjectButtonConstraints.ipady = 10;

            this.add(addProjectButton, addProjectButtonConstraints);

            this.setVisible(true);

            List<Project> projects = ProjectService.getProjects();

            System.out.println("projetos:");
            for (Project project : projects) {
                System.out.println(project);
            }
        } catch (DbException | CustomException e) {
            System.out.println(e.getMessage());
        }
    }

    @NotNull
    private JButton getAddProjectButton(Color sideBarBackgroundColor) {
        JButton addProjectButton = new JButton();

        addProjectButton.setText("+");
        addProjectButton.setForeground(Color.WHITE);
        addProjectButton.setBackground(sideBarBackgroundColor);
        addProjectButton.setBorder(BorderFactory.createEmptyBorder());
        addProjectButton.setFocusPainted(false);

        addProjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addProjectButton.setBackground(new Color(185, 180, 199));
                addProjectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addProjectButton.setBackground(sideBarBackgroundColor);
                addProjectButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return addProjectButton;
    }
}