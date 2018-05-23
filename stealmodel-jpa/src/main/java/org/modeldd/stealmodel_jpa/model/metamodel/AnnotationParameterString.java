/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameterString extends AnnotationParameter {

    public AnnotationParameterString() {
        super();
        this.setParameterType( AnnotationParameterType.STRING);
    }

}
