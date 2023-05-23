package com.academia.em_forma.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academia.em_forma.domain.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class AbstractDao <T, PK extends Serializable> {

	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = 
			(Class<T>) ( (ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(T entity) { 

		entityManager.persist(entity);
	}
	
	public void update(T entity) {
		
		entityManager.merge(entity);
	}
	
	public void delete(PK id) {
		
		entityManager.remove(entityManager.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		
		return entityManager.find(entityClass, id);
	}
	
	public Optional<T> findByUsuarioId(PK id) {
	    String jpql = "SELECT t FROM " + entityClass.getSimpleName() + " t WHERE t.usuario.id = :id";
	    TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
	    query.setParameter("id", id);
	    List<T> resultList = query.getResultList();
	    if (resultList.isEmpty()) {
	        return Optional.empty();
	    } else {
	        return Optional.of(resultList.get(0));
	    }
	}
	
	public T findByEmail(PK email) {
		return entityManager.find(entityClass, email);
	}
	
	public List<T> findAll() {
		
		return entityManager
				.createQuery("from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}	
	
	protected List<T> createQuery(String jpql, Object... params) {
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for (int i = 0; i < params.length; i++) {
		    query.setParameter(i+1, params[i]);
        }
    	return query.getResultList();
	}

	

}
