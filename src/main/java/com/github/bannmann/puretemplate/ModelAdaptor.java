package com.github.bannmann.puretemplate;

import java.util.Map;

import com.github.bannmann.puretemplate.misc.STNoSuchPropertyException;

/**
 * An object that knows how to convert property references to appropriate actions on a model object. Some models, like
 * JDBC, are interface based (we aren't supposed to care about implementation classes). Some other models don't follow
 * StringTemplate's getter method naming convention. So, if we have an object of type {@code M} with property method
 * {@code M.foo()} (as opposed to {@code M.getFoo()}), we can register a model adaptor object, {@code adap}, that
 * converts a lookup for property {@code foo} into a call to {@code M.foo()}.
 * <p>
 * Given {@code <a.foo>}, we look up {@code foo} via the adaptor if {@code a instanceof M}.</p>
 *
 * @param <T> the type of values this adaptor can handle.
 */
public interface ModelAdaptor<T>
{
    /**
     * Lookup property name in {@code o} and return its value.
     * <p>
     * {@code property} is normally a {@code String} but doesn't have to be. E.g., if {@code o} is {@link Map}, {@code
     * property} could be any key type. If we need to convert to {@code String}, then it's done by {@code ST} and passed
     * in here.</p>
     */
    Object getProperty(Interpreter interp, ST self, T model, Object property, String propertyName)
        throws STNoSuchPropertyException;
}
