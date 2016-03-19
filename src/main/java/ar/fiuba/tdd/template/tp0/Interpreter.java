package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 18/03/16.
 */
public class Interpreter {
    int maxLength;
    TokenValidator tokenValidator = new TokenValidator();
    RandomNumber randomNumber = new RandomNumber(0, 1);

    public Interpreter(int maxLength) {
        this.maxLength = maxLength;
    }

    public String interpret(Token token) {
        int max;
        int min;
        StringBuffer result = new StringBuffer();
        ArrayList<String> options;
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
        randomNumber = new RandomNumber(min, max);
        int limit = randomNumber.getNumber();

        if (tokenValidator.isDot(token.getGenerator())) {


            for (int i = min; i <= limit; i++) {
                result.append(generateRandomChar());
            }

        } else {
            if (tokenValidator.isUnion(token.getGenerator())) {
                options = unionOptions(token.getGenerator());
                randomNumber.setMin(0);
                randomNumber.setMax(options.size() - 1);
                for (int i = min; i <= limit; i++) {
                    result.append(options.get(randomNumber.getNumber()));
                }
            } else {
                if (token.getQuantifier().equals("")) {
                    result.append(deleteEscaped(token.getGenerator()));
                } else {
                    for (int i = min; i < limit; i++) {
                        result.append(deleteEscaped(token.getGenerator()));
                    }

                }

            }
        }

        return result.toString();
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

        /** if(number < 14 ||number > 133 ){
         escape = "\\";
         }
         if(number == 47){
         escape = "\\";
         }*/

        //return String.valueOf(((char) randomNumber.getNumber()));
        //return escape.concat(String.valueOf(Character.toChars(randomNumber.getNumber())));
        return escape.concat(String.valueOf(Character.toChars(number)));
    }
}
