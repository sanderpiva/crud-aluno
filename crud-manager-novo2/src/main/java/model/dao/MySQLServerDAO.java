package model.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.ModelException;
import model.Server;


public class MySQLServerDAO implements ServerDAO {

	@Override
	public boolean save(Server server) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO servers VALUES "
				+ " (DEFAULT, ?, ?, ?, ?);";

		db.prepareStatement(sqlInsert);
		db.setString(1, server.getName());
		db.setString(2, server.getTelephone());
		db.setString(3, server.getEmail());
		db.setInt(4, server.getCompany().getId());


		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Server server) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE servers "
				+ "SET nome = ?, "
				+ "telefone = ?, "
				+ "email = ? "
				+ "company_id = ? "
				+ "WHERE id = ?";


		db.prepareStatement(sqlUpdate);

		db.setString(1, server.getName());
		db.setString(2, server.getTelephone());
		db.setString(3, server.getEmail());
		db.setInt(4, server.getCompany().getId());
		db.setInt(5, server.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Server server) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sqlDelete = " DELETE FROM servers "
				+ " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, server.getId());

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
	public List<Server> listAll() throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		List<Server> servers = new ArrayList<Server>();

		// Declara um instrução SQL
		String sqlQuery = "SELECT * FROM servers";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			Server s = createServer(db);
			servers.add(s);
		}

		return servers;
		/*DBHandler db = new DBHandler();

		List<Server> servers = new ArrayList<Server>();

		// Declara uma instrução SQL
		String sqlQuery = " SELECT s.id AS server_id, c.*, "
				+ " c.companyId "
				+ " FROM servers s "
				+ " INNER JOIN companies c "
				+ " ON s.id = c.company_Id "
				+ " ORDER BY content";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			Server s = createServer(db);

			servers.add(s);
		}

		return servers;*/


	}

	@Override
	public Server findById(int id) throws ModelException {
		// TODO Auto-generated method stub
		DBHandler db = new DBHandler();

		String sql = "SELECT * FROM servers WHERE id = ?";

		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();

		Server s = null;
		while (db.next()) {
			s = createServer(db);
			break;
		}

		return s;

	}

	private Server createServer(DBHandler db) throws ModelException {
		Server s = new Server(db.getInt("id"));
		s.setName(db.getString("nome"));
		s.setTelephone(db.getString("telefone"));
		s.setEmail(db.getString("email"));

		CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class); 

		Company company = companyDAO.findById(db.getInt("company_id"));
		s.setCompany(company);

		return s;

		
	}
}
