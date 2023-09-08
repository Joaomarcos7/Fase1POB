package Servicos;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Modelo.*;
import Servicos.*;

public class Deletar {

	
	protected ObjectContainer manager;

	public Deletar() {
		manager = Util.conectarBanco();
		apagarTodos();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void apagar() {
		Query q = manager.query();
		q.constrain(Atendimento.class);
		q.descend("id").constrain(4);
		List<Atendimento> resultados = q.execute(); 
		
		if (resultados.size() > 0) {
		
			Atendimento a= resultados.get(0);
			manager.delete(a);
			manager.commit();
			System.out.println("apagou Atendimento de id 4");
		} else
			System.out.println("Atendimento inexistente");
	}
	
	
	public void apagarTodos() { //quando quero zerar o banco!
		Query q= manager.query();
		
		q.constrain(Atendimento.class);
		List<Atendimento> atendimentos = q.execute();
		
		Query q2= manager.query();
		
		q2.constrain(Paciente.class);
		List<Paciente> pacientes= q2.execute();
		
		Query q3= manager.query();
		
		q3.constrain(Plano.class);
		List<Plano> planos= q3.execute();
		
		for(Atendimento a : atendimentos){
			manager.delete(a);
		}

		for(Paciente p : pacientes){
			manager.delete(p);
		}

		for(Plano pl : planos){
			manager.delete(pl);
		}
		
		manager.commit();
		System.out.println("apagamos todos os atendimentos e suas cascata");
	}

	
	
	public void apagarPaciente() {
		Query q = manager.query();
		q.constrain(Paciente.class);
		q.descend("Nome").constrain("Felipe");
		List<Paciente> resultados = q.execute(); 
		
		if (resultados.size() > 0) {
		
			Paciente a= resultados.get(0);
			manager.delete(a);
			manager.commit();
			System.out.println("apagou Atendimento de id 4");
		} else
			System.out.println("Atendimento inexistente");
		
	}
	
	
	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
	
	
	
}
