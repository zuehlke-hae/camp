package ch.zuehlke.camp.war.cdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/GreetingServlet")
public class GreetingServlet extends HttpServlet {

	private static final long serialVersionUID = -5457401445337149517L;
	
	@Inject
	private SimpleGreeting greetingService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String greeting = greetingService.greet(name);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Greeting-Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Greeting-Servlet</p>");
        out.println("<p>" + greeting + "</p>");
        out.println("</body>");
	}

}
