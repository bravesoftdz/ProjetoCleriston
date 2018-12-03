package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import business.AtribuicaoBO;
import entities.Atribuicao;

@ManagedBean
@SessionScoped
public class AtribuicaoBean {

    private Atribuicao novaAtribuicao = new Atribuicao();
    private Atribuicao alterarAtribuicao = new Atribuicao();
    private Atribuicao selectedAtribuicao = new Atribuicao();
    private List<Atribuicao> listaAtribuicao = new ArrayList<Atribuicao>();
    private AtribuicaoBO atribuicaoBO = new AtribuicaoBO();
    
    
    public Atribuicao getSelectedAtribuicao() {
        return selectedAtribuicao;
    }
    
    public void setSelectedAtrbuicoes(Atribuicao selectedAtribuicao) {
        this.selectedAtribuicao = selectedAtribuicao;
    }
    
    public String cadastrarAtribuicao(){
        if(getNovaAtribuicao().getConteudo().length()<3) {
        
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe um Conteúdo com mais de 3 letras",""));
            return "";
        } else {
            atribuicaoBO.inserirAtribuicao(novaAtribuicao);
            listaAtribuicao.clear();
            listaAtribuicao.addAll(atribuicaoBO.recuperarTodasAtribuicoes());
            
            novaAtribuicao = new Atribuicao();
            
            return "listarAtribuicoes.xhtml?faces-redirect=true";
        }
    }
    
    public String alterarAtribuicao() {
    	
    	Atribuicao atribuicao = getSelectedAtribuicao();
    	
        if(atribuicao.getNota() < 0.0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma nota positiva",""));
            return "";
        }else if(atribuicao.getNota() > 10.0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Informe uma nota entre  0.0 a 10.0",""));
            return "";
        }else
            {
            atribuicaoBO.atualizarAtribuicao(atribuicao);
            listaAtribuicao.clear();
            listaAtribuicao.addAll(atribuicaoBO.recuperarTodasAtribuicoes());
            
            alterarAtribuicao = new Atribuicao();
            
            return"listarTrabalhos.xhtml?faces-redirect=true";
        }
    }
    
    public String excluirAtribuicao() {
        atribuicaoBO.removerAtribuicao(novaAtribuicao);
        listaAtribuicao.clear();
        listaAtribuicao.addAll(atribuicaoBO.recuperarTodasAtribuicoes());

        novaAtribuicao = new Atribuicao();
    
    return "listarTrabalhos.xhtml?faces-redirect=true";
    }
    
    
    //Getters and setters
    public Atribuicao getNovaAtribuicao() {
        return novaAtribuicao;
    }

    public void setNovaAtribuicao(Atribuicao novaAtribuicao) {
        this.novaAtribuicao = novaAtribuicao;
    }

    public List<Atribuicao> getListaAtribuicao() {
        // Limpar e atualizar lista
        listaAtribuicao.clear();
        listaAtribuicao.addAll(atribuicaoBO.recuperarTodasAtribuicoes());

        return listaAtribuicao;
    }
    
    
    
}
