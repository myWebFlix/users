package com.webflix.users.services.beans;

import com.webflix.users.models.entities.UserEntity;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class UserDataBean {

	@PersistenceContext(unitName = "webflix-jpa")
	private EntityManager em;

	public List<UserEntity> getUsersRawData(){

		TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.getAll", UserEntity.class);

		return query.getResultList();
	}

	// Transactions

	private void beginTx() {
		if (!em.getTransaction().isActive())
			em.getTransaction().begin();
	}

	private void commitTx() {
		if (em.getTransaction().isActive())
			em.getTransaction().commit();
	}

	private void rollbackTx() {
		if (em.getTransaction().isActive())
			em.getTransaction().rollback();
	}

}
