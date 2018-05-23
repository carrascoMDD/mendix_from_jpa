/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.model;

import org.modeldd.stealmodel_jpa.model.metamodel.*;
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
public class ModelSpecManager {


    public void resetSpecs( @NotNull Map<String, EntitySpec> theSpecsRepository) {
        repositoryAccess( theSpecsRepository, Arrays.asList( new String[] {}), true /* theResetBefore */);
    }

    public EntitySpec entitySpec( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull String theClassName) {
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, Arrays.asList( new String[] { theClassName}), false /* theResetBefore */);
        if( ( someSpecs == null) || someSpecs.isEmpty()) {
            return null;
        }
        EntitySpec anEntitySpec = someSpecs.get( 0);
        return anEntitySpec;
    }


    public List<EntitySpec> entitySpecs( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull List<String> theClassNames) {
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, theClassNames, false /* theResetBefore */);
        return someSpecs;
    }

    public List<EntitySpec> entitySpecs( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull String[] theClassNames) {
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, Arrays.asList( theClassNames), false /* theResetBefore */);
        return someSpecs;
    }


    public EntitySpec entitySpecFromClass( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull Class theClass) {
        String aClassName = theClass.getCanonicalName();
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, Arrays.asList( new String[] { aClassName}), false /* theResetBefore */);
        if( ( someSpecs == null) || someSpecs.isEmpty()) {
            return null;
        }
        EntitySpec anEntitySpec = someSpecs.get( 0);
        return anEntitySpec;
    }

    public List<EntitySpec> entitySpecsFromClasses( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull List<Class> theClasses) {
        List<String> someClassNames = theClasses.stream().map(Class::getCanonicalName).collect(Collectors.toList());
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, someClassNames, false /* theResetBefore */);
        return someSpecs;
    }

    public List<EntitySpec> entitySpecsFromClasses( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull Class[] theClasses) {
        List<String> someClassNames = Arrays.asList( theClasses).stream().map(Class::getCanonicalName).collect(Collectors.toList());
        List<EntitySpec> someSpecs = repositoryAccess( theSpecsRepository, someClassNames, false /* theResetBefore */);
        return someSpecs;
    }







    private List<EntitySpec> repositoryAccess( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull List<String> theClassNames, Boolean theResetBefore) {
        synchronized( theSpecsRepository) {
            return repositoryAccess_inner(theSpecsRepository, theClassNames, theResetBefore);
        }
    }


    private List<EntitySpec> repositoryAccess_inner( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull List<String> theClassNames, Boolean theResetBefore) {

        if( theResetBefore) {
            theSpecsRepository.clear();
        }

        if( theClassNames.isEmpty()) {
            return null;
        }

        List<EntitySpec> anEntitySpecsMap = new ArrayList<>();
        for( String aClassName : theClassNames) {
            EntitySpec anEntitySpec =  entitySpec_inner( theSpecsRepository, aClassName);
            if( !( anEntitySpec == null)) {
                anEntitySpecsMap.add( anEntitySpec);
            }
        }
        return anEntitySpecsMap;
    }


    private EntitySpec entitySpec_inner( @NotNull Map<String, EntitySpec> theSpecsRepository, @NotNull String theClassName) {
        EntitySpec anEntitySpec = theSpecsRepository.get( theClassName);
        if( !( anEntitySpec == null)) {
            return anEntitySpec;
        }

        Class anEntityClass = null;
        try {
            anEntityClass = Class.forName( theClassName);
        }
        catch( ExceptionInInitializerError anException) {}
        catch( LinkageError anException) {}
        catch( ClassNotFoundException anException) {}
        if( anEntityClass == null) {
            return null;
        }

        anEntitySpec = initEntitySpec( anEntityClass);
        if( !( anEntitySpec == null)) {
            theSpecsRepository.put( theClassName, anEntitySpec);
        }

        return anEntitySpec;
    }



    private EntitySpec initEntitySpec( @NotNull Class theClass) {
        EntitySpec anEntitySpec = new EntitySpec();
        String aClassName = theClass.getCanonicalName();
        anEntitySpec.setShortName( theClass.getSimpleName());
        anEntitySpec.setFullName( aClassName);

        initEntitySpec_Class( theClass, anEntitySpec);

        initEntitySpec_Fields( theClass, anEntitySpec);

        initEntitySpec_Methods( theClass, anEntitySpec);

        return anEntitySpec;
    }

    private void initEntitySpec_Class( @NotNull Class theClass, @NotNull EntitySpec theEntitySpec) {

        TreeMap<String, ClassAnnotationSpec> someSortedClassAnotations = new TreeMap<>();

        for( Annotation anAnnotation : theClass.getDeclaredAnnotations()) {

            ClassAnnotationSpec aClassSpec = new ClassAnnotationSpec();
            Class anAnnotationType = anAnnotation.annotationType();
            String anAnnotationName = anAnnotationType.getCanonicalName();
            aClassSpec.setShortName( anAnnotationType.getSimpleName());
            aClassSpec.setFullName( anAnnotationName);

            someSortedClassAnotations.put( anAnnotationName, aClassSpec);

            initAnnotationParameters( anAnnotation, aClassSpec);
        }

        List<ClassAnnotationSpec> someClassAnnotations = theEntitySpec.getClassAnnotations();
        if( someClassAnnotations == null) {
            someClassAnnotations = new ArrayList<>();
            theEntitySpec.setClassAnnotations( someClassAnnotations);
        }
        someClassAnnotations.addAll( someSortedClassAnotations.values());
    }


    private void initEntitySpec_Fields( @NotNull Class theClass, @NotNull EntitySpec theEntitySpec) {

        List<FieldSpec> someFieldSpecs = theEntitySpec.getFieldSpecs();

        for( Field aField : theClass.getDeclaredFields()) {

            FieldSpec aFieldSpec = new FieldSpec();
            aFieldSpec.setShortName( aField.getName());
            aFieldSpec.setFullName( theClass.getCanonicalName() + "." + aField.getName());

            if( someFieldSpecs == null) {
                someFieldSpecs = new ArrayList<>();
                theEntitySpec.setFieldSpecs( someFieldSpecs);
            }

            someFieldSpecs.add( aFieldSpec);

            Class aFieldTypeClass = aField.getType();
            aFieldSpec.setTypeShortName( aFieldTypeClass.getSimpleName());
            aFieldSpec.setTypeFullName( aFieldTypeClass.getCanonicalName());

            Annotation someFieldAnnotations[] = aField.getDeclaredAnnotations();
            if( !( someFieldAnnotations == null)  && ( someFieldAnnotations.length > 0)) {

                List<FieldAnnotationSpec> someFieldAnnotationSpecs = new ArrayList<>();
                aFieldSpec.setFieldAnnotations( someFieldAnnotationSpecs);
                for( Annotation aFieldAnnotation : someFieldAnnotations) {
                    Class aFieldAnnotationType = aFieldAnnotation.annotationType();
                    FieldAnnotationSpec aFieldAnnotationSpec = new FieldAnnotationSpec();
                    aFieldAnnotationSpec.setShortName( aFieldAnnotationType.getSimpleName());
                    aFieldAnnotationSpec.setFullName( aFieldAnnotationType.getCanonicalName());
                    someFieldAnnotationSpecs.add( aFieldAnnotationSpec);

                    initAnnotationParameters( aFieldAnnotation, aFieldAnnotationSpec);
                }
            }
        }
    }


    private void initEntitySpec_Methods( @NotNull Class theClass, @NotNull EntitySpec theEntitySpec) {

        List<MethodSpec> someMethodSpecs = theEntitySpec.getMethodSpecs();

        for( Method aMethod : theClass.getDeclaredMethods()) {
            Annotation someMethodAnnotations[] = aMethod.getDeclaredAnnotations();
            if( !( someMethodAnnotations == null)  && ( someMethodAnnotations.length > 0)) {

                MethodSpec aMethodSpec = new MethodSpec();
                aMethodSpec.setShortName( aMethod.getName());
                aMethodSpec.setFullName( theClass.getCanonicalName() + "." + aMethod.getName());

                if( someMethodSpecs == null) {
                    someMethodSpecs = new ArrayList<>();
                    theEntitySpec.setMethodSpecs( someMethodSpecs);
                }

                someMethodSpecs.add( aMethodSpec);

                List<MethodAnnotationSpec> someMethodAnnotationSpecs = new ArrayList<>();
                aMethodSpec.setMethodAnnotations( someMethodAnnotationSpecs);
                for( Annotation aMethodAnnotation : someMethodAnnotations) {
                    Class aMethodAnnotationType = aMethodAnnotation.annotationType();
                    MethodAnnotationSpec aMethodAnnotationSpec = new MethodAnnotationSpec();
                    aMethodAnnotationSpec.setShortName( aMethodAnnotationType.getSimpleName());
                    aMethodAnnotationSpec.setFullName( aMethodAnnotationType.getCanonicalName());
                    someMethodAnnotationSpecs.add( aMethodAnnotationSpec);

                    initAnnotationParameters( aMethodAnnotation, aMethodAnnotationSpec);
                }
            }
        }
    }


    private void initAnnotationParameters( @NotNull Annotation theAnnotation, @NotNull AnnotationSpec theParentSpec) {

        List<AnnotationParameter> moreParameters = annotationParameters( theAnnotation);
        if( ( moreParameters == null) || moreParameters.isEmpty()) {
            return;
        }

        List<AnnotationParameter> someParameters = theParentSpec.getParameters();
        if (someParameters == null) {
            someParameters = new ArrayList<>();
            theParentSpec.setParameters(someParameters);
        }
        someParameters.addAll( moreParameters);
    }



    private List<AnnotationParameter> annotationParameters( @NotNull Annotation theAnnotation) {

        TreeMap<String, AnnotationParameter> someSortedParameters = new TreeMap<>();

        Class<?> anAnnotationType = theAnnotation.annotationType();

        for (Class<?> aClass = anAnnotationType; !( aClass == null); aClass = aClass.getSuperclass()) {
            Method[] someMethods = aClass.getDeclaredMethods();
            for (Method aMethod : someMethods) {

                String aParameterName = aMethod.getName();

                aMethod.setAccessible(true);
                Object aMethodResult = null;
                try {
                    aMethodResult = aMethod.invoke( theAnnotation);
                }
                catch (IllegalAccessException anException) {
                }
                catch (IllegalArgumentException anException) {
                }
                catch (InvocationTargetException anException) {
                }

                Class aReturnType = aMethod.getReturnType();
                AnnotationParameter aParameter = annotationParameterValue( theAnnotation, aParameterName, aReturnType, aMethodResult);
                if (!(aParameter == null)) {
                    someSortedParameters.put( aParameterName, aParameter);
                }
            }
        }

        List<AnnotationParameter> someParameters = new ArrayList<>();
        someParameters.addAll( someSortedParameters.values());
        return someParameters;
    }



    private AnnotationParameter annotationParameterValue( @NotNull Annotation theAnnotation, String theParameterName, Class theReturnType, Object theAnnotationValue) {

        if (theReturnType.isArray()) {
            return annotationParameterValueMultiple(theAnnotation, theParameterName, theReturnType, (Object[]) theAnnotationValue);
        } else {
            return annotationParameterValueSingle(theAnnotation, theParameterName, theReturnType, theAnnotationValue);
        }
    }



    private AnnotationParameter annotationParameterValueSingle( @NotNull Annotation theAnnotation, String theParameterName, Class theReturnType, Object theAnnotationValue) {

        if (theAnnotationValue == null) {
            return annotationParameterValueSingleNull(theAnnotation, theParameterName, theReturnType);
        }
        else {
            return annotationParameterValueSingleNotNull(theAnnotation, theParameterName, theReturnType, theAnnotationValue);
        }
    }



    private AnnotationParameter annotationParameterValueSingleNull( @NotNull Annotation theAnnotation, String theParameterName, Class theReturnType) {

        AnnotationParameter aParameter = null;
        if (theReturnType.isAssignableFrom( String.class)) {
            aParameter = new AnnotationParameterStringSingle();
            aParameter.setParameterName(theParameterName);
        } else {
            if (theReturnType.isAssignableFrom(  Integer.class)) {
                aParameter = new AnnotationParameterIntegerSingle();
                aParameter.setParameterName(theParameterName);
            } else {
                if (theReturnType.isAssignableFrom(  Boolean.class)) {
                    aParameter = new AnnotationParameterBooleanSingle();
                    aParameter.setParameterName(theParameterName);
                } else {
                    if (theReturnType.isAssignableFrom( Class.class)) {
                        AnnotationParameterClassSingle aParameterClass = new AnnotationParameterClassSingle();
                        aParameterClass.setParameterName(theParameterName);
                        aParameterClass.setShortClassName( theReturnType.getSimpleName());
                        aParameterClass.setFullClassName( theReturnType.getCanonicalName());
                        aParameter = aParameterClass;
                    } else {
                        if (theReturnType.isEnum()) {
                            AnnotationParameterEnumSingle aParameterEnum = new AnnotationParameterEnumSingle();
                            aParameterEnum.setParameterName(theParameterName);
                            aParameterEnum.setShortEnumName( theReturnType.getSimpleName());
                            aParameterEnum.setFullEnumName( theReturnType.getCanonicalName());
                            aParameter = aParameterEnum;
                        } else {
                            aParameter = new AnnotationParameterStringSingle();
                            aParameter.setParameterName(theParameterName);
                        }
                    }
                }
            }
        }
        return aParameter;
    }


    private AnnotationParameter annotationParameterValueSingleNotNull( @NotNull Annotation theAnnotation, String theParameterName, Class theReturnType, Object theAnnotationValue) {

        if (theAnnotationValue == null) {
            return null;
        }

        AnnotationParameter aParameter = null;
        if (theAnnotationValue instanceof String) {
            aParameter = new AnnotationParameterStringSingle();
            aParameter.setParameterName(theParameterName);
            ((AnnotationParameterStringSingle) aParameter).setStringValue((String) theAnnotationValue);
        } else {
            if (theAnnotationValue instanceof Integer) {
                aParameter = new AnnotationParameterIntegerSingle();
                aParameter.setParameterName(theParameterName);
                ((AnnotationParameterIntegerSingle) aParameter).setIntegerValue((Integer) theAnnotationValue);
            } else {
                if (theAnnotationValue instanceof Boolean) {
                    aParameter = new AnnotationParameterBooleanSingle();
                    aParameter.setParameterName(theParameterName);
                    ((AnnotationParameterBooleanSingle) aParameter).setBooleanValue((Boolean) theAnnotationValue);
                } else {
                    if (theAnnotationValue instanceof Class) {
                        AnnotationParameterClassSingle aParameterClass = new AnnotationParameterClassSingle();
                        Class aClassValue = (Class) theAnnotationValue;
                        aParameterClass.setParameterName(theParameterName);
                        aParameterClass.setShortClassName( aClassValue.getSimpleName());
                        aParameterClass.setFullClassName( aClassValue.getCanonicalName());
                        aParameter = aParameterClass;
                    } else {
                        if (theReturnType.isEnum()) {
                            AnnotationParameterEnumSingle aParameterEnum = new AnnotationParameterEnumSingle();
                            aParameterEnum.setParameterName(theParameterName);
                            aParameterEnum.setShortEnumName( theReturnType.getSimpleName());
                            aParameterEnum.setFullEnumName( theReturnType.getCanonicalName());
                            aParameterEnum.setEnumValue( theAnnotationValue.toString());
                            aParameter = aParameterEnum;
                        } else {
                            Annotation otherAnnotation = null;
                            try {
                                otherAnnotation = ( Annotation) theAnnotationValue;
                            }
                            catch( ClassCastException anException) {}

                            if( !( otherAnnotation == null)) {
                                AnnotationParameterNestedSingle aParameterNested = new AnnotationParameterNestedSingle();
                                aParameterNested.setParameterName(theParameterName);

                                NestedAnnotationSpec aNestedAnnotation = new NestedAnnotationSpec();
                                Class otherAnnotationType = otherAnnotation.annotationType();
                                aNestedAnnotation.setShortName( otherAnnotationType.getSimpleName());
                                aNestedAnnotation.setFullName( otherAnnotationType.getCanonicalName());
                                aParameterNested.setNestedAnnotation( aNestedAnnotation);

                                List<AnnotationParameter> someAnnotationParameters = annotationParameters( otherAnnotation);
                                if( !( someAnnotationParameters == null) && !someAnnotationParameters.isEmpty()) {
                                    aNestedAnnotation.setParameters( someAnnotationParameters);
                                }
                                aParameter = aParameterNested;
                            } else {
                                aParameter = new AnnotationParameterStringSingle();
                                aParameter.setParameterName(theParameterName);
                                ((AnnotationParameterStringSingle) aParameter).setStringValue(theAnnotationValue.toString());
                            }
                        }
                    }
                }
            }
        }
        return aParameter;
    }





    private AnnotationParameter annotationParameterValueMultiple( @NotNull Annotation theAnnotation, String theParameterName, Class theReturnType, Object[] theAnnotationValue) {

        if (!theReturnType.isArray()) {
            return null;
        }

        Class aComponentType = theReturnType.getComponentType();

        AnnotationParameter aParameter = null;
        if (aComponentType.isAssignableFrom( String.class)) {
            AnnotationParameterStringMultiple aParameterString = new AnnotationParameterStringMultiple();
            aParameterString.setParameterName(theParameterName);

            if( theAnnotationValue.length > 0) {
                List<String> someStringValues = new ArrayList<>( theAnnotationValue.length);
                for( Object anEnumValue : theAnnotationValue) {
                    someStringValues.add( (String) anEnumValue);
                }
                aParameterString.setStringValues( someStringValues);
            }
            else {
                aParameterString.setStringValues( new ArrayList<>());
            }
            aParameter = aParameterString;
        } else {
            if (aComponentType.isAssignableFrom( Integer.class)) {
                AnnotationParameterIntegerMultiple aParameterInteger = new AnnotationParameterIntegerMultiple();
                aParameterInteger.setParameterName(theParameterName);

                if( theAnnotationValue.length > 0) {
                    List<Integer> someIntegerValues = new ArrayList<>( theAnnotationValue.length);
                    for( Object anEnumValue : theAnnotationValue) {
                        someIntegerValues.add( (Integer) anEnumValue);
                    }
                    aParameterInteger.setIntegerValues( someIntegerValues);
                }
                else {
                    aParameterInteger.setIntegerValues( new ArrayList<>());
                }
                aParameter = aParameterInteger;
            } else {
                if (aComponentType.isAssignableFrom( Boolean.class)) {
                    AnnotationParameterBooleanMultiple aParameterBoolean = new AnnotationParameterBooleanMultiple();
                    aParameterBoolean.setParameterName(theParameterName);

                    if( theAnnotationValue.length > 0) {
                        List<Boolean> someBooleanValues = new ArrayList<>( theAnnotationValue.length);
                        for( Object anEnumValue : theAnnotationValue) {
                            someBooleanValues.add( (Boolean) anEnumValue);
                        }
                        aParameterBoolean.setBooleanValues( someBooleanValues);
                    }
                    else {
                        aParameterBoolean.setBooleanValues( new ArrayList<>());
                    }
                    aParameter = aParameterBoolean;
                } else {
                    if (aComponentType.isAssignableFrom( Class.class)) {
                        AnnotationParameterClassMultiple aParameterClass = new AnnotationParameterClassMultiple();
                        aParameterClass.setParameterName(theParameterName);

                        if( theAnnotationValue.length > 0) {
                            List<String> someClassFullNames  = new ArrayList<>( theAnnotationValue.length);
                            List<String> someClassShortNames = new ArrayList<>( theAnnotationValue.length);
                            for( Object anEnumValue : theAnnotationValue) {
                                Class oneClass = (Class) anEnumValue;
                                someClassFullNames.add(  oneClass.getCanonicalName());
                                someClassShortNames.add( oneClass.getSimpleName());
                            }
                            aParameterClass.setFullClassNames( someClassFullNames);
                            aParameterClass.setShortClassNames( someClassShortNames);
                        }
                        else {
                            aParameterClass.setFullClassNames( new ArrayList<>());
                            aParameterClass.setShortClassNames( new ArrayList<>());
                        }
                        aParameter = aParameterClass;
                    } else {
                        if (aComponentType.isEnum()) {
                            AnnotationParameterEnumMultiple aParameterEnum = new AnnotationParameterEnumMultiple();
                            aParameterEnum.setParameterName(theParameterName);
                            aParameterEnum.setShortEnumName( aComponentType.getSimpleName());
                            aParameterEnum.setFullEnumName( aComponentType.getCanonicalName());

                            if( theAnnotationValue.length > 0) {
                                List<String> someEnumValues = new ArrayList<>( theAnnotationValue.length);
                                for( Object anEnumValue : theAnnotationValue) {
                                    someEnumValues.add( anEnumValue.toString());
                                }
                                aParameterEnum.setEnumValues( someEnumValues);
                            }
                            else {
                                aParameterEnum.setEnumValues( new ArrayList<>());
                            }
                            aParameter = aParameterEnum;
                        } else {
                            Boolean anAreNestedAnnotations = false;
                            if( theAnnotationValue.length > 0) {
                                for( Object aNestedAnnotationValue : theAnnotationValue) {
                                    Annotation otherAnnotation = null;
                                    try {
                                        otherAnnotation = ( Annotation) aNestedAnnotationValue;
                                    }
                                    catch( ClassCastException anException) {}
                                    anAreNestedAnnotations = !( otherAnnotation == null);
                                }
                            }
                            if( anAreNestedAnnotations) {
                                AnnotationParameterNestedMultiple aParameterNested = new AnnotationParameterNestedMultiple();
                                aParameterNested.setParameterName(theParameterName);

                                List<NestedAnnotationSpec> someNestedAnnotationSpecs = new ArrayList<>( theAnnotationValue.length);
                                aParameterNested.setNestedAnnotations( someNestedAnnotationSpecs);

                                for( Object aNestedAnnotationValue : theAnnotationValue) {
                                    Annotation otherAnnotation = null;
                                    try {
                                        otherAnnotation = ( Annotation) aNestedAnnotationValue;
                                    }
                                    catch( ClassCastException anException) {}
                                    if( !( otherAnnotation == null))  {
                                        NestedAnnotationSpec aNestedAnnotation = new NestedAnnotationSpec();
                                        Class otherAnnotationType = otherAnnotation.annotationType();
                                        aNestedAnnotation.setShortName(otherAnnotationType.getSimpleName());
                                        aNestedAnnotation.setFullName(otherAnnotationType.getCanonicalName());
                                        someNestedAnnotationSpecs.add( aNestedAnnotation);

                                        List<AnnotationParameter> someAnnotationParameters = annotationParameters(otherAnnotation);
                                        if (!(someAnnotationParameters == null) && !someAnnotationParameters.isEmpty()) {
                                            aNestedAnnotation.setParameters(someAnnotationParameters);
                                        }
                                    }
                                }
                                aParameter = aParameterNested;
                            } else {
                                AnnotationParameterStringMultiple aParameterString = new AnnotationParameterStringMultiple();
                                aParameterString.setParameterName(theParameterName);

                                if (theAnnotationValue.length > 0) {
                                    List<String> someStringValues = new ArrayList<>(theAnnotationValue.length);
                                    for (Object anEnumValue : theAnnotationValue) {
                                        someStringValues.add(anEnumValue.toString());
                                    }
                                    aParameterString.setStringValues(someStringValues);
                                } else {
                                    aParameterString.setStringValues(new ArrayList<>());
                                }
                                aParameter = aParameterString;
                            }
                        }
                    }
                }
            }
        }
        return aParameter;
    }


}
