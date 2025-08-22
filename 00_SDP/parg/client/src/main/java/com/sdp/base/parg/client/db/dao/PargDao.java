package com.sdp.base.parg.client.db.dao;

import com.sdp.base.parg.client.db.entity.Parg;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@SuppressWarnings("unchecked")
@Repository
public class PargDao {

    @PersistenceContext
    private EntityManager em;

    public List<Parg> getTable() {
        List<Parg> data = new ArrayList<Parg>();
        try {
            Query<Parg> qry = (Query<Parg>) em.createQuery("FROM Parg");
            data = qry.getResultList();
        } catch (Exception ex) {
            System.out.println("error");
        }
        return data;
    }
    void metodoCursor() {
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        try {
//            Query<Parg> query = session.createQuery("FROM Parg", Parg.class);
//            query.setFetchSize(50); // opcional, tamaño del lote que pide a la BD
//
//            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
//
//            int count = 0;
//            while (results.next()) {
//                Parg entity = (Parg) results.get(0);
//
//                // Procesas la entidad
////            System.out.println("Procesando id=" + entity.getId());
//
//                // Para evitar acumular todo en el 1er nivel de caché de Hibernate
//                if (++count % 50 == 0) {
//                    session.flush();
//                    session.clear();
//                }
//            }
//
//            tx.commit();
//        } catch (Exception ex) {
//
//        } finally {
//            session.close();
//        }
    }

        public void sincursor(String[] args) {
//            EntityManagerFactory emf =
//                    Persistence.createEntityManagerFactory("miPU"); // definido en persistence.xml o properties
//            EntityManager em = emf.createEntityManager();
//
//            try {
//                em.getTransaction().begin();
//
//                // Cargar un único registro
//                Cliente c = em.find(Cliente.class, 1);
//                System.out.println("Cliente: " + c.getNombre());
//
//                // O recorrer todos como si fuera un cursor
//                List<Cliente> clientes =
//                        em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
//
//                for (Cliente cli : clientes) {
//                    System.out.println("ID: " + cli.getId() + ", Nombre: " + cli.getNombre());
//                }
//
//                em.getTransaction().commit();
//            } finally {
//                em.close();
//                emf.close();
//            }
//        }
    }
}
