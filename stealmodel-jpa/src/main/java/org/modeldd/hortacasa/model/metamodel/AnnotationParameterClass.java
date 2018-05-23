/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameterClass extends AnnotationParameter {

    public AnnotationParameterClass() {
        super();
        this.setParameterType( AnnotationParameterType.CLASS);
    }

}
