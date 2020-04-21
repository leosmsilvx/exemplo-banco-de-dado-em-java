package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();

    //Constantes
    public static final String URL = "jdbc:mysql://localhost:3306/nomebd";//substitua pelo endereço do servidor/nome do banco de dados
    public static final String USER = "usuario";//usuário para acessar o banco
    public static final String PASSWORD = "senha";//senha para acessar o banco
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";//nome do driver


    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //métodos responsável por fazer a conexão com o BD
    private Connection newConnection() {
        Connection connection = null;
        //tenta criar fazer a conexão
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Banco de dados conectado");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Houve uuma falha na conexão com o banco de dados");
        }
        //Caso a conexão seja possível, retorna a conexão
        return connection;
    }

    public static Connection getConnection() {
        //retorna a conexão
        return instance.newConnection();
    }

}
