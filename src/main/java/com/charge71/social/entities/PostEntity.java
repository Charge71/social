package com.charge71.social.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Entity used for the persistence of a post by a user.
 * 
 * @author Diego Bardari
 *
 */
@Entity
@Table(name = "post")
@NamedQueries({
	
		/**
		 * Select all posts by a user.
		 */
		@NamedQuery(name = "PostEntity.findByUser",
				    query = "SELECT p FROM PostEntity p WHERE p.user = :user ORDER BY p.id DESC"),

		/**
		 * Select all posts by a user and by the users he folloes.
		 */
		@NamedQuery(name = "PostEntity.findBySubscription",
		            query = "SELECT p FROM PostEntity p "
						+ "WHERE p.user = :user OR p.user IN "
						+ "(SELECT s.subscriptionId.subscription FROM SubscriptionEntity s "
						+ "WHERE s.subscriptionId.user = :user) ORDER BY p.id DESC")

})
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username")
	private String user;

	@Lob
	private String message;

	@Column(name = "posttime")
	private Date timestamp;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostEntity [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", message=");
		builder.append(message);
		builder.append(", timestamp=");
		builder.append(DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(timestamp));
		builder.append("]");
		return builder.toString();
	}

}
