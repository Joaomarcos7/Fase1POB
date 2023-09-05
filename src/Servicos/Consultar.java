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
		System.out.println("\n---listar atendimentos na data 28/08/2023");
		Query q1 = manager.query();
		q1.constrain(Atendimento.class);  
		q1.descend("data").constrain("28/08/2023");
		List<Atendimento> atendimentos= q1.execute();
		for(Atendimento a: atendimentos) {
			System.out.println(a);
		}
		
		
		System.out.println("\n---Atendimentos do paciente de nome João ");
		Query q2 = manager.query();
		q2.constrain(Atendimento.class);  
		q2.descend("paciente").descend("Nome").constrain("João");
		List<Atendimento> atendimentos2= q2.execute();
		for(Atendimento a: atendimentos2) {
			System.out.println(a);
		}
		
		
		System.out.println("\n---Pacientes com mais de 2 atendimentos agendados");
		Query q3 = manager.query();
		q3.constrain(Paciente.class);  
		q3.constrain( new Filtro() );
		List<Paciente> pacientes= q3.execute();
		for(Paciente p: pacientes) {
			System.out.println(p);
		}
		
		
		
		
	
	}
	
	public static void main(String[] args) {
		new Consultar();
	}


//classe interna 
private  class Filtro implements Evaluation {
	public void evaluate(Candidate candidate) {
		//obter cada objeto da classe Pessoa que esta no banco
		Paciente p = (Paciente) candidate.getObject();
		
		if(p.getAtendimentos().size()>2) 
			candidate.include(true); 	//incluir objeto no resultado da consulta
		else		
			candidate.include(false);	//excluir objeto do resultado da consulta
		}
	}


}
	

