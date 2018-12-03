package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.AtribuicoesBO;
import entities.Atribuicoes;

@ManagedBean
@SessionScoped
public class AtribuicoesBean {

    private Atribuicoes novaAtribuicoes = new Atribuicoes();
    private Atribuicoes alterarAtribuicoes = new Atribuicoes();
    private Atribuicoes selectedAtribuicoes = new Atribuicoes();
    private List<Atribuicoes> listaAtribuicoes = new ArrayList<Atribuicoes>();
    private AtribuicoesBO atribuicoesBO = new AtribuicoesBO();
    
    
    public Atribuicoes getSelectedAtribuicoes() {
        return selectedAtribuicoes;
    }
    
    public void setSelectedAtrbuicoes(Atribuicoes selectedAtribuicoes) {
        this.selectedAtribuicoes = selectedAtribuicoes;
    }
    
    public String cadastrarAtribuicoes(){
        if(getNovaAtribuicoes().getConteudo().length()<3) {
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe um Conteúdo com mais de 3 letras",""));
            return "";
        } else {
            atribuicoesBO.inserirAtribuicoes(novaAtribuicoes);
            listaAtribuicoes.clear();
            listaAtribuicoes.addAll(atribuicoesBO.recuperarTodasAtribuicoes());
            
            novaAtribuicoes = new Atribuicoes();
            
            return "listarTrabalhos.xhtml?faces-redirect=true";
        }
    }
    
    public String alterarAtribuicoes() {
        if(getNovaAtribuicoes().getNota() >= 0.0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma nota positiva",""));
            return "";
        }else if(getNovaAtribuicoes().getNota() <= 10.0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma nota entre  0.0 a 10.0",""));
            return "";
        }else
            {
            atribuicoesBO.atualizarAtribuicoes(alterarAtribuicoes);
            listaAtribuicoes.clear();
            listaAtribuicoes.addAll(atribuicoesBO.recuperarTodasAtribuicoes());
            
            alterarAtribuicoes = new Atribuicoes();
            
            return"listarTrabalhos.xhtml?faces-redirect=true";
        }
    }
    
    public String excluirAtribuicoes() {
        atribuicoesBO.removerAtribuicoes(novaAtribuicoes);
        listaAtribuicoes.clear();
        listaAtribuicoes.addAll(atribuicoesBO.recuperarTodasAtribuicoes());

        novaAtribuicoes = new Atribuicoes();
    
    return "listarTrabalhos.xhtml?faces-redirect=true";
    }
    
    
    //Getters and setters
    public Atribuicoes getNovaAtribuicoes() {
        return novaAtribuicoes;
    }

    public void setNovaAtribuicoes(Atribuicoes novaAtribuicoes) {
        this.novaAtribuicoes = novaAtribuicoes;
    }

    public List<Atribuicoes> getListaAtribuicoes() {
        // Limpar e atualizar lista
        listaAtribuicoes.clear();
        listaAtribuicoes.addAll(atribuicoesBO.recuperarTodasAtribuicoes());

        return listaAtribuicoes;
    }
    
    
    
}
