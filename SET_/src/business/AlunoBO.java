/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Aluno;
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
public class AlunoBO {
    
    private List<Aluno> alunoList= new ArrayList<Aluno>();

    
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
    
    public void inserirAluno(Aluno novoAluno){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into alunos (nome,ra) VALUES (?,?);");
            stmt.setString(1, novoAluno.getNome());
            stmt.setString(2, novoAluno.getRa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List recuperarTodosAlunos(){
    	alunoList.clear();
        try {
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  alunos");
            while (rs.next()) {
            	Aluno aluno = new Aluno();

            	aluno.setId(rs.getLong(1));
            	aluno.setNome(rs.getString(2));
            	aluno.setRa(rs.getString(3));
            	alunoList.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAlunoList();
    }
    
   public Aluno buscarAlunoById(String id){
	   alunoList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  alunos  where id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Aluno aluno = new Aluno();

            	aluno.setId(rs.getLong(1));
            	aluno.setNome(rs.getString(2));
            	aluno.setRa(rs.getString(3));
                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List pesquisaPorNome(String paramPesquisa){
    	alunoList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  alunos  where nome like ?");
            stmt.setString(1, "%"+paramPesquisa+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Aluno aluno = new Aluno();

            	aluno.setId(rs.getLong(1));
            	aluno.setNome(rs.getString(2));
            	aluno.setRa(rs.getString(3));
            	alunoList.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAlunoList();
    }
    
    public void removerAluno(Aluno aluno){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from alunos where id=?;");
            stmt.setLong(1, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void atualizarAluno(Aluno aluno){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("UPDATE alunos SET nome=? where id=?;");
            stmt.setString(1, aluno.getNome());
            stmt.setLong(2, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Getters and setters
    public List<Aluno> getAlunoList() {
        return alunoList;
    }

    public void setAlunoList(List<Aluno> alunoList) {
        this.alunoList = alunoList;
    }
    

    
}