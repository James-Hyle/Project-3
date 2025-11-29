import model.TM;
import model.TMState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TMSimulator {
    public static void main(String[] args) {
        String filePath = args[0];
        String line;
        String comma = ",";
        ArrayList<String> transitions = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int numStates = Integer.parseInt(br.readLine());
            int alphabetSize = Integer.parseInt(br.readLine());
            TM tm = new TM(numStates, alphabetSize);
            for(int i = 1; i <= alphabetSize; i++) {
                tm.addSigma((char) i);
            }
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                transitions.add(line);
            }

            for(int numState = 0; numState < numStates; numState++) {
                TMState s = new TMState(String.valueOf(numState));
                tm.addState(s);
                for(int i = 1; i <= alphabetSize; i++) {
                    String[] transitionInfo = transitions.get(i - 1).split(comma);
                    tm.addTransition(s.getName(), (char) i, transitionInfo[0], transitionInfo[1], transitionInfo[2]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
