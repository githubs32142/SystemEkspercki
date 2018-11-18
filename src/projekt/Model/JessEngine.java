/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Model;

import java.io.StringWriter;
import jess.JessException;
import jess.Rete;

/**
 *
 * @author Admin
 */
public class JessEngine {
        public static Rete engine;

    public static String queryInferenceEngine(String s) throws JessException {
        // Create a Jess rule engine
        engine = new Rete();
        engine.reset();
        StringWriter o = new StringWriter();
        engine.addOutputRouter("t", o);
        String result = "";
        // Load the pricing rules
        engine.batch("projekt/JESS/diagnose.clp");
        engine.eval(s);
        engine.run();

        result = o.toString();
        engine.clear();
        if (result == null ? "" == null : result.equals("")) {
            result = "No cancer was diagnosed. Please try again";
        }
        return result;
    }
}
