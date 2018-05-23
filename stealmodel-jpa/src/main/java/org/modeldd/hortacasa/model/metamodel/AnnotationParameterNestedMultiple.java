/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterNestedMultiple extends AnnotationParameterNested {

    private List<NestedAnnotationSpec> nestedAnnotations;

    public AnnotationParameterNestedMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<NestedAnnotationSpec> getNestedAnnotations() {
        return nestedAnnotations;
    }

    public void setNestedAnnotations(List<NestedAnnotationSpec> nestedAnnotations) {
        this.nestedAnnotations = nestedAnnotations;
    }
}
