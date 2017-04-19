import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cruddemo.enity.Connect;

public class test {

	@SuppressWarnings("unused")
	@Autowired
	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		test1();
	}
	public static void test1() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(new Connect(111, "SHA", 222));
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
	}
}
