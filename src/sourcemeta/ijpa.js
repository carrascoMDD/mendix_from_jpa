"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var ISpecType;
(function (ISpecType) {
    ISpecType[ISpecType["ENTITY"] = 0] = "ENTITY";
    ISpecType[ISpecType["FIELD"] = 1] = "FIELD";
    ISpecType[ISpecType["ANNOTATION"] = 2] = "ANNOTATION";
    ISpecType[ISpecType["PARAMETER"] = 3] = "PARAMETER";
})(ISpecType = exports.ISpecType || (exports.ISpecType = {}));
var IAnnotatedMetaType;
(function (IAnnotatedMetaType) {
    IAnnotatedMetaType[IAnnotatedMetaType["CLASS"] = 0] = "CLASS";
    IAnnotatedMetaType[IAnnotatedMetaType["FIELD"] = 1] = "FIELD";
    IAnnotatedMetaType[IAnnotatedMetaType["METHOD"] = 2] = "METHOD";
    IAnnotatedMetaType[IAnnotatedMetaType["NESTED"] = 3] = "NESTED";
})(IAnnotatedMetaType = exports.IAnnotatedMetaType || (exports.IAnnotatedMetaType = {}));
var IAnnotationParameterType;
(function (IAnnotationParameterType) {
    IAnnotationParameterType[IAnnotationParameterType["STRING"] = 0] = "STRING";
    IAnnotationParameterType[IAnnotationParameterType["BOOLEAN"] = 1] = "BOOLEAN";
    IAnnotationParameterType[IAnnotationParameterType["INTEGER"] = 2] = "INTEGER";
    IAnnotationParameterType[IAnnotationParameterType["CLASS"] = 3] = "CLASS";
    IAnnotationParameterType[IAnnotationParameterType["ENUM"] = 4] = "ENUM";
    IAnnotationParameterType[IAnnotationParameterType["NESTED"] = 5] = "NESTED";
})(IAnnotationParameterType = exports.IAnnotationParameterType || (exports.IAnnotationParameterType = {}));
//# sourceMappingURL=ijpa.js.map