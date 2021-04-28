package main.java.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.java.filesMapping.Departamento;
import main.java.daoTablas.DepartamentoDAO;
import main.java.filesMapping.Empleado;
import main.java.daoTablas.EmpleadoDAO;

/**
 * Servlet implementation class MostrarEmpleados
 */
@WebServlet("/MostrarEmpleados")
public class MostrarEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(MostrarDepartamentos.class);
	
	static SessionFactory sessionFactory;
	static Session session;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarEmpleados() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("Inicio del servidor");
		
		session = HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.debug("PROCEDE A OBTENER LOS DEPARTAMENTOS");
		PrintWriter out = response.getWriter();
		List<Empleado> listaEmpleado = EmpleadoDAO.getAllEmpleados(session);
		logger.debug("ESCRIBE EL LISTADO DE DEPARTAMENTOS");
		printResponse(out, listaEmpleado);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private PrintWriter printResponse(PrintWriter out, List<Empleado> listaEmpleado) {
		
		PrintWriter res = out;		
		res.println("<html>");
		res.println("<title>Ejercicio Serverlet</title>");
		res.println("<body>");
		res.println("<h1>TABLA EMPLEADOS</h1>");		
		res.println("<table border=\"2\">");
		res.println("<tr>");
		res.println("<td> CODIGO </td>");
		res.println("<td> NOMBRE </td>");
		res.println("<td> CODIGO RESPONSABLE </td>");
		res.println("<td> PRIMER APELLIDO </td>");
		res.println("<td> SEGUNDO APELLIDO </td>");
		res.println("<td> LUGAR NACIMIENTO</td>");
		res.println("<td> FECHA NACIMIENTO </td>");
		res.println("<td> TELEFONO </td>");
		res.println("<td> PUESTO </td>");
		res.println("<td> COD DEPARTAMENTO</td>");
		res.println("</tr>");
		
		//RECORRIDO DE LOS DEPARTAMENTOS PARA PINTARLOS EN LA TABLA
		for(int i = 0; i < listaEmpleado.size(); i++) {
			
			Empleado emp = listaEmpleado.get(i);
			res.println("<tr>");
			res.println("<td>" + emp.getCodigo() + "</td>");
			res.println("<td>" + emp.getNombre() + "</td>");
			res.println("<td>" + emp.getApellido1()+"</td> ");
			res.println("<td>" + emp.getApellido2()+"</td> ");
			res.println("<td>" + emp.getLugarNacimiento()+"</td> ");
			res.println("<td>" + emp.getFechaNacimiento()+"</td> ");
			res.println("<td>" + emp.getTelefono()+"</td> ");
			res.println("<td>" + emp.getPuesto()+"</td> ");
			res.println("<td>" + emp.getCodDepartamento()+"</td> ");
			res.println("</tr>");
		}
		
		res.println("</table>");
		res.println("</body>");
		res.println("</html>");			
		return res;
	}

}
