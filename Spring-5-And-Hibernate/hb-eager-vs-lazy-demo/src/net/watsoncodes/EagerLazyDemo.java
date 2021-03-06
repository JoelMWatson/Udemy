package net.watsoncodes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import net.watsoncodes.entity.Course;
import net.watsoncodes.entity.Instructor;
import net.watsoncodes.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			System.out.println("Starting Transaction");
			session.beginTransaction();

			Instructor instructor = session.get(Instructor.class, 1);

			System.out.println("Instructor: " + instructor);
			System.out.println("Courses: " + instructor.getCourses());

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Transaction Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
