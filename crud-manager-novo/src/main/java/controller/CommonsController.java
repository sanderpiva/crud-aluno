package controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import model.ModelException;
import model.Supliers;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.SupliersDAO;

public class CommonsController {
	
	public static void listUsers(HttpServletRequest req) {
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		List<User> listUsers = null;
		try {
			listUsers = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (listUsers != null)
			req.setAttribute("users", listUsers);		
	}
	
	public static void listSupliers(HttpServletRequest req) {
		SupliersDAO dao = DAOFactory.createDAO(SupliersDAO.class);
		
		List<Supliers> listSupliers = null;
		try {
			listSupliers = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (listSupliers != null)
			req.setAttribute("supliers", listSupliers);		
	}
}
