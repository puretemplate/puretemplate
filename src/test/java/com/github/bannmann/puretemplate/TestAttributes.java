package com.github.bannmann.puretemplate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestAttributes extends BaseTest
{
    @Test
    public void testRedefOfKeyInClone()
    {
        ST a = new ST("x");
        // a has no formal args

        ST b = new ST(a);
        b.add("x", "foo");

        ST c = new ST(a);
        c.add("x", "bar");
        assertTrue(true); // should not get exception
    }

    /**
     * @see <a href="https://github.com/antlr/stringtemplate4/issues/72">antlr/stringtemplate4#72</a>
     * @see <a href="https://github.com/antlr/stringtemplate4/issues/98">antlr/stringtemplate4#98</a>
     */
    @Test
    public void testRedefOfKeyInCloneAfterAddingAttribute()
    {
        ST a = new ST("x");
        a.add("y", "eh?");  // This forces formal def of "y" attribute

        ST b = new ST(a);
        b.add("x", "foo");

        ST c = new ST(a);
        c.add("x", "bar");
        assertTrue(true); // should not get exception
    }
}
