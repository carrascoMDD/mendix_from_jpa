/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterEnumMultiple extends AnnotationParameterEnum {

    private List<String> enumValues;


    public AnnotationParameterEnumMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues;
    }
}
