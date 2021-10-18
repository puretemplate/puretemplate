package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.puretemplate.BaseTest;
import org.puretemplate.Context;

public class TestFunctions extends BaseTest
{
    @Test
    public void testFirst()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", List.of("Ter", "Tom"));
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testLength()
    {
        Context context = makeTemplateContext("<length(names)>").add("names", List.of("Ter", "Tom"));
        assertRenderingResult("2", context);
    }

    @Test
    public void testLengthWithNullValues()
    {
        Context context = makeTemplateContext("<length(names)>").add("names", Arrays.asList("Ter", null, "Tom", null));
        assertRenderingResult("4", context);
    }

    @Test
    public void testFirstOp()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testFirstOpList()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testFirstOpArray()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testFirstOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("0", context);
    }

    @Test
    public void testTruncOp()
    {
        Context context = makeTemplateContext("<trunc(names); separator=\", \">").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Ter, Tom", context);
    }

    @Test
    public void testTruncOpList()
    {
        Context context = makeTemplateContext("<trunc(names); separator=\", \">").add("names",
            Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Ter, Tom", context);
    }

    @Test
    public void testTruncOpArray()
    {
        Context context = makeTemplateContext("<trunc(names); separator=\", \">").add("names",
            new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Ter, Tom", context);
    }

    @Test
    public void testTruncOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<trunc(names); separator=\", \">").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("0, 1", context);
    }

    @Test
    public void testRestOp()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Tom, Sriram", context);
    }

    @Test
    public void testRestOpList()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names",
            Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Tom, Sriram", context);
    }

    @Test
    public void testRestOpArray()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names",
            new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Tom, Sriram", context);
    }

    @Test
    public void testRestOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("1, 2", context);
    }

    @Test
    public void testRestOpEmptyList()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names", List.of());
        assertRenderingResult("", context);
    }

    @Test
    public void testRestOpEmptyArray()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names", new String[0]);
        assertRenderingResult("", context);
    }

    @Test
    public void testRestOpEmptyPrimitiveArray()
    {
        Context context = makeTemplateContext("<rest(names); separator=\", \">").add("names", new int[0]);
        assertRenderingResult("", context);
    }

    @Test
    public void testReUseOfRestResult()
    {
        String templates = "a(names) ::= \"<b(rest(names))>\"" + NEWLINE + "b(x) ::= \"<x>, <x>\"" + NEWLINE;
        Context context = loadGroupFromString(templates).getTemplate("a")
            .createContext()
            .add("names", List.of("Ter", "Tom"));
        assertRenderingResult("Tom, Tom", context);
    }

    @Test
    public void testReUseOfRestPrimitiveArrayResult()
    {
        String templates = "a(names) ::= \"<b(rest(names))>\"" + NEWLINE + "b(x) ::= \"<x>, <x>\"" + NEWLINE;
        Context context = loadGroupFromString(templates).getTemplate("a")
            .createContext()
            .add("names", new int[]{ 0, 1 });
        assertRenderingResult("1, 1", context);
    }

    @Test
    public void testLastOp()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Sriram", context);
    }

    @Test
    public void testLastOpList()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Sriram", context);
    }

    @Test
    public void testLastOpArray()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Sriram", context);
    }

    @Test
    public void testLastOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("2", context);
    }

    @Test
    public void testStripOp()
    {
        Context context = makeTemplateContext("<strip(names); null=\"n/a\">").add("names", null)
            .add("names", "Tom")
            .add("names", null)
            .add("names", null)
            .add("names", "Sriram")
            .add("names", null);
        assertRenderingResult("TomSriram", context);
    }

    @Test
    public void testStripOpList()
    {
        Context context = makeTemplateContext("<strip(names); null=\"n/a\">").add("names",
            Arrays.asList(null, "Tom", null, null, "Sriram", null));
        assertRenderingResult("TomSriram", context);
    }

    @Test
    public void testStripOpArray()
    {
        Context context = makeTemplateContext("<strip(names); null=\"n/a\">").add("names",
            new String[]{ null, "Tom", null, null, "Sriram", null });
        assertRenderingResult("TomSriram", context);
    }

    @Test
    public void testLengthStrip()
    {
        Context context = makeTemplateContext("<length(strip(names))>").add("names", null)
            .add("names", "Tom")
            .add("names", null)
            .add("names", null)
            .add("names", "Sriram")
            .add("names", null);
        assertRenderingResult("2", context);
    }

    @Test
    public void testLengthStripList()
    {
        Context context = makeTemplateContext("<length(strip(names))>").add("names",
            Arrays.asList(null, "Tom", null, null, "Sriram", null));
        assertRenderingResult("2", context);
    }

    @Test
    public void testLengthStripArray()
    {
        Context context = makeTemplateContext("<length(strip(names))>").add("names",
            new String[]{ null, "Tom", null, null, "Sriram", null });
        assertRenderingResult("2", context);
    }

    @Test
    public void testCombinedOp()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[first(mine),rest(yours)]; separator=\", \">").add("mine", "1")
            .add("mine", "2")
            .add("mine", "3")
            .add("yours", "a")
            .add("yours", "b");
        assertRenderingResult("1, b", context);
    }

    @Test
    public void testCombinedOpList()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[first(mine),rest(yours)]; separator=\", \">").add("mine",
            Arrays.asList("1", "2", "3"))
            .add("yours", "a")
            .add("yours", "b");
        assertRenderingResult("1, b", context);
    }

    @Test
    public void testCombinedOpArray()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[first(mine),rest(yours)]; separator=\", \">").add("mine",
            new String[]{ "1", "2", "3" })
            .add("yours", "a")
            .add("yours", "b");
        assertRenderingResult("1, b", context);
    }

    @Test
    public void testCombinedOpPrimitiveArray()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[first(mine),rest(yours)]; separator=\", \">").add("mine",
            new int[]{ 1, 2, 3 })
            .add("yours", "a")
            .add("yours", "b");
        assertRenderingResult("1, b", context);
    }

    @Test
    public void testCatListAndSingleAttribute()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[mine,yours]; separator=\", \">").add("mine", "1")
            .add("mine", "2")
            .add("mine", "3")
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatListAndSingleAttribute2()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[mine,yours]; separator=\", \">").add("mine",
            Arrays.asList("1", "2", "3"))
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatArrayAndSingleAttribute()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[mine,yours]; separator=\", \">").add("mine",
            new String[]{ "1", "2", "3" })
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatPrimitiveArrayAndSingleAttribute()
    {
        // replace first of yours with first of mine
        Context context = makeTemplateContext("<[mine,yours]; separator=\", \">").add("mine", new int[]{ 1, 2, 3 })
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testReUseOfCat()
    {
        String templates = "a(mine,yours) ::= \"<b([mine,yours])>\"" + NEWLINE + "b(x) ::= \"<x>, <x>\"" + NEWLINE;
        Context context = loadGroupFromString(templates).getTemplate("a")
            .createContext()
            .add("mine", List.of("Ter", "Tom"))
            .add("yours", List.of("Foo"));
        assertRenderingResult("TerTomFoo, TerTomFoo", context);
    }

    @Test
    public void testCatListAndEmptyAttributes()
    {
        /*
         * + is overloaded to be cat strings and cat lists so the
         * two operands (from left to right) determine which way it
         * goes.  In this case, x+mine is a list so everything from there
         * to the right becomes list cat.
         */
        Context context = makeTemplateContext("<[x,mine,y,yours,z]; separator=\", \">").add("mine", "1")
            .add("mine", "2")
            .add("mine", "3")
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatListAndEmptyAttributes2()
    {
        // see the comment in testCatListAndEmptyAttributes()
        Context context = makeTemplateContext("<[x,mine,y,yours,z]; separator=\", \">").add("mine",
            Arrays.asList("1", "2", "3"))
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatArrayAndEmptyAttributes2()
    {
        // see the comment in testCatListAndEmptyAttributes()
        Context context = makeTemplateContext("<[x,mine,y,yours,z]; separator=\", \">").add("mine",
            new String[]{ "1", "2", "3" })
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testCatPrimitiveArrayAndEmptyAttributes()
    {
        // see the comment in testCatListAndEmptyAttributes()
        Context context = makeTemplateContext("<[x,mine,y,yours,z]; separator=\", \">").add("mine",
            new int[]{ 1, 2, 3 })
            .add("yours", "a");
        assertRenderingResult("1, 2, 3, a", context);
    }

    @Test
    public void testNestedOp()
    {
        Context context = makeTemplateContext("<first(rest(names))>").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Tom", context);
    }

    @Test
    public void testNestedOpList()
    {
        Context context = makeTemplateContext("<first(rest(names))>").add("names",
            Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Tom", context);
    }

    @Test
    public void testNestedOpArray()
    {
        Context context = makeTemplateContext("<first(rest(names))>").add("names",
            new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Tom", context);
    }

    @Test
    public void testNestedOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<first(rest(names))>").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("1", context);
    }

    @Test
    public void testFirstWithOneAttributeOp()
    {
        Context context = makeTemplateContext("<first(names)>").add("names", "Ter");
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testLastWithOneAttributeOp()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", "Ter");
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testLastWithLengthOneListAttributeOp()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", List.of("Ter"));
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testLastWithLengthOneArrayAttributeOp()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", new String[]{ "Ter" });
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testLastWithLengthOnePrimitiveArrayAttributeOp()
    {
        Context context = makeTemplateContext("<last(names)>").add("names", new int[]{ 0 });
        assertRenderingResult("0", context);
    }

    @Test
    public void testRestWithOneAttributeOp()
    {
        Context context = makeTemplateContext("<rest(names)>").add("names", "Ter");
        assertRenderingResult("", context);
    }

    @Test
    public void testRestWithLengthOneListAttributeOp()
    {
        Context context = makeTemplateContext("<rest(names)>").add("names", List.of("Ter"));
        assertRenderingResult("", context);
    }

    @Test
    public void testRestWithLengthOneArrayAttributeOp()
    {
        Context context = makeTemplateContext("<rest(names)>").add("names", new String[]{ "Ter" });
        assertRenderingResult("", context);
    }

    @Test
    public void testRestWithLengthOnePrimitiveArrayAttributeOp()
    {
        Context context = makeTemplateContext("<rest(names)>").add("names", new int[]{ 0 });
        assertRenderingResult("", context);
    }

    @Test
    public void testRepeatedRestOp()
    {
        Context context = makeTemplateContext("<rest(names)>, <rest(names)>").add("names", "Ter")
            .add("names", "Tom");
        assertRenderingResult("Tom, Tom", context);
    }

    @Test
    public void testRepeatedRestOpList()
    {
        Context context = makeTemplateContext("<rest(names)>, <rest(names)>").add("names", Arrays.asList("Ter", "Tom"));
        assertRenderingResult("Tom, Tom", context);
    }

    @Test
    public void testRepeatedRestOpArray()
    {
        Context context = makeTemplateContext("<rest(names)>, <rest(names)>").add("names",
            new String[]{ "Ter", "Tom" });
        assertRenderingResult("Tom, Tom", context);
    }

    @Test
    public void testRepeatedRestOpPrimitiveArray()
    {
        Context context = makeTemplateContext("<rest(names)>, <rest(names)>").add("names", new int[]{ 0, 1 });
        assertRenderingResult("1, 1", context);
    }

    @Test
    public void testIncomingLists()
    {
        Context context = makeTemplateContext("<rest(names)>, <rest(names)>").add("names", "Ter")
            .add("names", "Tom");
        assertRenderingResult("Tom, Tom", context);
    }

    @Test
    public void testFirstWithCatAttribute()
    {
        Context context = makeTemplateContext("<first([names,phones])>").add("names", "Ter")
            .add("names", "Tom")
            .add("phones", "1")
            .add("phones", "2");
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testFirstWithListOfMaps()
    {
        Map<String, String> m1 = Map.of("Ter", "x5707");
        Map<String, String> m2 = Map.of("Tom", "x5332");
        Context context = makeTemplateContext("<first(maps).Ter>").add("maps", m1)
            .add("maps", m2);
        assertRenderingResult("x5707", context);

        context.add("maps", List.of(m1, m2));
        assertRenderingResult("x5707", context);
    }

    @Test
    public void testFirstWithListOfMaps2()
    {
        Map<String, String> m1 = Map.of("Ter", "x5707");
        Map<String, String> m2 = Map.of("Tom", "x5332");

        Context context = makeTemplateContext("<first(maps):{ m | <m>!}>").add("maps", m1)
            .add("maps", m2);
        assertRenderingResult("Ter!", context);

        context.add("maps", List.of(m1, m2));
        assertRenderingResult("Ter!", context);
    }

    @Test
    public void testTrim()
    {
        Context context = makeTemplateContext("<trim(name)>").add("name", " Ter  \n");
        assertRenderingResult("Ter", context);
    }

    @Test
    public void testStrlen()
    {
        Context context = makeTemplateContext("<strlen(name)>").add("name", "012345");
        assertRenderingResult("6", context);
    }

    @Test
    public void testReverse()
    {
        Context context = makeTemplateContext("<reverse(names); separator=\", \">").add("names", "Ter")
            .add("names", "Tom")
            .add("names", "Sriram");
        assertRenderingResult("Sriram, Tom, Ter", context);
    }

    @Test
    public void testReverseList()
    {
        Context context = makeTemplateContext("<reverse(names); separator=\", \">").add("names",
            Arrays.asList("Ter", "Tom", "Sriram"));
        assertRenderingResult("Sriram, Tom, Ter", context);
    }

    @Test
    public void testReverseArray()
    {
        Context context = makeTemplateContext("<reverse(names); separator=\", \">").add("names",
            new String[]{ "Ter", "Tom", "Sriram" });
        assertRenderingResult("Sriram, Tom, Ter", context);
    }

    @Test
    public void testReversePrimitiveArray()
    {
        Context context = makeTemplateContext("<reverse(names); separator=\", \">").add("names", new int[]{ 0, 1, 2 });
        assertRenderingResult("2, 1, 0", context);
    }
}
