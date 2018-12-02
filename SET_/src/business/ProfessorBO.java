package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Professor;


public class ProfessorBO {
    
    private List<Professor> professorList= new ArrayList<Professor>();

    
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
    
    public void inserirProfessor(Professor novoProfessor){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into professores (nome) VALUES (?);");
            stmt.setString(1, novoProfessor.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Professor> recuperarTodosProfessores(){
    	professorList.clear();
        try {
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  Professores");
            while (rs.next()) {
            	Professor professor = new Professor();

            	professor.setId(rs.getLong(1));
            	professor.setNome(rs.getString(2));
            	professorList.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getProfessorList();
    }
    
   public Professor buscarProfessorById(long id) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  professores  where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Professor professor = new Professor();

            	professor.setId(rs.getLong(1));
            	professor.setNome(rs.getString(2));

            	return professor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Professor> pesquisaPorNome(String paramPesquisa){
    	professorList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  professores  where nome like ?");
            stmt.setString(1, "%"+paramPesquisa+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Professor professor = new Professor();

            	professor.setId(rs.getLong(1));
            	professor.setNome(rs.getString(2));
            	professorList.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getProfessorList();
    }
    
    public void removerProfessor(Professor professor){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from professores where id=?;");
            stmt.setLong(1, professor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void atualizarProfessor(Professor professor){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("UPDATE professores SET nome=? where id=?;");
            stmt.setString(1, professor.getNome());
            stmt.setLong(2, professor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Getters and setters
    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }
    

    
}