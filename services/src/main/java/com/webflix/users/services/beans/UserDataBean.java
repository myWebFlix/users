package com.webflix.users.services.beans;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.kumuluz.ee.configuration.cdi.ConfigValue;
import com.webflix.users.models.entities.UserEntity;
import com.webflix.users.services.config.AppConfig;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class UserDataBean {
	@Inject
	private AppConfig appConfig;

	@PersistenceContext(unitName = "webflix-jpa")
	private EntityManager em;

	public Integer manageUser(String idTokenString) {

		//String CLIENT_ID = "304157826665-k1nt1phqk7qgsgk5m2hh6hlfkof6g5oe.apps.googleusercontent.com";
		//String CLIENT_ID = "321634089538-fge6kl40hqtie38r6issu5fidnmdiosa.apps.googleusercontent.com";

		String CLIENT_ID = appConfig.getGoogleClientID();

		System.out.println(CLIENT_ID);

		HttpTransport transport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				// Specify the CLIENT_ID of the app that accesses the backend:
				.setAudience(Collections.singletonList(CLIENT_ID))
				// Or, if multiple clients access the backend:
				//.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
				.build();

		System.out.println(idTokenString);

		GoogleIdToken idToken = null;
		try {
			idToken = verifier.verify(idTokenString);
		} catch (Exception e) {
		}

		if (idToken != null) {
			Payload payload = idToken.getPayload();
			String googleUserId = payload.getSubject();
			String userName = (String) payload.get("name");
			String email = payload.getEmail();

			UserEntity userEntity = null;
			try {
				userEntity = em.createQuery(
						"SELECT u FROM UserEntity u WHERE u.google_user_id LIKE :googleUserId", UserEntity.class)
						.setParameter("googleUserId", googleUserId)
						.setMaxResults(1)
						.getResultList().get(0);
			} catch (Exception e) {
				userEntity = new UserEntity();
				userEntity.setGoogle_user_id(googleUserId);
				userEntity.setUser_name(userName);

				beginTx();
				em.persist(userEntity);
				commitTx();
			}

			System.out.println(userEntity);


			// Print user identifier
			System.out.println("User ID: " + userEntity.getId());

			return userEntity.getId(); // Response.ok(vmes).build();
		} else {
			return null; // Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

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
