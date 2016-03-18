package ar.fiuba.tdd.template.tp0;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by mbataglia on 18/03/16.
 */
public class TokenValidatorTest {
    private TokenValidator tokenValidator = new TokenValidator();
    @Test
    public void isQuantifier() throws Exception {
        Assert.assertThat(tokenValidator.isQuantifier("*"), is(true));
        Assert.assertThat(tokenValidator.isQuantifier("?"), is(true));
        Assert.assertThat(tokenValidator.isQuantifier("+"), is(true));
    }

    @Test
    public void isOpenUnionGenerator() throws Exception {
        Assert.assertThat(tokenValidator.isOpenUnionGenerator("[123"), is(true));
    }

    @Test
    public void isEscaped() throws Exception {
        Assert.assertThat(tokenValidator.isEscaped("\\"), is(true));
    }

    @Test
    public void isLastGenerator() throws Exception {
        Assert.assertThat(tokenValidator.isLastGenerator("a"), is(true));
    }
}