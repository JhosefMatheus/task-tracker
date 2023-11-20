package services;

import entities.Project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    public static List<Project> getProjects() {
        List<Project> projects = new ArrayList<Project>();

        projects.add(new Project(1, "Diário", LocalDateTime.now(), null));
        projects.add(new Project(2, "GIC", LocalDateTime.now(), null));
        projects.add(new Project(3, "GPX Manager", LocalDateTime.now(), null));

        return projects;
    }
}
