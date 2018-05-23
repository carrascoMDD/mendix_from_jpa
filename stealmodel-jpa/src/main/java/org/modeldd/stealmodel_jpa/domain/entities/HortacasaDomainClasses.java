package org.modeldd.stealmodel_jpa.domain.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HortacasaDomainClasses {

    private static final List<Class> DOMAINCLASSES = Arrays.asList( new Class[] {
            Usuario.class,
            Rol.class,
            Autorizacion.class});

    private static final List<String> DOMAINCLASSNAMES = DOMAINCLASSES.stream().map(Class::getCanonicalName).collect( Collectors.toList());


    public static List<Class> domainClasses() {
        return new ArrayList<>( DOMAINCLASSES);
    }

    public static List<String> domainClassNames() {
        return new ArrayList<>( DOMAINCLASSNAMES);
    }
}
