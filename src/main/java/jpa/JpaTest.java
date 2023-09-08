package jpa;


import domain.Appointment;
import domain.Patient;
import domain.Professional;
import domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Date;
import java.util.List;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		JpaTest test = new JpaTest(manager);
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}


	private void createUsers() {
		Patient p1 = new Patient("toto@gmail.com", "toto", "toto", "leZero");
		Patient p2 = new Patient("tata@gmail.com", "tata", "tata", "tati");
		Professional pro1 = new Professional("aymerick@gmail.com", "123", "Aymerick", "Leborgne", "Médecin généraliste");
		Appointment app1 = new Appointment(new Date(), 3600, "Problème de ventre", p1,  pro1);
		Appointment app2 = new Appointment(new Date(1694239619), 3600, "Certificat sportif", p1,  pro1);
		Appointment app3 = new Appointment(new Date(1694326019), 3600, "Maladie", p2,  pro1);
		int numOfUsers = manager.createQuery("Select a From User a", User.class).getResultList().size();
		int numOfAppointment = manager.createQuery("Select a From Appointment a", Appointment.class).getResultList().size();

		if (numOfUsers == 0 && numOfAppointment == 0) {

			manager.persist(p1);
			manager.persist(p2);
			manager.persist(pro1);

			manager.persist(app1);
			manager.persist(app2);
			manager.persist(app3);
		}

	}






}
