

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	


		String action = request.getParameter("buttonLogin");
		System.out.println(action);
		
	
			MeetingService mserv= new MeetingServiceImpl();
			
			String time = new Date().toString();
			int id = Integer.parseInt(request.getParameter("userid"));
			String email = request.getParameter("email");

			try {
				// calling login function
				Users u = mserv.login(id, email);
//				request.getSession(true).setAttribute("userOb", u);
//
//				// udating lastlogintime in users table
//				loginService.updateLoginTime(u.getUserId(), time);

				String role = u.getRole().toLowerCase();

				System.out.println(role);

				switch (role) {

				case "admin":

					request.getRequestDispatcher("adminLogin.jsp?page=1").forward(request, response);
					break;

				case "manager":
					request.getRequestDispatcher("managerLogin.jsp").forward(request, response);
					break;

				case "member":
					request.getRequestDispatcher("memberLogin.jsp").forward(request, response);
					break;

				default:
					break;
				}

			} catch (RuntimeException re) {
				
				// error person not found
		
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Login Failed. Please enter valid credentials.')");
				out.println("location='index.jsp';");
				out.println("</script>");
				
			}


	}

}
