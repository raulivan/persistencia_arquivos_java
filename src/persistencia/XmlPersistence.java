package persistencia;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

import entidade.Contato;
import persistencia.core.IPersistencia;
import persistencia.libxml.ArquivoXML;
import persistencia.libxml.ItemXML;

public class XmlPersistence implements IPersistencia<Contato>{

	private String _caminho;
	
	private Marshaller marshaller;
    private Unmarshaller unmarshaller;
	
	public XmlPersistence(String caminho) throws Exception {
		this._caminho = caminho;
	}
		
	private Object abrirXML() throws Exception {
		JAXBContext context = null;

        context = JAXBContext.newInstance(ArquivoXML.class);
        unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(
                new FileInputStream(_caminho));
	        
	}
	
	private void salvarXML(Object object, String fileName) throws Exception {
        final StringWriter out = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        marshaller = context.createMarshaller();
        marshaller.setProperty(
        		javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE);
        marshaller.marshal(object, new StreamResult(out));
       
        Writer writer = null;
    
        writer = new FileWriter(fileName);
        marshaller.marshal(object, writer);
       
        if (writer != null) {
            writer.close();
        }
    }
	
	@Override
	public boolean create(Contato registro) throws Exception {
		ArquivoXML arquivoXML = (ArquivoXML) abrirXML();
		arquivoXML.getContatos().add(new ItemXML(registro));
		
		salvarXML(arquivoXML,_caminho);

		return true;
	}

	@Override
	public Contato recover(int id) throws Exception {
		ArquivoXML arquivoXML = (ArquivoXML) abrirXML();
		
		for(ItemXML item :arquivoXML.getContatos())
		{
			if(item.get().getId() == id)
				return item.get();
		}
		return null;
	}

	@Override
	public boolean update(Contato registro) throws Exception {
		ArquivoXML arquivoXML = (ArquivoXML) abrirXML();
		
		for(int i = 0; i < arquivoXML.getContatos().size(); i++)
			if(arquivoXML.getContatos().get(i).get().getId() == registro.getId()) {
				arquivoXML.getContatos().set(i, new ItemXML(registro));
				salvarXML(arquivoXML,_caminho);
				return true;
			}
		
		return false;
	}

	@Override
	public boolean delete(int id) throws Exception {
		ArquivoXML arquivoXML = (ArquivoXML) abrirXML();
		
		for(int i = 0; i < arquivoXML.getContatos().size(); i++)
			if(arquivoXML.getContatos().get(i).get().getId() == id) {
				arquivoXML.getContatos().remove(i);
				salvarXML(arquivoXML,_caminho);
				return true;
			}
		return false;
	}

	@Override
	public List<Contato> recoverAll() throws Exception {
		ArquivoXML arquivoXML = (ArquivoXML) abrirXML();
		
		List<Contato> retorno = new ArrayList<Contato>();
		
		for(ItemXML item: arquivoXML.getContatos()) 
			retorno.add(item.get());
		
		return retorno;
	}

	private String geraXML(Object object) throws Exception {
		final StringWriter out = new StringWriter();
        JAXBContext context = null;

        context = JAXBContext.newInstance(object.getClass());
        marshaller = context.createMarshaller();
        marshaller.setProperty(
                javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                Boolean.TRUE
        );
        marshaller.marshal(object, new StreamResult(out));
        
        return out.toString();
	}
	
}
