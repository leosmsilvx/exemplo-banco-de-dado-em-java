package model.DAO;

import model.BEAN.PersonBEAN;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO {

    private Connection connection; //atributo que vai receber a conexão
    private PreparedStatement pst; //atributo que realiza consultas sql

    public boolean insertPerson(PersonBEAN person){
        this.connection = ConnectionFactory.getConnection();
        //define o comando a ser executado
        String sql = "INSERT INTO `PERSON`(NAME, DATEB, ADDRESS) VALUES (?,?,?);";
        try {
            //prepara o atributo para a consulta
            pst = connection.prepareStatement(sql);
            // define os valores "?" da consulta, na mesma ordem
            pst.setString(1, person.getName());
            pst.setString(2, person.getDateb());
            pst.setString(3, person.getAddress());
            //executa o comando
            pst.execute();
            //fecha a conexão, FECHAR SEMPRE
            closeConnection(pst, connection);
            //retorna que a ação foi possível
            return true;
        } catch (SQLException e) {
            //exibe detalhes do erro
            e.printStackTrace();
            //retorna que não foi possível executar a ação
            return false;
        }
    }

    public PersonBEAN getPerson(String name){
        this.connection = ConnectionFactory.getConnection();
        //define o comando a ser executado
        String sql = "SELECT * FROM PERSON WHERE NAME = ?";
        //Objeto a ser retornado com os dados da consulta
        PersonBEAN person = new PersonBEAN();
        try{
            //prepara o atributo para a consulta
            pst = connection.prepareStatement(sql);
            //define os valores "?" da consulta, na mesma ordem
            pst.setString(1, name);
            //executa o comando e armazena o resultaod
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                //pega os dados da consulta e coloca no objeto
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setDateb(rs.getString(3));
                person.setAddress(rs.getString(4));
            }
            //fecha conexão, FECHAR SEMPRE
            closeConnection(pst, connection);
            //retorna o objeto com os dados da consulta
            return person;
        }catch (SQLException e){
            //exibe detalhes do erro
            e.printStackTrace();
            //retorna o objeto vazio
            return null;
        }
    }

    public ArrayList<PersonBEAN> getAllPersons(){
        this.connection = ConnectionFactory.getConnection();
        //define o comando a ser executado
        String sql = "SELECT * FROM PERSON";
        //Objeto a ser retornado com os dados da consulta
        ArrayList<PersonBEAN> persons = new ArrayList<>();
        try{
            //prepara o atributo para a consulta
            pst = connection.prepareStatement(sql);
            //executa o comando e armazena o resultado
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                PersonBEAN person = new PersonBEAN();
                //pega os dados da consulta e coloca no objeto
                person.setId(rs.getInt(1));
                person.setName(rs.getString(2));
                person.setDateb(rs.getString(3));
                person.setAddress(rs.getString(4));
                persons.add(person);
            }
            //fecha conexão, FECHAR SEMPRE
            closeConnection(pst, connection);
            //retorna lista de objetos
            return persons;
        }catch (SQLException e){
            //exibe detalhes do erro
            e.printStackTrace();
            //retorna o objeto vazio
            return null;
        }
    }

    //método que atualiza informações no banco, note que aqui o id no parametro é correspondente ao registro no banco a ser atualizado
    public boolean updatePerson(PersonBEAN person, int id){
        this.connection = ConnectionFactory.getConnection();
        String sql = "UPDATE PERSON SET NAME = ?, DATEB = ?, ADDRESS = ? WHERE ID = ?";
        try {
            //prepara o atributo para a consulta
            pst = connection.prepareStatement(sql);
            // define os valores "?" da consulta, na mesma ordem
            pst.setString(1, person.getName());
            pst.setString(2, person.getDateb());
            pst.setString(3, person.getAddress());
            pst.setInt(4, id);
            // executa o comando
            pst.execute();
            //fecha a conexão, FECHAR SEMPRE
            closeConnection(pst, connection); //sempre temos que fechar a conexão
            //retorna que a atualização foi possível
            return true;
        } catch (SQLException e) {
            //exibe detalhes do erro
            e.printStackTrace();
            //retorna que não foi possível executar a ação
            return false;
        }
    }

    public boolean deletePerson(int id){
        this.connection = ConnectionFactory.getConnection();
        String sql = "DELETE FROM PERSON WHERE ID = ?";
        try {
            //prepara o atributo para a consulta
            pst = connection.prepareStatement(sql);
            // define os valores "?" da consulta, na mesma ordem
            pst.setInt(1, id);
            // executa o comando
            pst.execute();
            //fecha a conexão, FECHAR SEMPRE
            closeConnection(pst, connection); //sempre temos que fechar a conexão
            //retorna que foi possível apagar
            return true;
        } catch (SQLException e) {
            //exibe detalhes do erro
            e.printStackTrace();
            //retorna que não foi possível executar a ação
            return false;
        }
    }

    //método que finaliza a conexão com o banco. Lembre-se de SEMPRE FECHAR A CONEXÃO
    private void closeConnection(PreparedStatement pst, Connection connection){
        try {
            pst.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
