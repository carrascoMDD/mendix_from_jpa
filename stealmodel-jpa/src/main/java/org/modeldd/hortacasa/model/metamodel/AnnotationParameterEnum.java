/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameterEnum extends AnnotationParameter {

    private String fullEnumName;
    private String shortEnumName;


    public AnnotationParameterEnum() {
        super();
        this.setParameterType( AnnotationParameterType.ENUM);
    }

    public String getFullEnumName() {
        return fullEnumName;
    }

    public void setFullEnumName(String fullEnumName) {
        this.fullEnumName = fullEnumName;
    }

    public String getShortEnumName() {
        return shortEnumName;
    }

    public void setShortEnumName(String shortEnumName) {
        this.shortEnumName = shortEnumName;
    }

}
