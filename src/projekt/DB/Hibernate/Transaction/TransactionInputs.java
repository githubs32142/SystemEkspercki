package projekt.DB.Hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import projekt.DB.Hibernate.HibernateInit;
import projekt.DB.Model.Input;
import projekt.DB.Model.User;

import java.util.List;

import static projekt.Activity.AlertActivity.showOutputMessageError;
 class TransactionInputs {

  public  static void transactionInputs(List<Input> list) {
    if (listIsNotEmpty(list)) {
      long idUser = list.get(0).getUser().getId();
      if (sesionInNotNull()) {
        Session session = HibernateInit.getSesionfactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = getUser(idUser, session);
        if (user.getId() == idUser) {
          list.stream()
                  .forEach((t) -> {
                    session.save(t);
                  });
        }
        try {
          transaction.commit();
          session.close();
        }
        catch (Exception e){
          transaction.rollback();
          session.close();
          showOutputMessageError("Problem z zapisem do bazą danych");
        }



      } else {
        showOutputMessageError("Problem z połączeniem do bazą danych");
        System.exit(1);
      }
    }
  }

  private static User getUser(long idUser, Session session) {
    Query query =session.createQuery("from User where id=:id");
    query.setParameter("id",idUser);
    List list1 =  query.list();
    return (User) list1.get(0);
  }

  private static boolean sesionInNotNull() {
    return HibernateInit.getSesionfactory()!=null;
  }

  private static boolean listIsNotEmpty(List<Input> list) {
    return !list.isEmpty();
  }

}
