package business;

import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoBO {
		    
	    private List<Aluno> alunoList= new ArrayList<Aluno>();
	    
	    public void inserirAluno(Aluno novoAluno){
	        getAlunoList().add(novoAluno);
	    }
	    
	    public List recuperarTodosAlunos(){
	        return getAlunoList();
	    }

		public List<Aluno> getAlunoList() {
			return alunoList;
		}

		public void setAlunoList(List<Aluno> alunoList) {
			this.alunoList = alunoList;
		}
	    
	    //Getters and setters
	     
}
