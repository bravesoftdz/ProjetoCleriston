package business;

import java.util.ArrayList;
import java.util.List;
import entities.Trabalho;

public class TrabalhoBO {
	
	private static List<Trabalho> trabalhoList= new ArrayList<Trabalho>();
    
    public static void inserirTrabalho(Trabalho novoTrabalho){
        getTrabalhoList().add(novoTrabalho);
    }
    
    public List<Trabalho> recuperarTodostrabalhos(){
        return getTrabalhoList();
    }

	public static List<Trabalho> getTrabalhoList() {
		return trabalhoList;
	}

	public void setTrabalhoList(List<Trabalho> trabalhoList) {
		TrabalhoBO.trabalhoList = trabalhoList;
	}

}
