package entities;

public class Atribuicao {
    private Long idAluno;
	private Long idTrabalho;
	private String conteudo;
	private float nota;
	
	
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public Long getIdTrabalho() {
		return idTrabalho;
	}
	public void setIdTrabalho(Long idTrabalho) {
		this.idTrabalho = idTrabalho;
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
	
	
}
