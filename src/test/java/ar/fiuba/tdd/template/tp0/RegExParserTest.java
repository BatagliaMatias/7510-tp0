package ar.fiuba.tdd.template.tp0;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;

/**
 * Created by mbataglia on 17/03/16.
 */
public class RegExParserTest {
    private RegExParser regExParser = new RegExParser();
    private ArrayList<Token> tokens;

    @Test
    public void testRegExVacia(){
        tokens = regExParser.parsear("");
        Assert.assertNotNull(tokens);
        Assert.assertThat(tokens.isEmpty(), is(true));
    }

    @Test
    public void testRegExDot(){
        tokens = regExParser.parsear(".");
        Token token = tokens.get(0);
        Assert.assertEquals("",token.getQuantifier());
        Assert.assertEquals(".",token.getGenerator());
    }

    @Test
    public void testRegEscapedAsterisk(){
        tokens = regExParser.parsear("\\*");
        Token token = tokens.get(0);
        Assert.assertEquals("",token.getQuantifier());
        Assert.assertEquals("\\*",token.getGenerator());
    }

    @Test
    public void testRegExLiteral(){
        tokens = regExParser.parsear("a");
        Token token = tokens.get(0);
        Assert.assertEquals("",token.getQuantifier());
        Assert.assertEquals("a",token.getGenerator());
    }

    @Test
    public void testRegExLiteralAsterisk(){
        tokens = regExParser.parsear("a*");
        Token token = tokens.get(0);
        Assert.assertEquals("*",token.getQuantifier());
        Assert.assertEquals("a",token.getGenerator());
    }

    @Test
    public void testRegExUnion(){
        tokens = regExParser.parsear("[abc]");
        Token token = tokens.get(0);
        Assert.assertEquals("",token.getQuantifier());
        Assert.assertEquals("[abc]",token.getGenerator());
    }

    @Test
    public void testRegExEscaped(){
        tokens = regExParser.parsear("\\@");
        Token token = tokens.get(0);
        Assert.assertEquals("",token.getQuantifier());
        Assert.assertEquals("\\@",token.getGenerator());
    }

    @Test
    public void testRegExABC(){
        tokens = regExParser.parsear("abc");
        Token tokenA = tokens.get(0);
        Token tokenB = tokens.get(1);
        Token tokenC = tokens.get(2);

        Assert.assertEquals("",tokenA.getQuantifier());
        Assert.assertEquals("a",tokenA.getGenerator());

        Assert.assertEquals("",tokenB.getQuantifier());
        Assert.assertEquals("b",tokenB.getGenerator());

        Assert.assertEquals("",tokenC.getQuantifier());
        Assert.assertEquals("c",tokenC.getGenerator());
    }

    @Test
    public void testRegExABCWithQuantifier(){
        tokens = regExParser.parsear("a?b*c+");
        Token tokenA = tokens.get(0);
        Token tokenB = tokens.get(1);
        Token tokenC = tokens.get(2);

        Assert.assertEquals("?",tokenA.getQuantifier());
        Assert.assertEquals("a",tokenA.getGenerator());

        Assert.assertEquals("*",tokenB.getQuantifier());
        Assert.assertEquals("b",tokenB.getGenerator());

        Assert.assertEquals("+",tokenC.getQuantifier());
        Assert.assertEquals("c",tokenC.getGenerator());
    }

    @Test
    public void testRegExABCWithQuantifierWithUnion(){
        tokens = regExParser.parsear("a?b*c+[abc\\@]*.");
        Token tokenA = tokens.get(0);
        Token tokenB = tokens.get(1);
        Token tokenC = tokens.get(2);
        Token tokenUnion = tokens.get(3);
        Token tokenDot = tokens.get(4);

        Assert.assertEquals("?",tokenA.getQuantifier());
        Assert.assertEquals("a",tokenA.getGenerator());

        Assert.assertEquals("*",tokenB.getQuantifier());
        Assert.assertEquals("b",tokenB.getGenerator());

        Assert.assertEquals("+",tokenC.getQuantifier());
        Assert.assertEquals("c",tokenC.getGenerator());

        Assert.assertEquals("*",tokenUnion.getQuantifier());
        Assert.assertEquals("[abc\\@]",tokenUnion.getGenerator());

        Assert.assertEquals("",tokenDot.getQuantifier());
        Assert.assertEquals(".",tokenDot.getGenerator());
    }

    @Test
    public void testRegExOthers(){
        tokens = regExParser.parsear(".?ñ*C+[123abc\\@]+..*.");
        Token token1 = tokens.get(0);
        Token token2 = tokens.get(1);
        Token token3 = tokens.get(2);
        Token token4 = tokens.get(3);
        Token token5 = tokens.get(4);
        Token token6 = tokens.get(5);
        Token token7 = tokens.get(6);

        Assert.assertEquals("?",token1.getQuantifier());
        Assert.assertEquals(".",token1.getGenerator());

        Assert.assertEquals("*",token2.getQuantifier());
        Assert.assertEquals("ñ",token2.getGenerator());

        Assert.assertEquals("+",token3.getQuantifier());
        Assert.assertEquals("C",token3.getGenerator());

        Assert.assertEquals("+",token4.getQuantifier());
        Assert.assertEquals("[123abc\\@]",token4.getGenerator());

        Assert.assertEquals("",token5.getQuantifier());
        Assert.assertEquals(".",token5.getGenerator());

        Assert.assertEquals("*",token6.getQuantifier());
        Assert.assertEquals(".",token6.getGenerator());

        Assert.assertEquals("",token7.getQuantifier());
        Assert.assertEquals(".",token7.getGenerator());
    }

}