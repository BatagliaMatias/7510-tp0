package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {

    private boolean validate(String regEx, int numberOfResults) {
        int maxLength = 5;
        RegExGenerator generator = new RegExGenerator(maxLength);
        List<String> results = generator.generate(regEx, numberOfResults);
        // force matching the beginning and the end of the strings
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        return results
            .stream()
            .reduce(true,
                (acc, item) -> {
                    Matcher matcher = pattern.matcher(item);
                    return acc && matcher.find();
                },
                (item1, item2) -> item1 && item2);
    }

    @Test
    public void testAnyCharacter() {
        assertTrue(validate(".", 100));
    }

    @Test
    public void testMultipleCharacters() {
        assertTrue(validate("...", 100));
    }

    @Test
    public void testLiteral() {
        assertTrue(validate("\\@", 100));
    }

    @Test
    public void testLiteralRepeat() {
        assertTrue(validate("\\@", 100));
    }

    @Test
    public void testTonsOfDots() {
        assertTrue(validate(".........", 100));
    }

    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 100));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 100));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 100));
    }

    @Test
    public void testCharacterSetWithEscaped() {
        assertTrue(validate("[\\@\\!]", 100));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 100));
    }

    @Test
    public void testCharacterSetWithQuantifiers2() {
        assertTrue(validate("[abc\\@]*", 100));
    }

    @Test
    public void testCharacterSetWithQuantifiers3() {
        assertTrue(validate("[abc\\@]?", 100));
    }

    @Test
    public void testCharacterSetWithQuantifiersComplex() {
        assertTrue(validate("[abc\\@]?[ac\\@]*[abc\\@]+[abc\\@]", 100));
    }

    @Test
    public void testCharacterLiteral() {
        assertTrue(validate("\\!", 100));
    }

    @Test
    public void testCharacterLiteral2() {
        assertTrue(validate("\\!\\&", 100));
    }

    @Test
    public void testCharacterLiteral3() {
        assertTrue(validate("\\!\\&abc", 100));
    }

    @Test
    public void testComplexRegex() {
        assertTrue(validate(".?[aft]*\\?+.*", 100));
    }

    @Test
    public void testUltimateComplex() {
        assertTrue(validate(".?[aft]*\\?+.*.?.+[123]?[123]+[123]*\\++\\.\\??.", 100));
    }

    @Test
    public void testAsterisk() {
        assertTrue(validate("*", 100));
    }

    @Test
    public void testPlus() {
        assertTrue(validate("+", 100));
    }

    @Test
    public void testInterrogant() {
        assertTrue(validate("?", 100));
    }

    @Test
    public void testEmpty() {
        assertTrue(validate("", 100));
    }

    @Test
    public void testDotZeroOrOne() {
        assertTrue(validate(".?", 100));
    }

    @Test
    public void testUnionZeroOrOne() {
        assertTrue(validate("[abc]?", 100));
    }
}
