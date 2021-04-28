package main.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.filesMapping.Departamento;
import main.java.daoTablas.DepartamentoDAO;
import main.java.filesMapping.Empleado;
import main.java.daoTablas.EmpleadoDAO;

/**
 * Servlet implementation class MostrarDepartamentos
 */
@WebServlet("/MostrarDepartamentos")
public class MostrarDepartamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//private static Logger logger = LogManager.getLogger(MostrarDepartamentos.class);
	
	static SessionFactory sessionFactory;
	static Session session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDepartamentos() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		//logger.info("%1$s: >>>>>> Main execution started.");
		
		session = HibernateUtil.getSessionFactory().openSession();
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//logger.debug("PROCEDE A OBTENER LOS DEPARTAMENTOS");
		PrintWriter out = response.getWriter();
		List<Departamento> listaDepartamento = DepartamentoDAO.getAllDepartamento(session);
		//logger.debug("ESCRIBE EL LISTADO DE DEPARTAMENTOS");
		printResponse(out, listaDepartamento);
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private PrintWriter printResponse(PrintWriter out, List<Departamento> listaDepartamento) {
			
			PrintWriter res = out;
			
			res.println("<html>");
			res.println("<title>Ejercicio Servlet</title>");
			res.println("<body>");
			res.println("<h1>TABLA DEPARTAMENTOS</h1>");		
			res.println("CODIGO");
			res.println("NOMBRE>");
			res.println("CODIGO RESPONSABLE");
	
			for(int i = 0; i < listaDepartamento.size(); i++) {
				
				Departamento depar = listaDepartamento.get(i);
				res.println("<br>");
				res.println(depar.getCodigo()+" ");
				res.println(depar.getNombre()+" ");
				res.println(depar.getCodResponsable()+" ");
				res.println("<br>");
			}
			res.println("</table>");
			res.println("</body>");
			res.println("</html>");
			
			return res;
		}
	
	}
