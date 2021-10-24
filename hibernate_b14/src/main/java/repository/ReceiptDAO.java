package repository;

import entity.ReceiptEntity;
import org.hibernate.Session;
import util.CollectionUtil;
import util.HibernateUtil;

import java.util.List;

public class ReceiptDAO {
    public void addNewReceipt(List<ReceiptEntity> receiptEntities) {
        if (CollectionUtil.isEmpty(receiptEntities)) {
            return;
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            receiptEntities.forEach(session::save);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        } finally {
            assert session != null;
            session.close();
        }
    }
}
