package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.AlunoBO;

import entities.Aluno;
import entities.Disciplina;
import entities.Professor;


@ManagedBean
@SessionScoped
public class AlunoBean {

    private Aluno novoAluno = new Aluno();
    private List<Aluno> listaAlunos = new ArrayList<Aluno>();
  
    private AlunoBO alunoBO = new AlunoBO();
    
    private Aluno selectedAluno = new Aluno();
    
    
    public Aluno getSelectedAluno() {
    	return selectedAluno;
    }
    
    public void setSelectedAluno(Aluno selectedAluno) {
    	this.selectedAluno = selectedAluno;
    }
    
    public String cadastrarAluno(){
        if (getNovoAluno().getNome().length()<3) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));

        	return "";
        } else {
        	
        	// fazer para aceitar de 2007 pra cima .
        	//int n;
        	//n = Integer.parseInt((String) getAluno().getRa().subSequence(0, 3));
        	//System.out.println(getAluno().getRa().subSequence(0, 4));
        	if (Integer.parseInt((String) getNovoAluno().getRa().subSequence(0, 4)) < 2007) {
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        				"Informe um R.A acima de 2007",""));
        		
        		return "";
        	} else {
        		
        			alunoBO.inserirAluno(novoAluno);
        			listaAlunos.clear();
        			listaAlunos.addAll(alunoBO.recuperarTodosAlunos());
        		
        			novoAluno = new Aluno();
        		
        		
        			return"listarAlunos.xhtml?faces-redirect=true";
    		}
    	}
       
	}
    
    public String alterarAluno() {
    	if (getNovoAluno().getNome().length()<3) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));

        	return "";
        } else {
        	
        	// fazer para aceitar de 2007 pra cima .
        	//int n;
        	//n = Integer.parseInt((String) getAluno().getRa().subSequence(0, 3));
        	//System.out.println(getAluno().getRa().subSequence(0, 4));
        	if (Integer.parseInt((String) getNovoAluno().getRa().subSequence(0, 4)) < 2007) {
        		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        				"Informe um R.A acima de 2007",""));
        		
        		return "";
        	} else {
        		
        			alunoBO.atualizarAluno(novoAluno);
        			listaAlunos.clear();
        			listaAlunos.addAll(alunoBO.recuperarTodosAlunos());
        		
        			novoAluno = new Aluno();
        		
        		
        			return"listarAlunos.xhtml?faces-redirect=true";
    		}
    	}
	}
    
    public String excluirAluno() {
    	alunoBO.removerAluno(novoAluno);
		listaAlunos.clear();
		listaAlunos.addAll(alunoBO.recuperarTodosAlunos());

		novoAluno = new Aluno();
	
	return "listarAlunos.xhtml?faces-redirect=true";
    }

	public Aluno getAluno() {
		return novoAluno;
	}

	public void setAluno(Aluno aluno) {
		this.novoAluno = aluno;
	}
	
	public Aluno getNovoAluno() {
		return novoAluno;
	}

	public void setNovoAluno(Aluno novoAluno) {
		this.novoAluno = novoAluno;
	}

	public AlunoBO getAlunoBO() {
		return alunoBO;
	}

	public void setAlunoBO(AlunoBO alunoBO) {
		this.alunoBO = alunoBO;
	}

	public List<Aluno> getListaAlunos() {
		// Limpar e atualizar lista
		listaAlunos.clear();
		listaAlunos.addAll(alunoBO.recuperarTodosAlunos());
		
		return listaAlunos;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
}
