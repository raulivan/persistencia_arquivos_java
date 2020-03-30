package persistencia.libxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entidade.Contato;

@XmlRootElement(name = "contato")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemXML {

	@XmlElement(name = "id", required = true)
	private int id;
	@XmlElement(name = "nome", required = true)
	private String nome;
	@XmlElement(name = "telefone", required = false)
	private String telefone;
	@XmlElement(name = "email", required = false)
	private String email;
	
	public ItemXML() {}
	
	public ItemXML(Contato contato) {
		id = contato.getId();
		nome = contato.getNome();
		telefone = contato.getTelefone();
		email = contato.getEmail();
	}
	
	public Contato get() {
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		
		return contato;
	}
}
