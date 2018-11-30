package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import business.TrabalhoBO;
import entities.Trabalho;



public class TrabalhoBean {
	private Trabalho novoTrabalho = new Trabalho();
    private List<Trabalho> listaTrabalhos = new ArrayList<Trabalho>();
  
    private TrabalhoBO trabalhoBO = new TrabalhoBO();
    
    private Trabalho selectedTrabalho = new Trabalho();
    
    
    public Trabalho getSelectedTrabalho() {
    	return selectedTrabalho;
    }
    
    public void setSelectedTrabalho(Trabalho selectedTrabalho) {
    	this.selectedTrabalho = selectedTrabalho;
    }
    
    public String cadastrarTrabalho(){
        if(getTrabalho().getNome().length()<3) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
        			"Infome um nome com mais de 3 letras",""));

        	return"";
        }
        	
        	else {
        		
        		TrabalhoBO.inserirTrabalho(novoTrabalho);
        			listaTrabalhos.clear();
        			listaTrabalhos.addAll(trabalhoBO.recuperarTodostrabalhos());
        		
        			novoTrabalho = new Trabalho();
        		
        		
        			return"listarTrabalhos.xhtml?faces-redirect=true";
        		}
        	}

	public Trabalho getTrabalho() {
		return novoTrabalho;
	}


	public void setTrabalho(Trabalho trabalho) {
		this.novoTrabalho = trabalho;
	}


	public List<Trabalho> getListaTrabalhos() {
		return listaTrabalhos;
	}


	public void setListaTrabalhos(List<Trabalho> listaTrabalhos) {
		this.listaTrabalhos = listaTrabalhos;
	}

}
