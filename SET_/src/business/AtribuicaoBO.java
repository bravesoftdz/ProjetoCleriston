package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Atribuicao;

public class AtribuicaoBO {

    
    private List<Atribuicao> atribuicoesList= new ArrayList<Atribuicao>();

    
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
    
    public void inserirAtribuicao(Atribuicao novaAtribuicao){
        try {
            PreparedStatement stmt = getConnection().prepareStatement("insert into atribuicoes (id_aluno,id_trabalho,conteudo) VALUES (?,?,?);");
            stmt.setLong(1, novaAtribuicao.getIdAluno());
            stmt.setLong(2, novaAtribuicao.getIdTrabalho());
            stmt.setString(3, novaAtribuicao.getConteudo());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public List<Atribuicao> recuperarTodasAtribuicoes(){
    	atribuicoesList.clear();
        try {
            Statement stm = getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  atribuicoes");
            while (rs.next()) {
            	Atribuicao atribuicao = new Atribuicao();

            	atribuicao.setIdAluno(rs.getLong(1));
            	atribuicao.setIdTrabalho(rs.getLong(2));
            	atribuicao.setConteudo(rs.getString(3));
            	atribuicao.setNota(rs.getInt(4));///int? não deveria ser float?
            	
            	atribuicoesList.add(atribuicao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAtribuicoesList();
    }
    
   public Atribuicao buscarAtribuicoesById(String id){
	   atribuicoesList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  atribuicoes  where id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Atribuicao atribuicao = new Atribuicao();

            	atribuicao.setIdAluno(rs.getLong(1));
            	atribuicao.setIdTrabalho(rs.getLong(2));
            	atribuicao.setConteudo(rs.getString(3));
            	atribuicao.setNota(rs.getInt(4));///int? não deveria ser float?
            	return atribuicao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Atribuicao> pesquisaPorNome(String paramPesquisa){
    	atribuicoesList.clear();
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM  atribuicoes  where conteudo like ?");
            stmt.setString(1, "%"+paramPesquisa+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Atribuicao atribuicoes = new Atribuicao();

            	atribuicoes.setIdAluno(rs.getLong(1));
            	atribuicoes.setIdTrabalho(rs.getLong(2));
            	atribuicoes.setConteudo(rs.getString(3));
            	atribuicoes.setNota(rs.getInt(4));///int? não deveria ser float?
            	atribuicoesList.add(atribuicoes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAtribuicoesList();
    }
    
    public void removerAtribuicao(Atribuicao atribuicao){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("delete from atribuicoes where id_aluno=? and id_trabalho=?;");//è assim pra fazer 'e' eu era pra pegar outra id?
            stmt.setLong(1, atribuicao.getIdAluno());
            stmt.setLong(2, atribuicao.getIdTrabalho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void atualizarAtribuicao(Atribuicao atribuicao){
         try {
            PreparedStatement stmt = getConnection().prepareStatement("UPDATE atribuicoes SET nota=? where id_aluno=? and id_trabalho=?;");//novamente, é assim?
            stmt.setFloat(1, atribuicao.getNota());
            stmt.setLong(2, atribuicao.getIdAluno());
            stmt.setLong(3, atribuicao.getIdTrabalho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //Getters and setters
    public List<Atribuicao> getAtribuicoesList() {
        return atribuicoesList;
    }

    public void setAtribuicoesList(List<Atribuicao> atribuicoesList) {
        this.atribuicoesList = atribuicoesList;
    }
    

    
}