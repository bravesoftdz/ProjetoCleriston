/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.DisciplinaBO;
import entities.Disciplina;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Cleriston
 */
@ManagedBean
@SessionScoped
public class DisciplinaBean {

    private Disciplina novaDisciplina = new Disciplina();
    private Disciplina alterarDisciplina = new Disciplina();
    private Disciplina selectedDisciplina = new Disciplina();
    
    
    public Disciplina getSelectedDisciplina() {
    	return selectedDisciplina;
    }
    
    public void setSelectedDisciplina(Disciplina selectedDisciplina) {
    	this.selectedDisciplina = selectedDisciplina;
    }
    
    private List<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
    
    private DisciplinaBO disciplinaBO = new DisciplinaBO();
    
    public String cadastrarDisciplina(){
        if(getNovaDisciplina().getNome().length()<3) {
        
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));
        	
        	return "";
        }else {
        	
        	
        	disciplinaBO.inserirDisciplina(novaDisciplina);
        	listaDisciplinas.clear();
        	listaDisciplinas.addAll(disciplinaBO.recuperarTodasDisciplinas());
        	
        	novaDisciplina = new Disciplina();
        	
        	
        	return"listarDisciplinas.xhtml?faces-redirect=true";
        }
        
        
    }
    
    public String alterarDisciplina() {
    	if(getNovaDisciplina().getNome().length()<3) {
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));
    		
    		return "";
    	}else {
    		disciplinaBO.atualizarDisciplina(alterarDisciplina);
    		listaDisciplinas.clear();
    		listaDisciplinas.addAll(disciplinaBO.recuperarTodasDisciplinas());
    		
    		alterarDisciplina = new Disciplina();
    		
    		return"listarDisciplinas.xhtml?faces-redirect=true";
    	}
    }
    
    public String excluirDisciplina() {
		disciplinaBO.removerDisciplina(novaDisciplina);
		listaDisciplinas.clear();
		listaDisciplinas.addAll(disciplinaBO.recuperarTodasDisciplinas());

		novaDisciplina = new Disciplina();
	
	return "listarDisciplinas.xhtml?faces-redirect=true";
    }
    
    
    //Getters and setters
    public Disciplina getNovaDisciplina() {
        return novaDisciplina;
    }

    public void setNovaDisciplina(Disciplina novaDisciplina) {
        this.novaDisciplina = novaDisciplina;
    }
/*
    public List<Disciplina> getListaDisciplinas() {
        return disciplinaBO.getDisciplinaList();
        
    }*/
    
    public List<Disciplina> getListaDisciplinas() {
		// Limpar e atualizar lista
		listaDisciplinas.clear();
		listaDisciplinas.addAll(disciplinaBO.recuperarTodasDisciplinas());

		return listaDisciplinas;
	}
    
    
    
}
