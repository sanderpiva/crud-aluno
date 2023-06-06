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
import model.ServiceProvider;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;
import model.dao.ServiceProviderDAO;

@WebServlet(urlPatterns = {"/serviceProviders", "/serviceProvider/form", "/serviceProvider/insert", "/serviceProvider/update", "/serviceProvider/delete"})
public class ServiceProviderController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getRequestURI();
		

		switch (action) {
		case "/crud-manager/serviceProvider/form": {
		
			listCompanies(req);
			//
			req.setAttribute("action", "insert");
			//
			ControllerUtil.forward(req, resp, "/form-serviceProvider.jsp");


			break;
		}
		case "/crud-manager/serviceProvider/update": {
			
			String idStr = req.getParameter("serviceProviderId");
			int idServiceProvider = Integer.parseInt(idStr);
			
			ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);
			ServiceProvider serviceProvider = null;
			
			try {
				serviceProvider = dao.findById(idServiceProvider);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			listCompanies(req);
			req.setAttribute("action", "update");
			req.setAttribute("serviceProvider", serviceProvider);
			
			ControllerUtil.forward(req, resp, "/form-serviceProvider.jsp");		
					
	//
			break;
		}
		default:
			listServers(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/serviceProviders.jsp");
		}
	}

	private void listServers(HttpServletRequest req) {
		// TODO Auto-generated method stub
		ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);

		List<ServiceProvider> serviceProvider = null;
		try {
			serviceProvider = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (serviceProvider != null)
			req.setAttribute("serviceProvider", serviceProvider);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getRequestURI();


		switch (action) {
		case "/crud-manager/serviceProvider/delete":
			deleteServiceProvider(req, resp);
			break;
		case "/crud-manager/serviceProvider/insert": {
			//System.out.println("oi");
			insertServiceProvider(req, resp);
			break;
		}
		case "/crud-manager/serviceProvider/update": {
			updateServiceProvider(req, resp);
			break;
		}
		default:
			System.out.println("URL inv√°lida " + action);
		}
		
		ControllerUtil.redirect(resp, req.getContextPath() + "/serviceProviders");
	}
	

	private ServiceProvider loadServer(HttpServletRequest req) {
		String serviceProviderIdParameter = req.getParameter("serviceProviderId");
		
		int serviceProviderId = Integer.parseInt(serviceProviderIdParameter);
		
		ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);
		
		try {
			ServiceProvider serviceProvider = dao.findById(serviceProviderId);
			
			if (serviceProvider == null)
				throw new ModelException("Prestador de servico n√£o encontrado para altera√ß√£o");
			
			return serviceProvider;
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


	private void insertServiceProvider(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//pega dados do form
		String serviceProviderName = req.getParameter("name");
		String serviceProviderAddress = req.getParameter("address");
		String serviceProviderTelephone = req.getParameter("phone");
		String serviceProviderEmail = req.getParameter("email");
		String companyIdstr = req.getParameter("company");
		Integer companyId = Integer.parseInt(companyIdstr);
		
		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setName(serviceProviderName);
		serviceProvider.setAddress(serviceProviderAddress);
		serviceProvider.setTelephone(serviceProviderTelephone);
		serviceProvider.setEmail(serviceProviderEmail);
		serviceProvider.setCompany(new Company(companyId));
		
		
		ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);
		
		try {
			if (dao.save(serviceProvider)) {
				ControllerUtil.sucessMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' n√£o pode ser salvo.");
			}
				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	
	}
	
	
	private void updateServiceProvider(HttpServletRequest req, HttpServletResponse resp) {
		String serviceIdStr = req.getParameter("serviceProviderId");
		String serviceProviderName = req.getParameter("name");
		String serviceProviderAddress = req.getParameter("address");
		String serviceProviderTelephone = req.getParameter("phone");
		String serviceProviderEmail = req.getParameter("email");
		Integer companyId = Integer.parseInt(req.getParameter("company"));
		
		ServiceProvider serviceProvider = new ServiceProvider(Integer.parseInt(serviceIdStr));
		serviceProvider.setName(serviceProviderName);
		serviceProvider.setAddress(serviceProviderAddress);
			
		serviceProvider.setTelephone(serviceProviderTelephone);
		serviceProvider.setEmail(serviceProviderEmail);
		serviceProvider.setCompany(new Company(companyId));
		
		
		ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);
		
		try {
			if (dao.update(serviceProvider)) {
				ControllerUtil.sucessMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' n√£o pode ser atualizado.");
			}				
		} catch (ModelException e) {
			// log no servidor
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}

	}
	
	private void deleteServiceProvider(HttpServletRequest req, HttpServletResponse resp) {
		String serviceIdParameter = req.getParameter("id");
		
		int serviceProviderId = Integer.parseInt(serviceIdParameter);
		
		ServiceProviderDAO dao = DAOFactory.createDAO(ServiceProviderDAO.class);
		
		try {
			ServiceProvider serviceProvider = dao.findById(serviceProviderId);
			
			if (serviceProvider == null)
				throw new ModelException("Prestador de serviÁo n√£o encontrado para dele√ß√£o.");
			
			if (dao.delete(serviceProvider)) {
				ControllerUtil.sucessMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' deletado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Prestador de serviÁo '" + serviceProvider.getName() + "' n√£o pode ser deletado. H√° dados relacionados ao servidor.");
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
