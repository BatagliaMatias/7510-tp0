package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 18/03/16.
 */
public class Interpreter {
    int maxLength;
    TokenValidator tokenValidator = new TokenValidator();
    RandomNumber randomNumber = new RandomNumber(0, 1);
    int max;
    int min;
    int limit;
    ArrayList<String> options;
    StringBuffer result;

    public Interpreter(int maxLength) {
        this.maxLength = maxLength;
    }

    public String interpret(Token token) {
        result = new StringBuffer();
        calculateQuantity(token);
        generateResult(token);
        return result.toString();
    }

    private void calculateQuantity(Token token) {
        switch (token.getQuantifier()) {
            case "*":
                min = 0;
                max = maxLength;
                break;
            case "?":
                min = 0;
                max = 1;

                break;
            case "+":
                min = 1;
                max = maxLength;
                break;
            default:
                min = 1;
                max = 1;
        }
        calculateLimit(token);
    }

    private void calculateLimit(Token token) {
        randomNumber = new RandomNumber(min, max);
        limit = randomNumber.getNumber();
        if (token.getQuantifier().equals("+")) {
            limit++;
        }
    }

    private void generateResult(Token token) {
        if (tokenValidator.isDot(token.getGenerator())) {
            appendResult(token);
        } else {
            if (tokenValidator.isUnion(token.getGenerator())) {
                options = unionOptions(token.getGenerator());
                randomNumber.setMin(0);
                randomNumber.setMax(options.size() - 1);
                if (token.getQuantifier().equals("")) {
                    result.append(deleteEscaped(options.get(randomNumber.getNumber())));
                } else {
                    for (int i = min; i < limit; i++) {
                        result.append(deleteEscaped(options.get(randomNumber.getNumber())));
                    }
                }

            } else {
                appendResult(token);

            }
        }

    }

    private void appendResult(Token token) {
        if (token.getQuantifier().equals("")) {
            result.append(deleteEscaped(token.getGenerator()));
        } else {
            for (int i = min; i < limit; i++) {
                result.append(deleteEscaped(token.getGenerator()));
            }

        }
    }

    private ArrayList<String> unionOptions(String generator) {
        ArrayList<String> options = new ArrayList<String>();
        String[] characters = generator.split("");
        String aux = "";
        for (String character : characters) {

            if (!character.equals("[") && !character.equals("]")) {
                if (character.equals("\\")) {
                    //aux = character;
                } else {
                    aux += character;
                    options.add(aux);
                    aux = "";
                }
            }

        }
        return options;
    }

    private String deleteEscaped(String generator) {
        String[] characters = generator.split("");
        StringBuffer result = new StringBuffer();
        for (String character : characters) {
            if (!character.equals("\\")) {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String generateRandomChar() {
        randomNumber.setMin(32); //puedo ver el numero y escaparlo si es necesario
        randomNumber.setMax(127);
        int number = randomNumber.getNumber();
        String escape = "";
        return escape.concat(String.valueOf(Character.toChars(number)));
    }

}
