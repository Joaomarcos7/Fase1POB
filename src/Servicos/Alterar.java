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
		atualizar(); //altera paciente do atendimento de id 4
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void atualizar(){
		//localizar paciente com nome joao
		Query q = manager.query();
		q.constrain(Paciente.class);  				
		q.descend("Nome").constrain("João");		 
		List<Paciente> pacientes = q.execute(); 
		
		
		Query q2= manager.query();
		q2.constrain(Atendimento.class);
		q2.descend("id").constrain(4);
		List<Atendimento> atendimentos= q2.execute();
		
		if(pacientes.isEmpty() || atendimentos.isEmpty()){
			System.out.println("joao inexistente ou atendimento de id 4 inexistente");
		}
		else {
			Paciente p =  pacientes.get(0);
			Atendimento a= atendimentos.get(0);
			
			a.setPaciente(p);
			manager.store(a);
			manager.commit();
			
			System.out.println("alterou o paciente do atendimento de id 4");
		}
			
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
	
}
