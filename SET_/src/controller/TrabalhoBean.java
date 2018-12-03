package controller;

import business.TrabalhoBO;
import entities.Trabalho;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean
@SessionScoped
public class TrabalhoBean {
	private Trabalho alterarTrabalho = new Trabalho();
    private Trabalho selectedTrabalho = new Trabalho();
    private List<Trabalho> listaTrabalhos = new ArrayList<Trabalho>();
    private TrabalhoBO trabalhoBO = new TrabalhoBO();

    private Trabalho novoTrabalho = new Trabalho();
    public Trabalho getNovoTrabalho() {
		return novoTrabalho;
	}

	public void setNovoTrabalho(Trabalho novoTrabalho) {
		this.novoTrabalho = novoTrabalho;
	}

	public Trabalho getAlterarTrabalho() {
		return alterarTrabalho;
	}

	public void setAlterarTrabalho(Trabalho alterarTrabalho) {
		this.alterarTrabalho = alterarTrabalho;
	}

	public List<Trabalho> getListaTrabalhos() {
        listaTrabalhos.clear();
        listaTrabalhos.addAll(trabalhoBO.recuperarTodosTrabalhos());
		return listaTrabalhos;
	}

	public void setListaTrabalhos(List<Trabalho> listaTrabalhos) {
		this.listaTrabalhos = listaTrabalhos;
	}

	public TrabalhoBO getTrabalhoBO() {
		return trabalhoBO;
	}

	public void setTrabalhoBO(TrabalhoBO trabalhoBO) {
		this.trabalhoBO = trabalhoBO;
	}
    
    public Trabalho getSelectedTrabalho() {
        return selectedTrabalho;
    }
    
    public void setSelectedTrabalho(Trabalho selectedTrabalho) {
        this.selectedTrabalho = selectedTrabalho;
    }
    
    public String cadastrarTrabalho() {
        if (getNovoTrabalho().getNome().length()<3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Infome um nome com mais de 3 letras",""));

            return "";
        } else {
            trabalhoBO.inserirTrabalho(novoTrabalho);
            listaTrabalhos.clear();
            listaTrabalhos.addAll(trabalhoBO.recuperarTodosTrabalhos());
            
            novoTrabalho = new Trabalho();
            
            return "listarTrabalhos.xhtml?faces-redirect=true";
        }
    }
}
