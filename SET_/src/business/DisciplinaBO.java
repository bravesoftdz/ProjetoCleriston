/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Disciplina;
import entities.Professor;

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
public class DisciplinaBO {
    
    private List<Disciplina> disciplinaList= new ArrayList<Disciplina>();

    
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
    
    public void inserirDisciplina(Disciplina novaDisciplina){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into disciplinas (id_professor,nome) VALUES (?,?);");
            stmt.setLong(1, novaDisciplina.getIdProfessor());
            stmt.setString(2, novaDisciplina.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List recuperarTodasDisciplinas(){
        disciplinaList.clear();
        try {
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  disciplinas");
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();

                disciplina.setId(rs.getLong(1));
                disciplina.setNome(rs.getString(2));
                disciplina.setIdProfessor(rs.getLong(3));
                disciplinaList.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getDisciplinaList();
    }
    
   public Disciplina buscarDisciplinaById(String id){
        disciplinaList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  disciplinas  where id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();

                disciplina.setId(rs.getLong(1));
                disciplina.setNome(rs.getString(2));
                disciplina.setIdProfessor(rs.getLong(3));
                disciplinaList.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List pesquisaPorNome(String paramPesquisa){
        disciplinaList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  disciplinas  where nome like ?");
            stmt.setString(1, "%"+paramPesquisa+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();

                disciplina.setId(rs.getLong(1));
                disciplina.setNome(rs.getString(2));
                disciplina.setIdProfessor(rs.getLong(3));
                disciplinaList.add(disciplina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getDisciplinaList();
    }
    
    public void removerDisciplina(Disciplina disciplina){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from disciplinas where id=?;");
            stmt.setString(1, disciplina.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void atualizarDisciplina(Disciplina disciplina){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("UPDATE disciplinas SET nome=? where id=?;");
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Getters and setters
    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }
    

    
}