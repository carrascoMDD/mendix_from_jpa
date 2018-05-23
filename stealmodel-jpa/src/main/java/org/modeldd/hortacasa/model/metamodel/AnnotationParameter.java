/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameter extends AbstractSpec {

    private AnnotationParameterType parameterType;
    private String                  parameterName;
    private Boolean                 multiple = false;

    public AnnotationParameter() {
        super();
        this.setSpecType( SpecType.PARAMETER);
    }


    public AnnotationParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(AnnotationParameterType parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }
}
