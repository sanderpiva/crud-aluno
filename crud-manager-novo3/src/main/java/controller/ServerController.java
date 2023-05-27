package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.Server;
import model.User;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;
import model.dao.ServerDAO;


//chave estrangeira no server vinculando a company(ies)

@WebServlet(urlPatterns = {"/servers", "/server/form", "/server/insert", "/server/update", "/server/delete"})
public class ServerController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getRequestURI();
		

		switch (action) {
		case "/crud-manager/server/form": {
			//PrintWriter p = resp.getWriter();
			//p.print("mapa ok");

			listCompanies(req);
			//
			req.setAttribute("action", "insert");
			//
			ControllerUtil.forward(req, resp, "/form-server.jsp");


			break;
		}
		case "/crud-manager/server/update": {
			
			//System.out.println("update chamada");
			/*listCompanies(req);
			Server server = loadServer(req);
			req.setAttribute("server", server);
			req.setAttribute("action", "update");
			ControllerUtil.forward(req, resp, "/form-server.jsp");
			*/
			
			String idStr = req.getParameter("serverId");
			int idServer = Integer.parseInt(idStr);
			
			ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);
			Server server = null;
			
			try {
				server = dao.findById(idServer);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CommonsController.listUsers(req);
			req.setAttribute("action", "update");
			req.setAttribute("server", server);
			
			ControllerUtil.forward(req, resp, "/form-server.jsp");		
					
	//
			break;
		}
		default:
			listServer(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/servers.jsp");
		}
	}

	private void listServer(HttpServletRequest req) {
		// TODO Auto-generated method stub
		ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);

		List<Server> server = null;
		try {
			server = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (server != null)
			req.setAttribute("server", server);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getRequestURI();


		switch (action) {
		case "/crud-manager/server/delete":
			deleteServer(req, resp);
			break;
		case "/crud-manager/server/insert": {
			insertServer(req, resp);
			break;
		}
		case "/crud-manager/server/update": {
			//PrintWriter p = resp.getWriter();
			//p.print("mapa ok");			
			updateServer(req, resp);
			break;
		}
		default:
			System.out.println("URL inválida " + action);
		}
		
		ControllerUtil.redirect(resp, req.getContextPath() + "/servers");
	}
	

	private Server loadServer(HttpServletRequest req) {
		String serverIdParameter = req.getParameter("serverId");
		
		int serverId = Integer.parseInt(serverIdParameter);
		
		ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);
		
		try {
			Server server = dao.findById(serverId);
			
			if (server == null)
				throw new ModelException("Servidor não encontrado para alteração");
			
			return server;
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
		
		return null;
	}
	
	
	private void listCompanies(HttpServletRequest req) {
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);

		List<Company> companies = null;
		try {
			companies = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (companies != null)
			req.setAttribute("companies", companies);
	}


	private void insertServer(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//pega dados do form
		String serverName = req.getParameter("name");
		String serverAddress = req.getParameter("address");
		String serverTelephone = req.getParameter("phone");
		String serverEmail = req.getParameter("email");
		String companyIdstr = req.getParameter("company");
		Integer companyId = Integer.parseInt(companyIdstr);
		
		Server server = new Server();
		server.setName(serverName);
		server.setAddress(serverAddress);
		server.setTelephone(serverTelephone);
		server.setEmail(serverEmail);
		server.setCompany(new Company(companyId));
		
		
		ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);
		
		try {
			if (dao.save(server)) {
				ControllerUtil.sucessMessage(req, "Servidor '" + server.getName() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Servidor '" + server.getName() + "' não pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	
	}
	
	
	private void updateServer(HttpServletRequest req, HttpServletResponse resp) {
		String serverIdStr = req.getParameter("serverId");
		String serverName = req.getParameter("name");
		String serverAddress = req.getParameter("address");
		String serverTelephone = req.getParameter("phone");
		String serverEmail = req.getParameter("email");
		Integer companyId = Integer.parseInt(req.getParameter("company"));

		Server server = new Server(Integer.parseInt(serverIdStr));
		server.setName(serverName);
		server.setAddress(serverAddress);
		server.setTelephone(serverTelephone);
		server.setEmail(serverEmail);
		server.setCompany(new Company(companyId));
		
		
		ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);
		
		try {
			if (dao.update(server)) {
				ControllerUtil.sucessMessage(req, "Servidor '" + server.getName() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Servidor '" + server.getName() + "' não pode ser atualizado.");
			}				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}

	}
	
	private void deleteServer(HttpServletRequest req, HttpServletResponse resp) {
		String serverIdParameter = req.getParameter("id");
		
		int serverId = Integer.parseInt(serverIdParameter);
		
		ServerDAO dao = DAOFactory.createDAO(ServerDAO.class);
		
		try {
			Server server = dao.findById(serverId);
			
			if (server == null)
				throw new ModelException("Servidor não encontrado para deleção.");
			
			if (dao.delete(server)) {
				ControllerUtil.sucessMessage(req, "Servidor '" + server.getName() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Servidor '" + server.getName() + "' não pode ser deletado. Há dados relacionados ao servidor.");
			}
		} catch (ModelException e) {
			// log no servidor
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				ControllerUtil.errorMessage(req, e.getMessage());
			}
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}

}
