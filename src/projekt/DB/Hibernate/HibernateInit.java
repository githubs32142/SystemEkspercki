package projekt.DB.Hibernate;


import javafx.concurrent.Task;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateInit {
  @Getter
  private static SessionFactory sesionfactory;

  public static  void initSesionFactory(){
    Task<Void> hibernateTask= new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        initSession();
        return null;
      }
    };
    new Thread(hibernateTask).start();
  }
  private static synchronized void initSession(){
    Configuration cfg = new Configuration().configure("resources/hibernate.cfg.xml");
    sesionfactory = cfg.buildSessionFactory();
  }

  public static void closeSesion(){
    sesionfactory.close();
  }

}
