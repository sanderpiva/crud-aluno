package model.dao;

import java.util.List;

import model.ModelException;
import model.ServiceProvider;

public interface ServiceProviderDAO {
	
	boolean save(ServiceProvider serv) throws ModelException ;
	boolean update(ServiceProvider serv) throws ModelException;
	boolean delete(ServiceProvider serv) throws ModelException;
	List<ServiceProvider> listAll() throws ModelException;
	ServiceProvider findById(int id) throws ModelException;
}
