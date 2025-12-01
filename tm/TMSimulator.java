package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TMSimulator {

    /**
     * Main for Turing Machine simulation
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String filePath = args[0];
        String line;
        String comma = ",";
        String input = ""; //initialize input string to empty
        ArrayList<String> transitions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            int numStates = Integer.parseInt(br.readLine());
            int alphabetSize = Integer.parseInt(br.readLine());
            TM tm = new TM(numStates, alphabetSize);
            int gammaSize = alphabetSize + 1;

            for (int i = 0; i <= alphabetSize; i++) {
                tm.addSigma("" + i);
            }

            while ((line = br.readLine()) != null) {
                //if the line has no comma's then it must be input string else add to transitions
                if(!line.contains(comma)) {
                    input = line;
                }
                else {
                    transitions.add(line);
                }
            }

            for (int s = 0; s < numStates; s++) {
                tm.addState(new TMState(String.valueOf(s)));
            }

            tm.setAccept(String.valueOf(numStates - 1));

            for (int i = 0; i < transitions.size(); i++) {

                int currentStateIndex = i / gammaSize;
                int currentSymbolIndex = i % gammaSize;

                String currentState = String.valueOf(currentStateIndex);
                String currentSymbol = String.valueOf(currentSymbolIndex);

                String[] info = transitions.get(i).split(comma);
                String nextState = info[0];
                String writeSymbol = info[1];
                String move = info[2];

                tm.addTransitions(currentState, currentSymbol, nextState, writeSymbol, move);
            }

            new TMSimulator().run(tm, input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute Turing Machine on input string
     *
     * @param tm    Turing Machine object
     * @param input Input string to load onto the tape before execution
     */
    public void run(TM tm, String input) {
        tm.loadInput(input);
        int sum = 0;
        //set initial sum to value of input string
        for (char c : input.toCharArray()) {
            sum = Character.getNumericValue(c) + sum;
        }

        // initial state
        TMState current = tm.getState("0");

        // main simulator loop, gets current symbol and transitions
        while (true) {
            char currentSymbol = tm.readTape();
            TMTransition t = current.getTransitions("" + currentSymbol);

            // machine halts if no valid transitions found
            if (t == null) {
                System.out.println("Halting: no transition.");
                break;
            }
            char writeSymbol = t.getWriteSymbol().charAt(0);
            if (writeSymbol != currentSymbol) {
                tm.writeTape(writeSymbol);
                sum = sum - Character.getNumericValue(currentSymbol) + Character.getNumericValue(writeSymbol);
            }
            tm.moveHead(t.getMoveDirection());
            current = tm.getState(t.getTo());

            if (current.getAcceptState()) {
                System.out.println("Accepted.");
                break;
            }
        }
        // print output of machine
        System.out.println(
                "Final tape: " + tm.toString() + "\nTape Length:" + tm.getTapeLength() + "\nSum of symbols: " + sum);
    }
}
