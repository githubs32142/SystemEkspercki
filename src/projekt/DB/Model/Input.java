package projekt.DB.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Wejscie")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Input implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_wejscia")
  long id;
  @Column(name = "typ_wejscia")
  String typeInput;
  @Column(name = "Nazwa")
  String name;
  @Column(name = "wartosc")
  Double value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_uzytkownika",nullable = false)
  User user;



}
