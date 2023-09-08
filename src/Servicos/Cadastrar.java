package Servicos;

import java.time.LocalDateTime;

import com.db4o.ObjectContainer;

import Modelo.*;

public class Cadastrar {

	protected ObjectContainer manager;

	public Cadastrar() {
		manager = Util.conectarBanco();
		cadastrar();
		Util.desconectar();
		System.out.println("fim do cadastro..;");
	}

	public void cadastrar() {

		
		
		try {
			
			System.out.println("cadastrando...");
			
			Paciente paciente1= new Paciente("14059714445","João");
			Paciente paciente2=new Paciente("1405971442","Pedro");
			Paciente paciente3=new Paciente("1405971441","Felipe");
			
			Plano plano1 = new Plano("Premium");
			Plano plano2 = new Plano("Básico");
			
			
			Atendimento atendimento1= new Atendimento(1,"27/08/2023",paciente1,plano1);
			Atendimento atendimento2= new Atendimento(2,"28/08/2023",paciente1,plano1);
			Atendimento atendimento3= new Atendimento(3,"28/08/2023",paciente2,plano2);
			Atendimento atendimento4= new Atendimento(4,"25/08/2023",paciente3,plano2);
			Atendimento atendimento5 = new Atendimento(5,"31/08/2023",paciente1,plano1);
			
			manager.store(atendimento1);
			manager.store(atendimento2);
			manager.store(atendimento3);
			manager.store(atendimento4);
			manager.store(atendimento5);
			
			manager.commit();			
			
			
		}
		catch(Exception e ) {
			System.out.println(e.getMessage());
		}

		System.out.println("cadastro realizado com sucesso!");

	}

	public static void main(String[] args) {
		new Cadastrar();
	}

}
