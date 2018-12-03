/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Trabalho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cleriston
 */
public class TrabalhoBO {
    
    private List<Trabalho> trabalhoList= new ArrayList<Trabalho>();

    
    private Connection connection = null;

    public Connection getConnection() {
    	 String url = "jdbc:postgresql://trabalhoscleriston.cm71a7r8urep.sa-east-1.rds.amazonaws.com:5432/trabalhos";
         String username = "root";
         String password = "trabalho1234";
         try {
             Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Não é possível achar o driver!", e);
        }
        
        try {
            connection = DriverManager.getConnection(url, username, password);
        } 
         catch (SQLException e) {
            throw new IllegalStateException("Não foi possível conectar ao banco de dados.", e);
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void inserirTrabalho(Trabalho novoTrabalho){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into trabalhos (nome,id_disciplina) VALUES (?,?);");
            stmt.setString(1, novoTrabalho.getNome());
            stmt.setLong(2, novoTrabalho.getIdDisciplina());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Trabalho> recuperarTodosTrabalhos(){
    	trabalhoList.clear();
        try {
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  trabalhos");
            while (rs.next()) {
            	Trabalho trabalho = new Trabalho();

            	trabalho.setId(rs.getLong(1));
            	trabalho.setNome(rs.getString(2));
            	trabalho.setIdDisciplina(rs.getLong(3));
            	
            	trabalhoList.add(trabalho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getTrabalhoList();
    }
    
   public Trabalho buscarTrabalhoById(String id){
	   trabalhoList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  trabalhos  where id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Trabalho trabalho = new Trabalho();

            	trabalho.setId(rs.getLong(1));
            	trabalho.setNome(rs.getString(2));
            	trabalho.setIdDisciplina(rs.getLong(3));
                return trabalho;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Trabalho> pesquisaPorNome(String paramPesquisa){
    	trabalhoList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  trabalhos  where nome like ?");
            stmt.setString(1, "%"+paramPesquisa+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Trabalho trabalho = new Trabalho();

            	trabalho.setId(rs.getLong(1));
            	trabalho.setNome(rs.getString(2));
            	trabalho.setIdDisciplina(rs.getLong(3));
            	trabalhoList.add(trabalho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getTrabalhoList();
    }
    
    public void removerTrabalho(Trabalho trabalho){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from trabalhos where id=?;");
            stmt.setLong(1, trabalho.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void atualizarTrabalho(Trabalho trabalho){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("UPDATE trabalhos SET nome=? where id=?;");
            stmt.setString(1, trabalho.getNome());
            stmt.setLong(2, trabalho.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Getters and setters
    public List<Trabalho> getTrabalhoList() {
        return trabalhoList;
    }

    public void setTrabalhoList(List<Trabalho> trabalhoList) {
        this.trabalhoList = trabalhoList;
    }
    

    
}