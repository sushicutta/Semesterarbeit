package server;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 221966519339833118L;

	@EJB
	HelloBean bean;

	public HelloServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out = response.getOutputStream();
		out.print("<html><body>");
		out.print("Request received at: " + request.getContextPath());
		out.print("<br>" + bean.sayHello("Duke"));
		out.print("</body></html>");
	}

	@Override
	public String getServletInfo() {
		return "Just a test";
	}
}
