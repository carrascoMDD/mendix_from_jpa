export interface Ijpa {
}


export enum ISpecType {
    ENTITY, FIELD, ANNOTATION, PARAMETER
}

export enum IAnnotatedMetaType {
    CLASS, FIELD, METHOD, NESTED
}

export enum IAnnotationParameterType {
    STRING, BOOLEAN, INTEGER, CLASS, ENUM, NESTED
}


export interface IAbstractSpec {
    specType: string,
    specTypeEnum?: ISpecType
}


export interface INamedSpec extends IAbstractSpec {
    fullName  : string,
    shortName : string
}


export interface IAnnotationSpec extends INamedSpec {
    /* specType shall be SpecType.ANNOTATION */
    annotatedMetaType: string,
    annotatedMetaTypeEnum?: IAnnotatedMetaType,
    // parameters: IAnnotationParameter[]
    parameters?: IAnnotationParameterCombo[] | null
}


export interface INestedAnnotationSpec extends IAnnotationSpec {
    /* annotatedMetaType shall be AnnotatedMetaType.NESTED */
}


export interface IEntitySpec extends INamedSpec {
    /* specType shall be SpecType.ENTITY */
    URI?: string,
    classAnnotations : IClassAnnotationSpec[],
    fieldSpecs : IFieldSpec[],
    methodSpecs : IMethodSpec[]
}


export interface IFieldSpec extends INamedSpec {
    /* specType shall be SpecType.FIELD */
    typeFullName : string,
    typeShortName : string,
    fieldAnnotations : IFieldAnnotationSpec[]
}


export interface IMethodSpec extends INamedSpec {
    /* specType shall be SpecType.METHOD */
    methodAnnotations : IMethodAnnotationSpec[]
}


export interface IClassAnnotationSpec extends IAnnotationSpec {
    /* annotatedMetaType shall be AnnotatedMetaType.CLASS */
}


export interface IFieldAnnotationSpec extends IAnnotationSpec {
    /* annotatedMetaType shall be AnnotatedMetaType.FIELD */
}


export interface IMethodAnnotationSpec extends IAnnotationSpec {
    /* annotatedMetaType shall be AnnotatedMetaType.METHOD */
}


export interface IAnnotationParameter extends IAbstractSpec {
    /* specType shall be SpecType.PARAMETER */
    parameterType : string,
    parameterTypeEnum? : IAnnotationParameterType,
    parameterName : string,
    multiple : boolean
}

export interface IAnnotationParameterBoolean extends IAnnotationParameter {
}

export interface IAnnotationParameterBooleanMultiple extends IAnnotationParameterBoolean {
    booleanValues : boolean[]
}

export interface IAnnotationParameterBooleanSingle extends IAnnotationParameterBoolean {
    booleanValue : boolean
}

export interface IAnnotationParameterClass extends IAnnotationParameter {
}

export interface IAnnotationParameterClassMultiple extends IAnnotationParameterClass {
    fullClassNames  : string[],
    shortClassNames : string[]
}

export interface IAnnotationParameterClassSingle extends IAnnotationParameterClass {
    fullClassName  : string
    shortClassName : string
}

export interface IAnnotationParameterEnum extends IAnnotationParameter {
    fullEnumName : string,
    shortEnumName : string
}

export interface IAnnotationParameterEnumMultiple extends IAnnotationParameterEnum {
    enumValues  : string[]
}

export interface IAnnotationParameterEnumSingle extends IAnnotationParameterEnum {
    enumValue  : string
}


export interface IAnnotationParameterInteger extends IAnnotationParameter {
}

export interface IAnnotationParameterIntegerMultiple extends IAnnotationParameterInteger {
    integerValues  : number[]
}

export interface IAnnotationParameterIntegerSingle extends IAnnotationParameterInteger {
    integerValue  : number
}


export interface IAnnotationParameterString extends IAnnotationParameter {
}

export interface IAnnotationParameterStringMultiple extends IAnnotationParameterString {
    stringValues  : string[]
}

export interface IAnnotationParameterStringSingle extends IAnnotationParameterString {
    stringValue  : string
}


export interface IAnnotationParameterNested extends IAnnotationParameter {
    nestedAnnotation : INestedAnnotationSpec
}

export interface IAnnotationParameterNestedMultiple extends IAnnotationParameterNested {
    nestedAnnotations  : INestedAnnotationSpec[]
}

export interface IAnnotationParameterNestedSingle extends IAnnotationParameterNested {
    nestedAnnotation  : INestedAnnotationSpec
}


export interface IAnnotationParameterCombo extends IAnnotationParameter {
    booleanValues? : boolean[],
    booleanValue? : boolean,
    fullClassNames? : string[],
    shortClassNames? : string[],
    fullClassName? : string
    shortClassName? : string,
    fullEnumName? : string,
    shortEnumName? : string,
    enumValues?  : string[],
    enumValue?  : string,
    integerValues?  : number,
    integerValue?  : number,
    stringValues?  : string[],
    stringValue?  : string,
    nestedAnnotation?: INestedAnnotationSpec,
    nestedAnnotations?  : INestedAnnotationSpec[]
}


