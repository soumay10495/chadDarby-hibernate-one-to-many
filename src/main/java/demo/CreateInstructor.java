package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructor {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a new Instructor object...");

            InstructorDetail instructorDetail = new InstructorDetail(
                    "adda24x7", "Academia");

            Instructor instructor = new Instructor
                    ("Samuel", "Jackson", "samjack@gamil.com");

            /*InstructorDetail instructorDetail = new InstructorDetail(
                    "cogito", "Knowledge");

            Instructor instructor = new Instructor
                    ("Jhakaas", "Pandit", "jhakpandit@gamil.com");*/

            //Setting InstructorDetail reference
            instructor.setInstructorDetail(instructorDetail);

            session.beginTransaction();
            System.out.println("Saving the instructor..." + instructor);

            //This will save InstructorDetail also due to CascadeType.ALL
            session.save(instructor);

            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
