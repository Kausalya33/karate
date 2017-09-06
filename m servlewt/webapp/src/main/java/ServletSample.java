import java.io.*;
import javax.servlet.http.*;

public class ServletSample extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.getWriter().println("<html><body><h1>Hello I am Here!!</h1></body></html>");
	}
}