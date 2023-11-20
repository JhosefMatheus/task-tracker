package services;

import db.Db;
import entities.Project;
import exceptions.CustomException;
import exceptions.DbException;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    @NotNull
    public static List<Project> getProjects() throws DbException, CustomException {
        Statement statement = null;

        try {
            List<Project> projects = new ArrayList<Project>();

            statement = Db.dbConnect();

            String sql = "SELECT * FROM project;";

            ResultSet projectsResultSet = statement.executeQuery(sql);

            while (projectsResultSet.next()) {
                @NotNull Integer projectId = projectsResultSet.getInt("id");
                @NotNull String projectName = projectsResultSet.getString("name");

                @NotNull Timestamp projectCreatedAtTimestamp = projectsResultSet.getTimestamp("created_at");
                @NotNull LocalDateTime projectCreatedAt = projectCreatedAtTimestamp.toLocalDateTime();

                Timestamp projectUpdatedAtTimestamp = projectsResultSet.getTimestamp("updated_at");
                LocalDateTime projectUpdatedAt = projectUpdatedAtTimestamp.toLocalDateTime();

                Project project = new Project(projectId, projectName, projectCreatedAt, projectUpdatedAt);

                projects.add(project);
            }

            return projects;
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao buscar pelos projetos: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao buscar pelos projetos: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                Db.closeDbConnection();
            } catch (SQLException e) {
                throw new DbException("Erro inesperado no banco de dados ao fechar o estado do banco de dados: " + e.getMessage());
            } catch (Exception e) {
                throw new CustomException("Erro inesperado ao fechar o estado do banco de dados: " + e.getMessage());
            }
        }
    }

    public static void createProject(@NotNull String projectName) throws DbException, CustomException {
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO project (name) values (?);";

            preparedStatement = Db.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, projectName);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao criar projeto: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao criar projeto: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                Db.closeDbConnection();
            } catch (SQLException e) {
                throw new DbException("Erro inesperado no banco de dados ao fechar o estado do banco de dados: " + e.getMessage());
            } catch (Exception e) {
                throw new CustomException("Erro inesperado ao fechar o estado do banco de dados: " + e.getMessage());
            }
        }
    }
}