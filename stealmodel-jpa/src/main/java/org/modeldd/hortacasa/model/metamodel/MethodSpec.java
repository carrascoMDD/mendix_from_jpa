/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;

import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class MethodSpec extends NamedSpec {

    private List<MethodAnnotationSpec> methodAnnotations;

    public MethodSpec() {
        super();
        this.setSpecType( SpecType.FIELD);
    }

    public List<MethodAnnotationSpec> getMethodAnnotations() {
        return methodAnnotations;
    }

    public void setMethodAnnotations(List<MethodAnnotationSpec> methodAnnotations) {
        this.methodAnnotations = methodAnnotations;
    }
}
