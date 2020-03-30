package persistencia.libxml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "registros")
@XmlAccessorType(XmlAccessType.NONE)
public class ArquivoXML {

	 @XmlElements(@XmlElement(name = "contato", type = ItemXML.class))
	 private ArrayList<ItemXML> contatos;
	 
	 public ArquivoXML() {
		 this(new ArrayList());
		 this.contatos = new ArrayList();
	 }
	 
	 public ArquivoXML(ArrayList contato) {
		 this.contatos = new ArrayList();
	 }

	public ArrayList<ItemXML> getContatos() {
		return contatos;
	}

	public void setContatos(ArrayList<ItemXML> contatos) {
		this.contatos = contatos;
	}
}
