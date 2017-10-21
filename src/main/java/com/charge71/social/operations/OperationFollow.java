package com.charge71.social.operations;

/**
 * Operation executed when a user follows another user.
 * 
 * @author Diego Bardari
 *
 */
public class OperationFollow extends Operation {

	private String subscription;

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OperationFollow [subscription=");
		builder.append(subscription);
		builder.append(", getUser()=");
		builder.append(getUser());
		builder.append("]");
		return builder.toString();
	}

}
