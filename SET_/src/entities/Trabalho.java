package entities;

public class Trabalho {
	private long id;
	private String nome;
	private String conteudo; 
	private float nota;
	private long idDisciplina;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	

	
	
}
