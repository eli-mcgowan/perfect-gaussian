package math.gaussianinteger.hibernate;

import math.gaussianinteger.GaussianInteger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

public class GaussianIntegerDivisorsSum_HibernateDAO {
	
	private static SessionFactory factory;
	
	public GaussianIntegerDivisorsSum_HibernateDAO() {
		try{
	         factory = new AnnotationConfiguration().
	                   configure().
	                   addAnnotatedClass(GaussianIntegerDivisorsSum.class).
	                   buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}



	/* Method to LOAD a new GaussianIntegerDivisorsSum in the database */
	public GaussianIntegerDivisorsSum load(GaussianInteger gaussianInteger) {
		GaussianIntegerDivisorsSum sum = null;
		Session session = factory.openSession();
		try {
			sum = (GaussianIntegerDivisorsSum) session.createCriteria(GaussianIntegerDivisorsSum.class)
						.add(Restrictions.eq("a", gaussianInteger.getA()))
						.add(Restrictions.eq("b", gaussianInteger.getB()))
						.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sum;
	}
	
	/* Method to SAVE a new GaussianIntegerDivisorsSum in the database */
	public Long save(GaussianIntegerDivisorsSum gaussianIntegerDivisorsSum) {
		Session session = factory.openSession();
		Transaction tx = null;
		Long gaussianId = null;
		try {
			tx = session.beginTransaction();
			gaussianId = (Long) session.save(gaussianIntegerDivisorsSum);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return gaussianId;
	}
}
