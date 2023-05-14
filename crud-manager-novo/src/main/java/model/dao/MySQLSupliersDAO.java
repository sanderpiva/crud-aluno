package model.dao;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.Supliers;

public class MySQLSupliersDAO implements SupliersDAO {

	@Override
	public boolean save(Supliers supliers) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO supliers VALUES "
				+ " (DEFAULT, ?, ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, supliers.getName());
		db.setString(2, supliers.getTelephone());
		db.setString(3, supliers.getEmail());
		  
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Supliers supliers) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE supliers "
				         	+ "SET nome = ?, "
				         	+ "telefone = ?, "
				         	+ "email = ? "
				         + "WHERE id = ?";
		
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, supliers.getName());
		db.setString(2, supliers.getTelephone());
		db.setString(3, supliers.getEmail());
		db.setInt(4, supliers.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Supliers supliers) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM supliers "
		                 + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, supliers.getId());
		
		try {
			
			return db.executeUpdate() > 0;
			
		} catch (ModelException e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				return false;
			}
			
			throw e; 
		}
	}

	@Override
	public List<Supliers> listAll() throws ModelException {
	
		DBHandler db = new DBHandler();
		
		List<Supliers> supliers = new ArrayList<Supliers>();
			
		// Declara um instrução SQL
		String sqlQuery = "SELECT * FROM supliers";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Supliers s = createSuplier(db);
			supliers.add(s);
		}
		
		return supliers;
	}

	@Override
	public Supliers findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
				
		String sql = "SELECT * FROM supliers WHERE id = ?";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Supliers supliers = null;
		while (db.next()) {
			supliers = createSuplier(db);
			break;
		}
		
		return supliers;
	}
	
	private Supliers createSuplier(DBHandler db) throws ModelException {
		Supliers s = new Supliers(db.getInt("id"));
		s.setName(db.getString("nome"));
		s.setTelephone(db.getString("telefone"));
		s.setEmail(db.getString("email"));
		
		return s;
	}
}
