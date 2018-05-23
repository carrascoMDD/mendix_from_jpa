/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterIntegerMultiple extends AnnotationParameterInteger {

    private List<Integer> integerValues;

    public AnnotationParameterIntegerMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<Integer> getIntegerValues() {
        return integerValues;
    }

    public void setIntegerValues(List<Integer> integerValues) {
        this.integerValues = integerValues;
    }
}
