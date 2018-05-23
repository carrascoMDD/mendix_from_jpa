/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;


/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationParameterClass extends AnnotationParameter {

    public AnnotationParameterClass() {
        super();
        this.setParameterType( AnnotationParameterType.CLASS);
    }

}
