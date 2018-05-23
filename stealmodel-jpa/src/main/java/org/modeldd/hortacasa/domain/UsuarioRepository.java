/**
 * hortacasa Antonio Carrasco Valero Copyright 2018
 */
package org.modeldd.hortacasa.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.modeldd.hortacasa.domain.entities.Usuario;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * @author Antonio Carrasco Valero
 */
// @RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findByNombre(@Param("nombre") String nombre);

}
