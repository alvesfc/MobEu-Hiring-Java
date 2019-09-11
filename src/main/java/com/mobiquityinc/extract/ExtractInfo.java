package com.mobiquityinc.extract;


/**
 * Interface responsable to extract values from string.
 * @author alvesfc
 * @version 1.0
 */
@FunctionalInterface
public interface ExtractInfo<T> {

    /**
     * Method that receive a string and return a object.
     * @param value - String with content
     * @return Object with value extracted
     */
    T extract(final String value);
}
