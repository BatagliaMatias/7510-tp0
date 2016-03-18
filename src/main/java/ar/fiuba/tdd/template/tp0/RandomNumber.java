package ar.fiuba.tdd.template.tp0;

import java.util.Random;

/**
 * Created by mbataglia on 18/03/16.
 */
public class RandomNumber {
    private Random random = new Random();
    private int min;
    private int max;

    public RandomNumber(int min,int max){
        this.min = min;
        this.max = max + 1;
    }

    public int getNumber(){
        return random.nextInt(max-min) + min;
    }
}
