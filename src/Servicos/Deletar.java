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
		apagar(); //apaga paciente João e suas cascatas!!
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void apagar() {
		Query q = manager.query();
		q.constrain(Paciente.class);
		q.descend("Nome").constrain("João");
		List<Paciente> resultados = q.execute(); 

		if (resultados.size() > 0) {
		
			Paciente p = resultados.get(0);
			manager.delete(p);
			manager.commit();
			System.out.println("apagou paciente João");
		} else
			System.out.println("paciente inexistente");
	}
	
	
	public void apagarTodos() {
		Query q= manager.query();
		
		q.constrain(Atendimento.class);
		List<Atendimento> atendimentos = q.execute();
		
		for(Atendimento a : atendimentos){
			manager.delete(a);
		}
		manager.commit();
		System.out.println("apagamos todos os atendimentos e sua cascata");
	}

	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
	
	
	
}
