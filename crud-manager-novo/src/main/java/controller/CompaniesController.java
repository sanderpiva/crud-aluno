package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.User;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;

@WebServlet(urlPatterns = {"/companies","/company/form", "/company/insert"})
public class CompaniesController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getRequestURI();
		//retorna: "crud-manager/company/form"

		switch (action) {
		case "/crud-manager/company/form": {
			//PrintWriter p = resp.getWriter();
			//p.print("mapa ok");

			CommonsController.listUsers(req);
			//
			req.setAttribute("action", "insert");
			//
			ControllerUtil.forward(req, resp, "/form-company.jsp");


			break;
		}
		case "/crud-manager/company/update": {
			break;
		}
		default:
			listCompany(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/companies.jsp");

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getRequestURI();


		switch (action) {
		case "/crud-manager/company/delete":

			break;
		case "/crud-manager/company/insert": {
			insertCompany(req, resp);	
			break;
		}
		case "/crud-manager/company/update": {

			break;
		}
		default:
			System.out.println("URL inválida " + action);
		}
	}
	
	private void insertCompany(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//pega dados do form
		String companyName = req.getParameter("name");
		String role = req.getParameter("role");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		//user: nome do select
		Integer userId = Integer.parseInt(req.getParameter("user"));
		
		Company comp = new Company();
		comp.setName(companyName);
		//comp.setRole("nos");
		comp.setRole(role);
		comp.setStart(Calendar.getInstance().getTime());
		comp.setEnd(Calendar.getInstance().getTime());
		comp.setUser(new User(userId));
		//persistencia
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		try {
			if (dao.save(comp)) {
				ControllerUtil.sucessMessage(req, "Empresa '" + comp.getName() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Empresa '" + comp.getName() + "' não pode ser salvo.");
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
	
	
	private void listCompany(HttpServletRequest req) {
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);

		List<Company> company = null;
		try {
			company = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (company != null)
			req.setAttribute("company", company);
	}
}
