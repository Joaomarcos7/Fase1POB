package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Plano {

	String nome;
	TreeMap<Integer, Atendimento> lista=new TreeMap<Integer,Atendimento>();
	
	
	
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
		lista.put(a.getId(), a);
	}
	
	public void remover(int a) throws Exception {
		if(lista.containsKey(a)) {
			lista.remove(a);
		}
		else {
			throw new Exception("nao contem essa chave na lista!");
		}
	}
	
	

	
	
}
