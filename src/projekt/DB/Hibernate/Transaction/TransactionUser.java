package projekt.DB.Hibernate.Transaction;

import javafx.scene.control.Alert;
import org.hibernate.Session;
import projekt.DB.Hibernate.HibernateInit;
import projekt.DB.Model.User;


class TransactionUser {

  public static void transactionUser(User user){
    if(HibernateInit.getSesionfactory()!=null){
      Session session =  HibernateInit.getSesionfactory().openSession();
      org.hibernate.Transaction transaction=session.beginTransaction();
      session.save(user);
      try {
        transaction.commit();
      }
      catch (Exception e){
        transaction.rollback();
        session.close();
        showOutputMessage("Problem z zapisem do bazą danych");
      }
    }
    else {
      showOutputMessage("Problem z połączeniem do bazą danych");
      System.exit(1);
    }
  }



  private static void showOutputMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Powiadomienie");
    alert.setHeaderText("Treść komunikatu:");
    alert.setContentText(message);
    alert.showAndWait();
  }

}
