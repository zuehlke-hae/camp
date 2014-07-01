package ch.zuehlke.camp.war.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = -9089231358752688259L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Test-Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Test-Servlet</p>");
        out.println("</body>");
    }

}