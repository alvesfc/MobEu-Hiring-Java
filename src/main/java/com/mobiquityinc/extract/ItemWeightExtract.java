package com.mobiquityinc.extract;


/**
 * @author alvesfc
 * @version 1.0
 */
public class ItemWeightExtract implements ExtractInfo<Double> {

    @Override
    public Double extract(final String value) {
        try {
            Double result = Double.parseDouble(value.split(",")[1]);

            if(result > 100D){
                throw new IllegalArgumentException("The item weight is can not be more than 100!");
            }

            return result;
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The item weight is not a number!");
        }
    }
}
