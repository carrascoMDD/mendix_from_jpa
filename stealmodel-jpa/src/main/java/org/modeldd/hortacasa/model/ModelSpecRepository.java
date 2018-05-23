/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.model;

import org.modeldd.hortacasa.model.metamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Antonio Carrasco Valero
 */
@Service
public class ModelSpecRepository {


    @Autowired
    private ModelSpecManager modelSpecManager;


    private static final Map<String, EntitySpec> ENTITYSPECSBYNAME = new HashMap<>();


    public void resetSpecs( ) {
        modelSpecManager.resetSpecs( ENTITYSPECSBYNAME);
    }



    public EntitySpec entitySpec( @NotNull String theClassName) {
        return modelSpecManager.entitySpec( ENTITYSPECSBYNAME, theClassName);
    }

    public List<EntitySpec> entitySpecs( @NotNull List<String> theClassNames) {
        return modelSpecManager.entitySpecs( ENTITYSPECSBYNAME, theClassNames);
    }

    public List<EntitySpec> entitySpecs( @NotNull String[] theClassNames) {
        return modelSpecManager.entitySpecs( ENTITYSPECSBYNAME, theClassNames);
    }



    public EntitySpec entitySpec( @NotNull Class theClass) {
        return modelSpecManager.entitySpecFromClass( ENTITYSPECSBYNAME, theClass);
    }

    public List<EntitySpec> entitySpecsFromClasses( @NotNull List<Class> theClasses) {
        return modelSpecManager.entitySpecsFromClasses( ENTITYSPECSBYNAME, theClasses);
    }

    public List<EntitySpec> entitySpecsFromClasses( @NotNull Class[] theClasses) {
        return modelSpecManager.entitySpecsFromClasses( ENTITYSPECSBYNAME, theClasses);
    }


}
