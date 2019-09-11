package com.mobiquityinc.extract;


/**
 *
 * @author alvesfc
 * @version 1.0
 */
public class ItemCostExtract implements ExtractInfo<Double> {

    private static final String THE_ITEM_COST_IS_CAN_NOT_BE_MORE_THAN_100 = "The item cost is can not be more than 100!";
    private static final String THE_ITEM_COST_IS_NOT_A_NUMBER = "The item cost is not a number!";
    private static final String TARGET = ")";
    private static final String CURRENCY = "€";
    private static final double MAX_ITEM_COST = 100D;
    private static final String COMMA = ",";

    @Override
    public Double extract(final String value) {
        try {
            Double result =Double.parseDouble(value.split(COMMA)[2]
                    .replace(TARGET, "")
                    .replace(CURRENCY, ""));

            if(result > MAX_ITEM_COST){
                throw new IllegalArgumentException(THE_ITEM_COST_IS_CAN_NOT_BE_MORE_THAN_100);
            }

            return result;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(THE_ITEM_COST_IS_NOT_A_NUMBER);
        }
    }
}
