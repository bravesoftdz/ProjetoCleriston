package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import business.ProfessorBO;
import entities.Professor;




public class ProfessorBean {

	private Professor novoProfessor = new Professor();
    private List<Professor> listaProfessores = new ArrayList<Professor>();
  
    private ProfessorBO professorBO = new ProfessorBO();
    
    private Professor selectedProfessor = new Professor();
    
    
    public Professor getSelectedProfessor() {
    	return selectedProfessor;
    }
    
    public void setSelectedProfessor(Professor selectedProfessor) {
    	this.selectedProfessor = selectedProfessor;
    }
    
    public String cadastrarProfessor(){
        if(getProfessor().getNome().length()<3) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));

        	return"";
        }else{
        			novoProfessor = new Professor();
   
        			professorBO.inserirProfessor(novoProfessor);
        			listaProfessores.clear();
        			listaProfessores.addAll(professorBO.recuperarTodosProfessores());
        		
        			
        		
        		
        			return"listarProfessores.xhtml?faces-redirect=true";
        		}
        	}
        //	System.out.println("R.A: ");
        //System.out.println(aluno.getRa());
    	


	public Professor getProfessor() {
		return novoProfessor;
	}


	public void setProfessor(Professor professor) {
		this.novoProfessor = professor;
	}


	public List<Professor> getListaProfessores() {
		return listaProfessores;
	}


	public void setListaProfessores(List<Professor> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}
}
