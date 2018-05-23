/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterBooleanMultiple extends AnnotationParameterBoolean {

    private List<Boolean> booleanValues;


    public AnnotationParameterBooleanMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<Boolean> getBooleanValues() {
        return booleanValues;
    }

    public void setBooleanValues(List<Boolean> booleanValues) {
        this.booleanValues = booleanValues;
    }
}
