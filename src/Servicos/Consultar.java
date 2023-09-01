package Servicos;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import Modelo.*;

public class Consultar {

	protected ObjectContainer manager;

	public Consultar(){
		manager = Util.conectarBanco();
		consultar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void consultar(){
		System.out.println("\n---listar pacientes em ordem alfabetica");
		Query q1 = manager.query();
		q1.constrain(Paciente.class);  
		q1.descend("nome").orderAscending();
		List<Paciente> pessoas = q1.execute();
		for(Paciente p: pessoas)
			System.out.println(p);
	
		System.out.println("\n---listar pessoas que nasceram no mes 02");
		Query q2 = manager.query();
		q2.constrain(Paciente.class);  
		q2.descend("dtnascimento").constrain("/02/").contains();
		q2.descend("nome").orderAscending();
		pessoas = q2.execute();
		for(Paciente p: pessoas)
			System.out.println(p);
	
		System.out.println("\n---listar pessoas com 1 telefone");
		Query q3 = manager.query();
		q3.constrain(Paciente.class);  
		q3.constrain(new Filtro());
		pessoas = q3.execute();
		for(Paciente p: pessoas)
			System.out.println(p);
		
		
		System.out.println("\n---listar telefones fixos");
		Query q4 = manager.query();
		q4.constrain(Atendimento.class);  
		q4.descend("numero").constrain("3").startsWith(true);
		List<Atendimento> atendimentos = q4.execute();
		for(Atendimento a: atendimentos)
			System.out.println(a);
	}
	
	public static void main(String[] args) {
		new Consultar();
	}


//classe interna 
class Filtro implements Evaluation {
	public void evaluate(Candidate candidate) {
		//obter cada objeto da classe Pessoa que esta no banco
		Paciente p = (Paciente) candidate.getObject();
		
		if(p.getAtendimentos().size()==1) 
			candidate.include(true); 	//incluir objeto no resultado da consulta
		else		
			candidate.include(false);	//excluir objeto do resultado da consulta
		}
	}


}
	

