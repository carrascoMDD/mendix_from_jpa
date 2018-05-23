/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameterNested extends AnnotationParameter {

    private NestedAnnotationSpec nestedAnnotation;

    public AnnotationParameterNested() {
        super();
        this.setParameterType( AnnotationParameterType.NESTED);
    }

    public NestedAnnotationSpec getNestedAnnotation() {
        return nestedAnnotation;
    }

    public void setNestedAnnotation(NestedAnnotationSpec nestedAnnotation) {
        this.nestedAnnotation = nestedAnnotation;
    }
}
