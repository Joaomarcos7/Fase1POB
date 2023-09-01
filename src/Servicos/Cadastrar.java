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

		Atendimento a;
		Paciente paciente;
		Plano plano;

		try {

			paciente = new Paciente("14059714445", "João");
			plano = new Plano("Standard");
			a = new Atendimento(1,LocalDateTime.now().toString());

			a.setPaciente(paciente);
			a.setPlano(plano);

			manager.store(a);
			manager.commit();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			paciente = new Paciente("14059714324", "Marcos");
			plano = new Plano("Standard");
			a = new Atendimento(1, LocalDateTime.now().toString());
			
			
			a.setPaciente(paciente);
			a.setPlano(plano);

			manager.store(a);
			manager.commit();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {

			paciente = new Paciente("140597144312", "Pedro");

			plano = new Plano("Premium");

			a = new Atendimento(1, LocalDateTime.now().toString());

			paciente.adicionar(a);
			plano.adicionar(a);

			manager.store(a);
			manager.commit();

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("cadastro realizado com sucesso!");

	}

	public static void main(String[] args) {
		new Cadastrar();
	}

}
