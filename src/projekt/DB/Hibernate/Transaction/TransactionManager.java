package projekt.DB.Hibernate.Transaction;

import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import projekt.DB.Model.Input;
import projekt.DB.Model.Output;
import projekt.DB.Model.User;

import java.util.List;

import static projekt.Activity.AlertActivity.showOutputMessageError;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionManager {
  @NonNull
  private List<Input> inputList;
  @NonNull
  private List<Output> outputList;
  @NonNull
  private User user;

  public void transact(){
    transactUser();
    TransactionOutput.transactionOutputs(outputList);
    transactInputs();
  }

  private void transactOutput() {
    Task<Void> outputTask= new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        TransactionOutput.transactionOutputs(outputList);
        return null;
      }
    };
    new Thread(outputTask).start();
  }

  private void transactInputs() {
    Task<Void> inputTask= new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        TransactionInputs.transactionInputs(inputList);
        return null;
      }
    };
    new Thread(inputTask).start();
  }

  private void transactUser() {
    if(userIsNotNull()){
      TransactionUser.transactionUser(user);
    }
    else {
      showOutputMessageError("Użytkownik jest pusty");
    }
  }

  private boolean userIsNotNull() {
    return user!=null;
  }
}
