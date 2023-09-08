package Modelo;

import java.util.ArrayList;

public class Plano {

	String nome;
	ArrayList<Atendimento> lista = new ArrayList<Atendimento>();
	
	
	public Plano(String nome) {
		
		this.nome=nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	

	public void adicionar(Atendimento a){
		lista.add(a);
	}

	@Override
	public String toString() {
		return "Plano [nome=" + nome + ", lista=" + lista.toString() + "]";
	}
	

	public String ToStringPattern() {
		return "Paciente [  " +  "Nome=" + this.nome + "]";
	}
	
	

	
	
}
