package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HQLJoinFetch {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Getting Instructor object...");
            session.beginTransaction();

            /*
            Using HQL to bypass lazy loading in Instructor class and fetching
            course list also at once. Since the courses are loaded into the Instructor
            object, they can be fetched even after closing the session.
            */

            Query<Instructor> query = session.createQuery(
                    "Select i from Instructor i " +
                            "JOIN FETCH i.courseList " +
                            "where i.id=:theInstructorId", Instructor.class);

            query.setParameter("theInstructorId", 1);
            Instructor instructor = query.getSingleResult();
            System.out.println("Instructor : " + instructor);

            session.getTransaction().commit();

            session.close();

            System.out.println("After closing session...\nCourses : " + instructor.getCourseList());

            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
