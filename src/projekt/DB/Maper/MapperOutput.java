package projekt.DB.Maper;

import projekt.DB.Model.Output;
import projekt.DB.Model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class MapperOutput {
  public static void mappingOutput(String result, List<Output> outputs, User user){
    Set<String> results=extracResult(result);
    results.parallelStream()
            .forEach((t)->{
              Output out= new Output();
              System.out.println(t);
              out.setResult(t);
              out.setUser(user);
              outputs.add(out);
            });

  }
  private static Set<String> extracResult(String result){
    Set<String> results= new HashSet<>();
    if(!result.equals("No cancer was diagnosed. Please try again")){
      StringTokenizer stringTokenizer = new StringTokenizer(result,"\n");
      while (stringTokenizer.hasMoreElements()){
        results.add(stringTokenizer.nextToken());
      }
    }
    else{
      results.add(result);
    }
    return results;
  }
}
