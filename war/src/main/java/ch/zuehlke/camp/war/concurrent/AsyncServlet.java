package ch.zuehlke.camp.war.concurrent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.zuehlke.camp.war.cdi.PerformanceLogging;

@WebServlet(urlPatterns = "/AsyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

	private static final long serialVersionUID = 2788685284959380787L;

//    @Resource
	@Resource(lookup="java:comp/DefaultManagedExecutorService")
	ManagedExecutorService executor;

    @PerformanceLogging
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Async-Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Async-Servlet 1</p>");
//        out.println("</body>");
        AsyncContext ac = request.startAsync();
        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("onComplete");
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                System.out.println("onTimeout");
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                System.out.println("onError");
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
                System.out.println("onStartAsync");
            }
        });
        executor.submit(new Service(ac));
        out.println("<p>See log for details</p>");
        out.println("</body>");
    }

    @PerformanceLogging
    protected void longRunningMethod(AsyncContext ac) {
        System.out.println("Service started");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
            System.out.println("Service interrupted");
		}
        System.out.println("Service completed");
        ac.complete();
    }

    class Service implements Runnable {

        AsyncContext ac;

        public Service(AsyncContext ac) {
            this.ac = ac;
        }

        @Override
        public void run() {
        	longRunningMethod(ac);
        }
    }

}
