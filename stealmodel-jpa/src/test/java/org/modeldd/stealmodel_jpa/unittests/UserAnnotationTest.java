/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.unittests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.junit.Assert;
import org.junit.Test;

import annotationassertions.AssertAnnotations;
import annotationassertions.ReflectTool;
import org.modeldd.stealmodel_jpa.domain.entities.Usuario;


/**
 * @author Antonio Carrasco Valero
 */
public class UserAnnotationTest {
    @Test
    public void typeAnnotations() {
        // assert
        AssertAnnotations.assertType(Usuario.class, Entity.class);
        System.out.println("User has Entity annotation");
    }
    @Test
    public void fieldAnnotations() {
        // assert
        AssertAnnotations.assertField(Usuario.class, "id", Id.class, GeneratedValue.class);
        System.out.println("User id has Id and GeneratedValue annotations");
    }
    /*
    @Test
    public void methodAnnotations() {
        // assert
        AssertAnnotations.assertMethod(
                User.class, "getId", Id.class, GeneratedValue.class);
        AssertAnnotations.assertMethod(
                User.class, "getName", Column.class);
        AssertAnnotations.assertMethod(
                User.class, "getEmail", Column.class);
        AssertAnnotations.assertMethod(
                User.class, "getPhones", OneToMany.class);
    }
    @Test
    public void entity() {
        // setup
        Entity a
                = ReflectTool.getClassAnnotation(User.class, Entity.class);
        // assert
        Assert.assertEquals("", a.name());
    }
    @Test
    public void table() {
        // setup
        Table t
                = ReflectTool.getClassAnnotation(User.class, Table.class);
        // assert
        Assert.assertEquals("T_PERSON", t.name());
    }
    @Test
    public void id() {
        // setup
        GeneratedValue a
                = ReflectTool.getMethodAnnotation(
                User.class, "getId", GeneratedValue.class);
        // assert
        Assert.assertEquals("", a.generator());
        Assert.assertEquals(GenerationType.AUTO, a.strategy());
    }
    @Test
    public void firstName() {
        // setup
        Column c
                = ReflectTool.getMethodAnnotation(
                User.class, "getFirstName", Column.class);
        // assert
        Assert.assertEquals("FIRST_NAME", c.name());
    }
    @Test
    public void email() {
        // setup
        Column c
                = ReflectTool.getMethodAnnotation(
                User.class, "getEmail", Column.class);
        // assert
        Assert.assertEquals("LAST_NAME", c.name());
    }

    */
}