package Servicos;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Servicos.Listar;
import Servicos.Util;
import Modelo.Atendimento;
import Modelo.Plano;
import Modelo.Paciente;


public class Listar {
	protected ObjectContainer manager;

	public Listar(){
		manager = Util.conectarBanco();
		listarAtendimentos();
		listarPlanos();
		listarPacientes();
		Util.desconectar();				
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void listarPacientes(){
		System.out.println("\n---listagem dos pacientes:");
		
		Query q = manager.query();
		q.constrain(Paciente.class);  				
		List<Paciente> resultados = q.execute();
		for(Paciente p: resultados)
			System.out.println(p.toString());
	}
	public void listarAtendimentos(){
		System.out.println("\n---listagem dos atendimentos:");
		
		Query q = manager.query();
		q.constrain(Atendimento.class);  				
		List<Atendimento> resultados = q.execute();
		for(Atendimento a: resultados)
			System.out.println(a.toString());
	}

	public void listarPlanos(){
		System.out.println("\n---listagem dos planos:");
		
		Query q = manager.query();
		q.constrain(Plano.class);  				
		List<Plano> resultados = q.execute();
		for(Plano p: resultados)
			System.out.println(p.toString());
	}




	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}
