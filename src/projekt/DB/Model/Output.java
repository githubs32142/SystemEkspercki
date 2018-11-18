package projekt.DB.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;

@Entity
@Table(name = "wyjscie")
@Data
@NoArgsConstructor
public class Output {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_wyjscia")
  long id;

  @Column(name = "Wynik")
  String result;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_uzytkownika",nullable = false)
  User user;
}
