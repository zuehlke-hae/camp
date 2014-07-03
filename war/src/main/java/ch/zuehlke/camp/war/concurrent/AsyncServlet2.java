package ch.zuehlke.camp.war.concurrent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AsyncServlet2", asyncSupported = true)
public class AsyncServlet2 extends HttpServlet {

	private static final long serialVersionUID = 2788685284959380787L;

	@Resource(lookup="java:comp/DefaultManagedExecutorService")
	ManagedExecutorService executor;

	@Inject Event<MathPowEvent> observableManager;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Async-Servlet 2</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Async-Servlet 2</p>");
        out.println("<p>See log for details</p>");
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
        System.out.println("start MathPow " + System.currentTimeMillis());
        MathPow calculator = new MathPow(2, 10, observableManager);
        Future<Integer> future = executor.submit(calculator);
        System.out.println("end MathPow " + System.currentTimeMillis());
        try {
			out.println("<p>Result: " + future.get() + "</p>");
	        System.out.println("printed result " + System.currentTimeMillis());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ac.complete();
			System.out.println("ac.complete " + System.currentTimeMillis());
		}
        out.println("</body>");
    }


}
