package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

public class CourseService implements CourseI  {

	@Override
	public void createCourse(Course course) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			session.persist(course);
			tx.commit();
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public Course getCourseById(int courseId) {
		Course courseByEmail = new Course();
		//String emailRetreived;
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			courseByEmail =session.createNamedQuery("getCourseById", Course.class).setParameter("id", courseId).getSingleResult();
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		return courseByEmail;
	
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		
		List<Course> courseList = new ArrayList<Course>();
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			courseList = session.createNamedQuery("getAllCourse", Course.class).getResultList();
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		return courseList;
	}

}
