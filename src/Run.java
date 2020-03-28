import entidade.Contato;
import persistencia.TxtPersistence;
import persistencia.core.IPersistencia;

public class Run {

	public static void main(String[] args) {
		
		try {
			IPersistencia db;
			db = new TxtPersistence("D:\\TEMP\\BANCO_DADOS.TXT");
			
			Contato c = new Contato();
			c.setNome("Raulivan Rodrigo da Silva");
			c.setId(10);
			c.setEmail("contato@raulivan.com.br");
			c.setTelefone("1234567890");
			
			db.create(c);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void exemplo_pesistencia_txt() throws Exception {
		
		IPersistencia db;
		db = new TxtPersistence("D:\\TEMP\\BANCO_DADOS.TXT");
		
		//INCLUSÃO
		/*
		for(int i=1; i <= 100; i++) {
			Contato c = new Contato();
			c.setNome("Fulando de Tal " + i);
			c.setId(i);
			c.setEmail("email" + i + "@gmail.com");
			c.setTelefone("99999" + i);
			db.create(c);
		}
		*/
		
		//ALTERAÇÃO
		/*
		Contato c = new Contato();
		c.setNome("REGISTRO ALTERADO");
		c.setId(10);
		c.setEmail("email@gmail.com");
		c.setTelefone("1234567890");
		
		db.update(c);*/
		
		
		System.out.println(((Contato)db.recoverAll().get(9)).getNome());
	}

}
