package by.clevertec.ivanchenko.dao;

import by.clevertec.ivanchenko.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ItemDAO {
    Configuration configuration = new Configuration().addAnnotatedClass(Item.class);

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();


    public Item getItem(int id) {
        Item item;
        try {
            session.beginTransaction();

            item = session.get(Item.class, id);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
        return item;
    }
}
