package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 17/03/16.
 */
public class RegExParser {
    private TokenValidator tokenValidator = new TokenValidator();

    public ArrayList<Token> parsear(String regEx) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        String[] characters = regEx.split("");
        Token token = new Token("","");
        String generator = "";
        for (String character : characters) {
            if(tokenValidator.isQuantifier(character)){
                token.setQuantifier(character);
                token.setGenerator(generator);
                tokens.add(token);
                generator = "";
                token = new Token("","");
            }
            else{
                if(tokenValidator.isOpenUnionGenerator(generator) || tokenValidator.isEscaped(generator)){
                    generator += character;
                }
                else{
                    if(generator != ""){
                        token.setGenerator(generator);
                        tokens.add(token);
                        token = new Token("","");
                        generator = "";
                    }
                    generator = character;
                }
            }

        }

        if(tokenValidator.isLastGenerator(generator)){
            token.setGenerator(generator);
            tokens.add(token);
        }
        return tokens;
    }


}
