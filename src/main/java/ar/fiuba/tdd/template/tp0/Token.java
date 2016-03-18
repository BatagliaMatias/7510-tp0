package ar.fiuba.tdd.template.tp0;

/**
 * Created by mbataglia on 17/03/16.
 */
public class Token {
    private String generator;
    private String quantifier;

    public Token(String generator, String quantifier) {
        this.generator = generator;
        this.quantifier = quantifier;
    }


    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }
}

