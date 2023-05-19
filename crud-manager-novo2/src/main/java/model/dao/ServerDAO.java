package model.dao;

import java.util.List;

import model.ModelException;
import model.Server;

public interface ServerDAO {
	
	boolean save(Server server) throws ModelException ;
	boolean update(Server server) throws ModelException;
	boolean delete(Server server) throws ModelException;
	List<Server> listAll() throws ModelException;
	Server findById(int id) throws ModelException;
}
