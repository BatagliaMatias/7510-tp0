package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

/**
 * Created by mbataglia on 18/03/16.
 */
public class RandomNumberTest {

    @Test
    public void getNumber() throws Exception {
        int min = 0;
        int max = 255;
        RandomNumber randomNumber = new RandomNumber(min, max);
        int result = randomNumber.getNumber();
        System.out.print(result);
    }
}