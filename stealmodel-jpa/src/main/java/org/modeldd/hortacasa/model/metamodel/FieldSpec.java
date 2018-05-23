/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model.metamodel;

import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public class FieldSpec extends NamedSpec {

    private String typeFullName;
    private String typeShortName;

    private List<FieldAnnotationSpec> fieldAnnotations;


    public FieldSpec() {
        super();
        this.setSpecType( SpecType.FIELD);
    }

    public String getTypeFullName() {
        return typeFullName;
    }

    public void setTypeFullName(String typeFullName) {
        this.typeFullName = typeFullName;
    }

    public String getTypeShortName() {
        return typeShortName;
    }

    public void setTypeShortName(String typeShortName) {
        this.typeShortName = typeShortName;
    }

    public List<FieldAnnotationSpec> getFieldAnnotations() {
        return fieldAnnotations;
    }

    public void setFieldAnnotations(List<FieldAnnotationSpec> fieldAnnotations) {
        this.fieldAnnotations = fieldAnnotations;
    }


}
