package db;

import exceptions.CustomException;
import exceptions.DbException;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class Db {
    private static Connection connection = null;

    public static Connection getConnection() throws DbException {
        if (connection == null) {
            throw new DbException("Conexão com o banco de dados não foi estabelecida.");
        }

        return connection;
    }

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
        createProjectSectionTable();
    }

    public static void createProjectTable() throws DbException, CustomException {
        Statement statement = null;

        try {
            statement = dbConnect();

            String sql = "CREATE TABLE IF NOT EXISTS project (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT UNIQUE NOT NULL," +
                    "created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "updated_at DATETIME NULL" +
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

            String sql = "CREATE TABLE IF NOT EXISTS project_section (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "project_id INTEGER NOT NULL," +
                    "name TEXT NOT NULL," +
                    "created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                    "updated_at DATETIME NULL," +
                    "FOREIGN KEY (project_id) references project (id)" +
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