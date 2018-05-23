/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */

package org.modeldd.hortacasa.web;

import java.util.List;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.TreeMap;

import org.modeldd.hortacasa.model.metamodel.AnnotationSpec;
import org.modeldd.hortacasa.model.metamodel.EntitySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.modeldd.hortacasa.model.ModelSpecRepository;
import org.modeldd.hortacasa.domain.entities.HortacasaDomainClasses;



/**
 * @author Antonio Carrasco Valero
 */
@Controller
@RequestMapping(path="/hortacasa/model")
public class ModelController {

    @Autowired
    private ModelSpecRepository modelSpecRepository;



    // Use with http://localhost:8080/hortacasa/model/resetSpecs
    @GetMapping(path="/resetSpecs")
    public @ResponseBody
    Boolean resetSpecs() {
        // This returns a JSON or XML with the users
        modelSpecRepository.resetSpecs();
        return true;
    }


    // Use with http://localhost:8080/hortacasa/model/entityNames
    @GetMapping(path="/entityNames")
    public @ResponseBody
    Iterable<String> modelEntityNames() {
        // This returns a JSON or XML with the users
        return HortacasaDomainClasses.domainClassNames();
    }


    // Use with http://localhost:8080/hortacasa/model/entitySpecs
    @GetMapping(path="/entitySpecs")
    public @ResponseBody
    Iterable<EntitySpec> entitySpecs() {
        return modelSpecRepository.entitySpecs( HortacasaDomainClasses.domainClassNames());
    }

}
