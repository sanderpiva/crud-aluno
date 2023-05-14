package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.Supliers;
import model.dao.DAOFactory;
import model.dao.SupliersDAO;

@WebServlet(urlPatterns = {"/supliers", "/supliers/form", "/supliers/delete", "/supliers/insert", "/supliers/update"})
public class SupliersController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		switch (action) {
		case "/crud-manager/supliers/form": {
			CommonsController.listSupliers(req);
			req.setAttribute("action", "insert");
			ControllerUtil.forward(req, resp, "/form-supliers.jsp");
			//PrintWriter p = resp.getWriter();
			//p.print("mapa ok");
			break;
		}
		case "/crud-manager/supliers/update": {
			//listUsers(req);
			//User user = loadUser(req);
			//req.setAttribute("user", user);
			//req.setAttribute("action", "update");
			//ControllerUtil.forward(req, resp, "/form-user.jsp");
			break;
		}
		default:
			listSupliers(req);
			
			ControllerUtil.transferSessionMessagesToRequest(req);
		
			ControllerUtil.forward(req, resp, "/supliers.jsp");
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getRequestURI();
		
		/*if (action == null || action.equals("") ) {
			ControllerUtil.forward(req, resp, "/index.jsp");
			return;
		}*/
		
		switch (action) {
		case "/crud-manager/supliers/delete":
			//deleteUser(req, resp);
			break;	
		case "/crud-manager/supliers/insert": {
			//insertUser(req, resp);
			break;
		}
		case "/crud-manager/supliers/update": {
			//updateUser(req, resp);
			break;
		}
		default:
			//System.out.println("URL inválida " + action);
			break;
		}
			
		ControllerUtil.redirect(resp, req.getContextPath() + "/supliers");
	}
	
	/*
	private User loadUser(HttpServletRequest req) {
		String userIdParameter = req.getParameter("userId");
		
		int userId = Integer.parseInt(userIdParameter);
		
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			User user = dao.findById(userId);
			
			if (user == null)
				throw new ModelException("Usuário não encontrado para alteração");
			
			return user;
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}*/
	
	private void listSupliers(HttpServletRequest req) {
		SupliersDAO dao = DAOFactory.createDAO(SupliersDAO.class);
		
		List<Supliers> supliers = null;
		try {
			supliers = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (supliers != null)
			req.setAttribute("supliers", supliers);
	}
	/*
	private void insertUser(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("name");
		String userGender = req.getParameter("gender");
		String userEMail = req.getParameter("mail");
		
		User user = new User();
		user.setName(userName);
		user.setGender(userGender);
		user.setEmail(userEMail);
		
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			if (dao.save(user)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + user.getName() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + user.getName() + "' não pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		String userName = req.getParameter("name");
		String userGender = req.getParameter("gender");
		String userEMail = req.getParameter("mail");
		
		User user = loadUser(req);
		user.setName(userName);
		user.setGender(userGender);
		user.setEmail(userEMail);
		
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			if (dao.update(user)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + user.getName() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + user.getName() + "' não pode ser atualizado.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		String userIdParameter = req.getParameter("id");
		
		int userId = Integer.parseInt(userIdParameter);
		
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			User user = dao.findById(userId);
			
			if (user == null)
				throw new ModelException("Usuário não encontrado para deleção.");
			
			if (dao.delete(user)) {
				ControllerUtil.sucessMessage(req, "Usuário '" + user.getName() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Usuário '" + user.getName() + "' não pode ser deletado. Há dados relacionados ao usuário.");
			}
		} catch (ModelException e) {
			// log no servidor
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				ControllerUtil.errorMessage(req, e.getMessage());
			}
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}*/
}
