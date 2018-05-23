/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterStringMultiple extends AnnotationParameterString {

    private List<String> stringValues;


    public AnnotationParameterStringMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<String> getStringValues() {
        return stringValues;
    }

    public void setStringValues(List<String> stringValues) {
        this.stringValues = stringValues;
    }
}
