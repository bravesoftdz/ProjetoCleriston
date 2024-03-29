package entities;

import java.io.Serializable;

import Interface.SampleEntity;

public class Professor implements Serializable,SampleEntity {
	
	private long id;
	private String nome;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return ""+ nome;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			return (this.getId() == ((Professor) obj).getId());
		}
		return super.equals(obj);
	}
	
	
	
}
