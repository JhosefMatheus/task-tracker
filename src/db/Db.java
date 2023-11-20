package db;

import exceptions.CustomException;
import exceptions.DbException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
    private static Connection connection = null;

    @NotNull
    public static Statement dbConnect() throws DbException, CustomException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:task-tracker.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(60);

            return statement;
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao tentar conectar com o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao tentar conectar com o banco de dados: " + e.getMessage());
        }
    }

    public static void closeDbConnection() throws DbException, CustomException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao fechar a conexão com o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }

    public static void initializeDb() throws DbException, CustomException {
        createProjectTable();
    }

    public static void createProjectTable() throws DbException, CustomException {
        Statement statement = null;

        try {
            statement = dbConnect();

            String sql = "CREATE TABLE project IF NOT EXISTS (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(MAX) UNIQUE NOT NULL," +
                    "createdAt DATETIME NOT NULL," +
                    "updatedAt DATETIME NULL" +
                    ");";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao criar a tabela de projetos: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao criar a tabela de projetos: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                closeDbConnection();
            } catch (SQLException e) {
                throw new DbException("Erro inesperado no banco de dados ao fechar o estado do banco de dados: " + e.getMessage());
            } catch (Exception e) {
                throw new CustomException("Erro inesperado ao fechar o estado do banco de dados: " + e.getMessage());
            }
        }
    }

    public static void createProjectSectionTable() throws DbException, CustomException {
        Statement statement = null;

        try {
            statement = dbConnect();

            String sql = "CREATE TABLE project_section IF NOT EXISTS (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "project_id INTEGER NOT NULL," +
                    "name VARCHAR(MAX) NOT NULL," +
                    "createdAt DATETIME NOT NULL," +
                    "updatedAt DATETIME NULL," +
                    "FOREIGN KEY (project_id references project.id)" +
                    ");";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException("Erro inesperado no banco de dados ao criar a tabela de seções de projetos: " + e.getMessage());
        } catch (Exception e) {
            throw new CustomException("Erro inesperado ao criar a tabela de seções de projetos: " + e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                closeDbConnection();
            } catch (SQLException e) {
                throw new DbException("Erro inesperado no banco de dados ao fechar o estado do banco de dados: " + e.getMessage());
            } catch (Exception e) {
                throw new CustomException("Erro inesperado ao fechar o estado do banco de dados: " + e.getMessage());
            }
        }
    }
}
