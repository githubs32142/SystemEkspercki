package projekt.DB.Maper;

import projekt.DB.Model.Input;
import projekt.DB.Model.User;
import projekt.Model.FCList;
import projekt.Model.TList;

import java.util.List;

public class MaperInput {

  public static void mapingTListToInput(TList tList, List<Input> list, User user,String name){
    tList.getListObject()
            .parallelStream()
            .forEach((t)->{
              Input input= new Input();
              input.setName(name);
              input.setValue(t.isAdded()?1d:0d);
              input.setTypeInput(t.getFactor());
              input.setUser(user);
              list.add(input);
            });
  }
  public static void mapingTObjectToInput(FCList fcList, List<Input> list, User user, String name){
    fcList.getList()
            .parallelStream()
            .forEach((t)->{
              Input input= new Input();
              input.setName(name);
              input.setValue((double) t.getAdded());
              input.setTypeInput(t.getFamily() +" "+ t.getCancer());
              input.setUser(user);
              list.add(input);
            });
  }
}
