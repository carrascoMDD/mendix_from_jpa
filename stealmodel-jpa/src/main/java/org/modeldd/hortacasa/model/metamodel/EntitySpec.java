/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;

import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class EntitySpec extends NamedSpec {

    private List<ClassAnnotationSpec> classAnnotations;
    private List<FieldSpec>           fieldSpecs;
    private List<MethodSpec>          methodSpecs;

    public EntitySpec() {
        super();
        this.setSpecType( SpecType.ENTITY);
    }

    public List<ClassAnnotationSpec> getClassAnnotations() {
        return classAnnotations;
    }

    public void setClassAnnotations(List<ClassAnnotationSpec> classAnnotations) {
        this.classAnnotations = classAnnotations;
    }

    public List<FieldSpec> getFieldSpecs() {
        return fieldSpecs;
    }

    public void setFieldSpecs(List<FieldSpec> fieldSpecs) {
        this.fieldSpecs = fieldSpecs;
    }

    public List<MethodSpec> getMethodSpecs() {
        return methodSpecs;
    }

    public void setMethodSpecs(List<MethodSpec> methodSpecs) {
        this.methodSpecs = methodSpecs;
    }
}
