import entidade.Contato;
import persistencia.TxtPersistence;
import persistencia.XmlPersistence;
import persistencia.core.IPersistencia;

public class Run {

	public static void main(String[] args) {
		
		try {
			int metodo = 1;
			IPersistencia db;
			
			if(metodo == 1)
				db = new TxtPersistence("D:\\TEMP\\BANCO_DADOS.txt");
			else
				db = new XmlPersistence("D:\\TEMP\\BANCO_DADOS.xml");
			
			Contato c = new Contato();
			c.setNome("Raulivan Rodrigo");
			c.setId(100);
			c.setEmail("eu@raulivan.com.br");
			c.setTelefone("1234567890");
			
			db.create(c);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}
}
