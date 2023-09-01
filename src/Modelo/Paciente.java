package Modelo;

import java.util.TreeMap;

public class Paciente {

	
	String CPF;
	
	String Nome;
	
	TreeMap<Integer,Atendimento> lista= new TreeMap<Integer,Atendimento>();
	
	public Paciente(String CPF,String Nome) {
		this.CPF=CPF;
		this.Nome=Nome;
	}
	
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cpf) {
		this.CPF=cpf;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String Nome) {
		this.Nome=Nome;
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

		
		public TreeMap<Integer,Atendimento> getAtendimentos(){
			return this.lista;
		}

	
		public void SetAtendimentos(TreeMap<Integer,Atendimento> lista ) {
			this.lista= lista;
		}
	
	}
	
