package Servicos;


import com.db4o.Db4oEmbedded;

import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import Modelo.*;


public class Util {

	
	static ObjectContainer manager; //gerenciador do banco
	
	public static ObjectContainer conectarBanco() {
		
		if(manager!=null) {
			return manager; //conexao ja realizada
		}
		
		EmbeddedConfiguration config= Db4oEmbedded.newConfiguration();
		config.common().messageLevel(0);
		
		
		config.common().objectClass(Atendimento.class).cascadeOnDelete(false);
		config.common().objectClass(Atendimento.class).cascadeOnUpdate(true);
		config.common().objectClass(Atendimento.class).cascadeOnActivate(true);
		config.common().objectClass(Paciente.class).cascadeOnDelete(false);
		config.common().objectClass(Paciente.class).cascadeOnUpdate(true);
		config.common().objectClass(Paciente.class).cascadeOnActivate(true);
		config.common().objectClass(Plano.class).cascadeOnDelete(false);
		config.common().objectClass(Plano.class).cascadeOnUpdate(true);
		config.common().objectClass(Plano.class).cascadeOnActivate(true);
		
		
		manager= Db4oEmbedded.openFile(config,"banco.db4o");
		return manager;
		
		
	}
	
	public static void desconectar() {
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	
	
	
}
