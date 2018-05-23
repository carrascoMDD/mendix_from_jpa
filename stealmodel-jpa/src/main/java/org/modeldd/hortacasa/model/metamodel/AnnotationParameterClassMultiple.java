/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class AnnotationParameterClassMultiple extends AnnotationParameterClass {

    private List<String> fullClassNames;
    private List<String> shortClassNames;

    public AnnotationParameterClassMultiple() {
        super();
        this.setMultiple( true);
    }

    public List<String> getFullClassNames() {
        return fullClassNames;
    }

    public void setFullClassNames(List<String> fullClassNames) {
        this.fullClassNames = fullClassNames;
    }

    public List<String> getShortClassNames() {
        return shortClassNames;
    }

    public void setShortClassNames(List<String> shortClassNames) {
        this.shortClassNames = shortClassNames;
    }
}
