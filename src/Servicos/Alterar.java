package Servicos;

import java.time.LocalDateTime;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Modelo.*;
import Servicos.*;

public class Alterar {

	protected ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		atualizar();
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void atualizar(){
		//localizar pessoa com nome joao
		Query q = manager.query();
		q.constrain(Paciente.class);  				
		q.descend("nome").constrain("joao");		 
		List<Paciente> resultados = q.execute(); // select p from Pessoa p where p.nome="joao"
		
		if(resultados.size()>0) {
			Paciente p =  resultados.get(0);
			p.setNome("joana");
			
			//adicionar novo telefone
			p.adicionar(new Atendimento(5,LocalDateTime.now().toString()));;
			
			//remover telefone existente
			Atendimento a = p.getAtendimentos().get(5);
			try {
			p.remover(5);  
			a.setPaciente(null);	 	//este objeto pode ficar� orfao no banco 
			}
			catch(Exception e){
				e.getMessage();
			}
			manager.store(p);
			manager.store(a);	
			manager.delete(a);	//deletar objeto orfao no banco 
			manager.commit();
			System.out.println("alterou nome e telefones de joao");
		}
		else
			System.out.println("joao inexistente");
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
	
}
