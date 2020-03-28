package persistencia.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IPersistencia<T> {

	/**
	 * Incluir novo registro
	 * @param entidade
	 * @return
	 */
	public boolean create(T registro) throws Exception;
	
	/**
	 * Recuperar um registro atraves de seu ID
	 * @param entidade
	 * @return
	 */
	public T recover(int id) throws Exception;
	
	/**
	 * Alterar um registro
	 * @param entidade
	 * @return
	 */
	public boolean update(T registro) throws Exception;
	
	/**
	 * Excluir um registro atraves de seu ID
	 * @param id
	 * @return
	 */
	public boolean delete(int id) throws Exception;
	
	/**
	 * Retornar todos os registros pesistidos
	 * @return
	 */
	public List<T> recoverAll()throws Exception;
	
}
