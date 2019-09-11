package com.mobiquityinc.extract;


/**
 * @author alvesfc
 * @version 1.0
 */
public class ItemIDExtract implements ExtractInfo<Integer> {

    @Override
    public Integer extract(final String value) {
        try {
            return Integer.parseInt(value.split(",")[0].replace("(", ""));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item id is not a number!");
        }
    }
}
