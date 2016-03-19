package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;

/**
 * Created by mbataglia on 18/03/16.
 */
public class TokenValidator {

    private ArrayList<String> quantifiers;

    public TokenValidator() {
        quantifiers = new ArrayList<String>();
        quantifiers.add("*");
        quantifiers.add("+");
        quantifiers.add("?");
    }

    public Boolean isQuantifier(String character){
        return quantifiers.contains(character);
    }

    public Boolean isOpenUnionGenerator(String generator){
        return generator.startsWith("[") && ! generator.endsWith("]");
    }

    public Boolean isEscaped(String generator){
        return generator.startsWith("\\");
    }

    public Boolean isLastGenerator(String generator){
        return (!generator.equals(""));
    }

    public Boolean isLastCharacterEscape(String generator){
        return generator.endsWith("\\");
    }

    public Boolean isDot(String generator){
        return generator.equals(".");
    }

    public Boolean isUnion(String generator){
        return generator.startsWith("[") && generator.endsWith("]");
    }

}
