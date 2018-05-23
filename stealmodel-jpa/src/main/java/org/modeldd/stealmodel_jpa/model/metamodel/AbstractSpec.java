/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;

import java.util.List;

/**
 * @author Antonio Carrasco Valero
 */
public abstract class AbstractSpec {

    private SpecType specType;

    public SpecType getSpecType() {
        return specType;
    }

    public void setSpecType(SpecType specType) {
        this.specType = specType;
    }

}
