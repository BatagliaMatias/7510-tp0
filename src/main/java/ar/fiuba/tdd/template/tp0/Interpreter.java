package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 18/03/16.
 */
public class Interpreter {
    int maxLength;
    TokenValidator tokenValidator = new TokenValidator();
    RandomNumber randomNumber;
    public Interpreter(int maxLength){
        this.maxLength = maxLength;
    }

    public String interpret(Token token){
        int max;
        int min;
        String result = "";
        ArrayList<String> options;
        switch (token.getQuantifier()){
            case "*":
                min = 0;
                max = maxLength;
                randomNumber = new RandomNumber(min,max);
                break;
            case "?":
                min = 0;
                max = 1;
                randomNumber = new RandomNumber(min,max);
                break;
            case "+":
                min = 1;
                max = maxLength;
                randomNumber = new RandomNumber(min,max);
                break;
            default:
                min = 1;
                max = 1;
        }

        if(tokenValidator.isDot(token.getGenerator())){
            options = allOptions();
        }
        else{
            if(tokenValidator.isUnion(token.getGenerator())){
                options = unionOptions(token.getGenerator());
            }
            else{
                return token.getGenerator();
            }
        }

        for (int i = min; i <= max; i++) {

        }


    }

}
