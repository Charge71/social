package com.charge71.social.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity used for the persistence of a subscription by a user to another user.
 * 
 * @author Diego Bardari
 *
 */
@Entity
@Table(name = "subscription")
public class SubscriptionEntity {

	@EmbeddedId
	private SubscriptionId subscriptionId;

	public SubscriptionId getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(SubscriptionId subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriptionEntity [user=");
		builder.append(subscriptionId.getUser());
		builder.append(", subscription=");
		builder.append(subscriptionId.getSubscription());
		builder.append("]");
		return builder.toString();
	}

}
