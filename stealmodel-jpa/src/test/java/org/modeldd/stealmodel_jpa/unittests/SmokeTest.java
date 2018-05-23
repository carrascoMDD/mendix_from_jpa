/**
 * stealmodel_jpa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.stealmodel_jpa.unittests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modeldd.stealmodel_jpa.web.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Antonio Carrasco Valero
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private UsuarioController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
        System.out.println("Autowired MainController is available");
    }
}