package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import entidade.Contato;
import persistencia.core.IPersistencia;

public class TxtPersistence implements IPersistencia<Contato>{

	private String _caminho;
	
	public TxtPersistence(String caminho) {
		this._caminho = caminho;				
	}
	
	private List<String> abrirArquivo() throws Exception{
		
		List<String> linhas = new ArrayList<String>();
		BufferedReader buffRead = new BufferedReader(new FileReader(_caminho));
		
        String linha = "";
        do {
        	linha = buffRead.readLine();
        	if(linha == null || linha.isEmpty())
        		break;
        	linhas.add(linha);
        }while(true);
       
        buffRead.close();
        
        return linhas;
	}
	
	private void salvarArquivo(List<String> dados) throws Exception {
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(_caminho));
		   
		for(String linha: dados)
			buffWrite.append(linha + "\n");
		buffWrite.close();
		
	}
	
	@Override
	public boolean create(Contato registro)  throws Exception{
		List<String> dados = abrirArquivo();
		
		String registro_formatado = String.format("%08d|%-60s|%-10s|%-40s"
				,registro.getId()
				,registro.getNome()
				,registro.getTelefone()
				,registro.getEmail());
		
		dados.add(registro_formatado);
		
		salvarArquivo(dados);
		return true;
	}

	@Override
	public Contato recover(int id)  throws Exception {
		List<String> dados = abrirArquivo();
		
		for(String item:dados) {
			if(Integer.valueOf(item.split("|")[0]) == id) {
				Contato registro = new Contato();
				registro.setId(id);
				registro.setNome(item.split("|")[1]);
				registro.setTelefone(item.split("|")[2]);
				registro.setEmail(item.split("|")[3]);
				
				return registro;
			}
		}
		return null;
	}

	@Override
	public boolean update(Contato registro)  throws Exception{
		List<String> dados = abrirArquivo();
		int posicao;
		for(posicao = 0; posicao < dados.size(); posicao++) {
			if(Integer.valueOf(dados.get(posicao).substring(0,8)) == registro.getId()) {
				
				String registro_formatado = String.format("%08d|%-60s|%-10s|%-40s"
						,registro.getId()
						,registro.getNome()
						,registro.getTelefone()
						,registro.getEmail());
				
				dados.set(posicao, registro_formatado);
				salvarArquivo(dados);
				
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean delete(int id)  throws Exception{
		
		List<String> dados = abrirArquivo();
		int posicao;
		for(posicao = 0; posicao < dados.size(); posicao++) {
			if(Integer.valueOf(dados.get(posicao).substring(0,8)) == id) {
								
				dados.remove(posicao);
				salvarArquivo(dados);

				return true;
			}
		}
		return true;
	}

	@Override
	public List<Contato> recoverAll()  throws Exception{
		List<String> dados = abrirArquivo();
		List<Contato> retorno = new ArrayList<Contato>();
		
		for(String item:dados) {
			
			Contato registro = new Contato();
			registro.setId(Integer.valueOf(item.substring(0,8)));
			registro.setNome(item.substring(9,69));
			registro.setTelefone(item.substring(70,80));
			registro.setEmail(item.substring(81,121));
			
			retorno.add(registro);
		}
		return retorno;
	}

}
