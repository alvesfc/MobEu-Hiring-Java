package com.mobiquityinc.extract;


/**
 * Class that extract Item weight from line.
 * @author alvesfc
 * @version 1.0
 */
public class ItemWeightExtract implements ExtractInfo<Double> {

    public static final String THE_ITEM_WEIGHT_IS_CAN_NOT_BE_MORE_THAN_100 = "The item weight is can not be more than 100!";
    public static final String THE_ITEM_WEIGHT_IS_NOT_A_NUMBER = "The item weight is not a number!";
    public static final String REGEX = ",";
    public static final double MAX_WEIGHT = 100D;

    @Override
    public Double extract(final String value) {
        try {
            Double result = Double.parseDouble(value.split(REGEX)[1]);

            if(result > MAX_WEIGHT){
                throw new IllegalArgumentException(THE_ITEM_WEIGHT_IS_CAN_NOT_BE_MORE_THAN_100);
            }

            return result;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(THE_ITEM_WEIGHT_IS_NOT_A_NUMBER);
        }
    }
}
