package sba.sms.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class StudentService implements StudentI {
	//static List<Student> listStu = new ArrayList<Student>();
	@Override
	public List<Student> getAllStudents() {
		List<Student> listStu = new ArrayList<Student>();
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			listStu = session.createNamedQuery("getAll", Student.class).getResultList();
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		System.out.println(listStu);
		return listStu;
	}

	@Override
	public void createStudent(Student student) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			session.persist(student);
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
	public Student getStudentByEmail(String email) {
		Student studentByEmail = new Student();
		//String emailRetreived;
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			studentByEmail =session.createNamedQuery("getById", Student.class).setParameter("email", email).getSingleResult();
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		return studentByEmail;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		boolean validStudent = false;
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			 Student stu = session.get(Student.class, email);
			 if(stu!=null) {
			 if ((stu.getEmail().equalsIgnoreCase(email))
						&& (stu.getPassword().equalsIgnoreCase(password))) {
				 validStudent = true;
				} else {
					validStudent = false;
				}
			 }
			/*
			 * List<Student[]> result =
			 * session.createQuery("SELECT email, password FROM student s",Student[].class).
			 * getResultList(); for (Student[] o : result) { for(int i=0;i<o.length;i++) {
			 * if ((o[i].getEmail().equalsIgnoreCase(email)) &&
			 * (o[i].getPassword().equalsIgnoreCase(password))) { validStu = true; break; }
			 * else { validStu = false; } }
			 * 
			 * }
			 */
			
			
		} catch(
		HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		
		return validStudent;
	}

	@Override
	public void registerStudentToCourse(String email, int courseId) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			 tx = session.beginTransaction();
			 Student stu = session.get(Student.class, email);
			 Course course = session.get(Course.class, courseId);
			 stu.getCourses().add(course);
			 session.merge(stu);
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
	public Set<Course> getStudentCourses(String email) {
		Set<Course> course = null;
		Student studByEmail = new Student();
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			 tx = session.beginTransaction();
			 studByEmail =session.createNamedQuery("getById", Student.class).setParameter("email", email).getSingleResult();
			 course = studByEmail.getCourses();
			// System.out.println(course);
			 
		} catch(HibernateException ex){
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
//System.out.println(course);
		return course;
	}

}
