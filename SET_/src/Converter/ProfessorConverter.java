package Converter;

import javax.faces.convert.FacesConverter;

import business.ProfessorBO;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import entities.Professor;

@FacesConverter("Professor")
public class ProfessorConverter implements Converter, Serializable {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		// Recebe o código e retorna o objeto;
		
		if (codigo != null && !codigo.isEmpty()) {
			Professor professor = new Professor();
			ProfessorBO professorBO = new ProfessorBO();
			
			professor = professorBO.buscarProfessorById(new Long(codigo));
			
			return professor;
		}
		
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		// Recebe o objeto e retorna o código
		
		if (objeto != null) {
			Professor professor = (Professor) objeto;
			return String.valueOf(professor.getId());
		}
		
		return null;
	}
	
	public long getAsLong(FacesContext arg0, UIComponent arg1, Object objeto) {
		// Recebe o objeto e retorna o código
		
		if (objeto != null) {
			Professor professor = (Professor) objeto;
			return new Long(professor.getId());
		}
		
		return 0;
	}
	
}
