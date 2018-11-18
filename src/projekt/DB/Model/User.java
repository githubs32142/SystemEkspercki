package projekt.DB.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Uzytkownik")
@Data
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_uzytkownika")
  long id;
  @Column(name = "wiek")
  int age;
  @Column(name = "waga")
  double weight;
  @Column(name = "plec")
  String sex;

  @OneToMany(mappedBy = "user")
  List<Input> inputs= new ArrayList<>();

  @OneToMany(mappedBy = "user")
  List<Output> outputs= new ArrayList<>();


}
