package com.charge71.social.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.charge71.social.OperationPlayer;
import com.charge71.social.entities.PostEntity;
import com.charge71.social.entities.SubscriptionEntity;
import com.charge71.social.entities.SubscriptionId;
import com.charge71.social.operations.Operation;
import com.charge71.social.operations.OperationFollow;
import com.charge71.social.operations.OperationPost;
import com.charge71.social.operations.OperationRead;
import com.charge71.social.operations.OperationWall;

public class OperationPlayerImpl implements OperationPlayer {

	private static final Logger logger = LoggerFactory.getLogger(OperationPlayerImpl.class);

	public final EntityManager em;
	private final EntityManagerFactory emf;

	/**
	 * Creates a new operation player using the given persistence unit.
	 * 
	 * @param puName
	 *            the name of the persistence unit to use
	 */
	public OperationPlayerImpl(String puName) {
		emf = Persistence.createEntityManagerFactory(puName);
		em = emf.createEntityManager();
	}

	public List<String> play(Operation op) {

		logger.info("Processing " + op);

		if (op instanceof OperationPost) {
			// create and persist the new post
			OperationPost opPost = (OperationPost) op;
			PostEntity post = new PostEntity();
			post.setUser(opPost.getUser());
			post.setMessage(opPost.getMessage());
			post.setTimestamp(new Date(System.currentTimeMillis()));
			em.getTransaction().begin();
			em.persist(post);
			em.getTransaction().commit();
			logger.info("Persisted " + post);
			return new ArrayList<>();
		} else if (op instanceof OperationRead) {
			// find all posts by the given user
			OperationRead opRead = (OperationRead) op;
			TypedQuery<PostEntity> query = em.createNamedQuery("PostEntity.findByUser", PostEntity.class);
			query.setParameter("user", opRead.getUser());
			List<PostEntity> list = query.getResultList();
			List<String> result = new ArrayList<>(list.size());
			for (PostEntity post : list) {
				StringBuilder sb = new StringBuilder();
				sb.append(post.getMessage());
				sb.append(" (").append(getTimeAgo(post.getTimestamp().getTime()));
				sb.append(" ago)");
				result.add(sb.toString());
			}
			return result;
		} else if (op instanceof OperationFollow) {
			// create and persist new subscription
			OperationFollow opFollow = (OperationFollow) op;
			SubscriptionId id = new SubscriptionId();
			id.setUser(opFollow.getUser());
			id.setSubscription(opFollow.getSubscription());
			SubscriptionEntity sub = new SubscriptionEntity();
			sub.setSubscriptionId(id);
			em.getTransaction().begin();
			// using merge here in case of existing subscription
			em.merge(sub);
			em.getTransaction().commit();
			logger.info("Persisted " + sub);
			return new ArrayList<>();
		} else if (op instanceof OperationWall) {
			// find all posts by a user an all the users he follows
			OperationWall opWall = (OperationWall) op;
			TypedQuery<PostEntity> query = em.createNamedQuery("PostEntity.findBySubscription", PostEntity.class);
			query.setParameter("user", opWall.getUser());
			List<PostEntity> list = query.getResultList();
			List<String> result = new ArrayList<>(list.size());
			for (PostEntity post : list) {
				StringBuilder sb = new StringBuilder();
				sb.append(post.getUser()).append(" - ");
				sb.append(post.getMessage());
				sb.append(" (").append(getTimeAgo(post.getTimestamp().getTime()));
				sb.append(" ago)");
				result.add(sb.toString());
			}
			return result;
		}
		// here unknown operation
		logger.warn("Unknown operation " + op);
		return null;

	}

	/**
	 * Close connection to database.
	 */
	@Override
	public void close() throws Exception {
		if (em != null) {
			em.clear();
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	/**
	 * Utility method to output the time elapsed since a given time in seconds.
	 * 
	 * @param time
	 * @return
	 */
	private String getTimeAgo(long time) {
		long diff = System.currentTimeMillis() - time;
		if (diff < 60 * 1000) {
			long val = diff / 1000;
			if (val == 1) {
				return String.valueOf(val) + " second";
			} else {
				return String.valueOf(val) + " seconds";
			}
		} else if (diff < 60 * 60 * 1000) {
			long val = diff / (60 * 1000);
			if (val == 1) {
				return String.valueOf(val) + " minute";
			} else {
				return String.valueOf(val) + " minutes";
			}
		} else if (diff < 24 * 60 * 60 * 1000) {
			long val = diff / (60 * 60 * 1000);
			if (val == 1) {
				return String.valueOf(val) + " hour";
			} else {
				return String.valueOf(val) + " hours";
			}
		} else {
			long val = diff / (24 * 60 * 60 * 1000);
			if (val == 1) {
				return String.valueOf(val) + " day";
			} else {
				return String.valueOf(val) + " days";
			}
		}
	}
}
