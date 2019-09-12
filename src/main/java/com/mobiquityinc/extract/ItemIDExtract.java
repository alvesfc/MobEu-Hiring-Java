package com.mobiquityinc.extract;


/**
 * Class that extract Item ID from line.
 * @author alvesfc
 * @version 1.0
 */
public class ItemIDExtract implements ExtractInfo<Integer> {

    public static final String THE_ITEM_ID_IS_NOT_A_NUMBER = "The item id is not a number!";
    public static final String COMMA = ",";
    public static final String TARGET = "(";
    public static final String EMPTY = "";

    @Override
    public Integer extract(final String value) {
        try {
            return Integer.parseInt(value.split(COMMA)[0].replace(TARGET, EMPTY));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(THE_ITEM_ID_IS_NOT_A_NUMBER);
        }
    }
}
