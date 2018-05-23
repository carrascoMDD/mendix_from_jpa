/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterNestedSingle extends AnnotationParameterNested {

    private NestedAnnotationSpec nestedAnnotation;

    public NestedAnnotationSpec getNestedAnnotation() {
        return nestedAnnotation;
    }

    public void setNestedAnnotation(NestedAnnotationSpec nestedAnnotation) {
        this.nestedAnnotation = nestedAnnotation;
    }
}
