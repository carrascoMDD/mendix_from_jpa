/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model.metamodel;

/**
 * @author Antonio Carrasco Valero
 */
public abstract class NamedSpec extends AbstractSpec {

    private String   fullName;
    private String   shortName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
