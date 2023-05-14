package model.dao;

import java.util.List;

import model.ModelException;
import model.Supliers;

public interface SupliersDAO {
	
	boolean save(Supliers supliers) throws ModelException ;
	boolean update(Supliers supliers) throws ModelException;
	boolean delete(Supliers supliers) throws ModelException;
	List<Supliers> listAll() throws ModelException;
	Supliers findById(int id) throws ModelException;
}
