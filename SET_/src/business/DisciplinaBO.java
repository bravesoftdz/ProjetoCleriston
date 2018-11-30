/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entities.Disciplina;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cleriston
 */
public class DisciplinaBO {
    
    private List<Disciplina> disciplinaList= new ArrayList<Disciplina>();
    
    public void alterarDisciplina(Disciplina alterarDisciplina) {
    	getDisciplinaList().add(alterarDisciplina);
    }
    
    public void inserirDisciplina(Disciplina novaDisciplina){
        getDisciplinaList().add(novaDisciplina);
    }
    
    public List recuperarTodasDisciplinas(){
        return getDisciplinaList();
    }
    
    //Getters and setters
        public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }
    
}
