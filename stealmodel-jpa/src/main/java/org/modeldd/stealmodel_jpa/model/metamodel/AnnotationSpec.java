/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;

import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public abstract class AnnotationSpec extends NamedSpec {

    private AnnotatedMetaType annotatedMetaType;

    private List<AnnotationParameter> parameters;

    public AnnotationSpec() {
        super();
        this.setSpecType( SpecType.ANNOTATION);
    }

    public AnnotatedMetaType getAnnotatedMetaType() {
        return annotatedMetaType;
    }

    public void setAnnotatedMetaType(AnnotatedMetaType annotatedMetaType) {
        this.annotatedMetaType = annotatedMetaType;
    }

    public List<AnnotationParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<AnnotationParameter> parameters) {
        this.parameters = parameters;
    }


}
