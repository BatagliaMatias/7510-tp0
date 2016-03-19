package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mbataglia on 18/03/16.
 */
public class InterpreterTest {

    public Interpreter interpreter = new Interpreter(3);

    @Test
    public void testGenerateRandomChar(){
        System.out.print(interpreter.generateRandomChar());
    }
}