package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 17/03/16.
 */
public class RegExParser {
    private TokenValidator tokenValidator = new TokenValidator();
    private Token token = new Token("", "");
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private String generator = "";

    public ArrayList<Token> parsear(String regEx) {
        String[] characters = regEx.split("");
        for (String character : characters) {
            processCharacter(character);
        }

        lastCharacter(generator);

        return tokens;
    }

    private void lastCharacter(String generator) {
        if (tokenValidator.isLastGenerator(generator)) {
            token.setGenerator(generator);
            tokens.add(token);
        }
    }

    private void processCharacter(String character) {
        if (tokenValidator.isQuantifier(character) && !tokenValidator.isLastCharacterEscape(generator)) {
            token.setQuantifier(character);
            token.setGenerator(generator);
            tokens.add(token);
            generator = "";
            token = new Token("", "");
        } else {
            if (tokenValidator.isOpenUnionGenerator(generator) || tokenValidator.isLastCharacterEscape(generator)) {
                generator += character;
            } else {
                if (!generator.equals("") ) {
                    token.setGenerator(generator);
                    tokens.add(token);
                    token = new Token("", "");
                    generator = "";
                }
                generator = character;
            }
        }
    }

}
