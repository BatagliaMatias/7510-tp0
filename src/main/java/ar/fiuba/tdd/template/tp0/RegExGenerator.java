package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {

    private int maxLength;
    private RegExParser regExParser = new RegExParser();
    private  Interpreter interpreter;
    public RegExGenerator(int maxLength) {
        this.maxLength = maxLength;
        interpreter = new Interpreter(maxLength);
    }

    // TODO: Uncomment parameters
    public List<String> generate(String regEx, int numberOfResults) {
        ArrayList<String> results = new ArrayList<String>();

        for (int i = 0; i < numberOfResults; i++) {
            results.add("");
        }

        ArrayList<Token> tokens = regExParser.parsear(regEx);

        for (Token token:tokens) {
            for (int i = 0; i < results.size() ; i++) {
                results.set(i, results.get(i) + interpreter.interpret(token));
            }

        }

        return results;
    }
}