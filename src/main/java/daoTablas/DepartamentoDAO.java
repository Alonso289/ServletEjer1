package main.java.daoTablas;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import main.java.filesMapping.Departamento;
import main.java.servlet.MostrarDepartamentos;



public class DepartamentoDAO {
	
	private static Logger logger = LogManager.getLogger(MostrarDepartamentos.class);
	
	//INSERTA DEPARTAMENTO
	public static void insertDepartamento(Session s, Departamento departamento) {
		logger.debug("INSERTANDO DEPARTAMENTOS");
		s.save(departamento);		
	}
	//ACTUALIZA DEPARTAMENTO
	public static void updateDepartamento(Session s, Departamento departamentoActualizado) {				
		logger.debug("ACTUALIZANDO DEPARTAMENTOS");
		Departamento departamento = s.get(Departamento.class, departamentoActualizado.getCodigo());
		departamento.setCodResponsable(departamentoActualizado.getCodResponsable());
		departamento.setNombre(departamentoActualizado.getNombre());
		if (departamento != null) 
			departamento = departamentoActualizado;
						
	}
	//ELIMINA DEPARTAMENTO
	public static void deleteDepartamento(Session s, int codigo) {	
		logger.debug("ELIMINANDO DEPARTAMENTOS");	
		Departamento departamento = s.get(Departamento.class, codigo);		
		s.delete(departamento);
	}
	//OBTIENE LISTA DEPARTAMENTOS
	public static List<Departamento> getAllDepartamento(Session s){
		logger.debug("OBTIENE LISTADO DEPARTAMENTOS");
		String hQuery = "from Departamento";
		List<Departamento> departamentoList = s.createQuery(hQuery, Departamento.class).list();
		
		return departamentoList;
	}
		
	// Criteria queries
	public static List<Departamento> getAllDepartamentos(Session s) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Departamento.class);
		List<Departamento> result = criteria.getExecutableCriteria(s).list();
		return result;
	}
	
	public static Departamento getDepartamento(Session s, int codigo) {
		// deprecado desde 5.2
		Criteria criteria = s.createCriteria(Departamento.class);
		Departamento result = (Departamento)criteria.add(Restrictions.eq("codigo", codigo))
											.setMaxResults(1)
											.uniqueResult();
		
		return result;
	}
}
