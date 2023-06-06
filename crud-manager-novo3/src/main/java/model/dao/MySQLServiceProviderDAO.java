package model.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.ServiceProvider;


public class MySQLServiceProviderDAO implements ServiceProviderDAO {

	@Override
	public boolean save(ServiceProvider serv) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO serviceproviders VALUES "
				+ " (DEFAULT, ?, ?, ?, ?, ?);";

		db.prepareStatement(sqlInsert);
		db.setString(1, serv.getName());
		db.setString(2, serv.getAddress());
		db.setString(3, serv.getTelephone());
		db.setString(4, serv.getEmail());
		db.setInt(5, serv.getCompany().getId());


		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(ServiceProvider serv) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE serviceproviders "
				+ "SET nome = ?, "
				+ "endereco = ?, "
				+ "telefone = ?, "
				+ "email = ?, "
				+ "company_id = ? "
				+ "WHERE id = ?";


		db.prepareStatement(sqlUpdate);

		db.setString(1, serv.getName());
		db.setString(2, serv.getAddress());
		db.setString(3, serv.getTelephone());
		db.setString(4, serv.getEmail());
		db.setInt(5, serv.getCompany().getId());
		db.setInt(6, serv.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(ServiceProvider serv) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlDelete = " DELETE FROM serviceproviders "
				+ " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, serv.getId());

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
	public List<ServiceProvider> listAll() throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		List<ServiceProvider> serv = new ArrayList<ServiceProvider>();

		String sqlQuery = "SELECT * FROM serviceproviders";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			ServiceProvider s = createServer(db);
			serv.add(s);
		}

		return serv;

	}

	@Override
	public ServiceProvider findById(int id) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sql = "SELECT * FROM serviceproviders WHERE id = ?";

		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();

		ServiceProvider s = null;
		while (db.next()) {
			s = createServer(db);
			break;
		}

		return s;

	}

	private ServiceProvider createServer(DBHandler db) throws ModelException {
		ServiceProvider s = new ServiceProvider(db.getInt("id"));
		s.setName(db.getString("nome"));
		s.setAddress(db.getString("endereco"));
		s.setTelephone(db.getString("telefone"));
		s.setEmail(db.getString("email"));

		CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class); 

		Company company = companyDAO.findById(db.getInt("company_id"));
		s.setCompany(company);

		return s;		
	}
}
