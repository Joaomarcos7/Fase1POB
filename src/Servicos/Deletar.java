package Servicos;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import Modelo.*;
import Servicos.*;

public class Deletar {

	
	protected ObjectContainer manager;

	public Deletar() {
		manager = Util.conectarBanco();
		apagar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplica��o");
	}

	public void apagar() {
		//localizar pessoa com nome joana
		Query q = manager.query();
		q.constrain(Paciente.class);
		q.descend("nome").constrain("joana");
		List<Paciente> resultados = q.execute(); // select p from Pessoa p where p.nome="maria"

		if (resultados.size() > 0) {
			//apagar joana
			Paciente p = resultados.get(0);
			manager.delete(p);
			manager.commit();
			System.out.println("apagou joana e seus telefones (cascata)");
		} else
			System.out.println("pessoa inexistente");
	}

	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
	
	
	
}
