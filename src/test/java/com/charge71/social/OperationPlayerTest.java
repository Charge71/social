package com.charge71.social;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.charge71.social.entities.PostEntity;
import com.charge71.social.entities.SubscriptionEntity;
import com.charge71.social.entities.SubscriptionId;
import com.charge71.social.operations.OperationFollow;
import com.charge71.social.operations.OperationPost;
import com.charge71.social.operations.OperationRead;
import com.charge71.social.operations.OperationWall;

public class OperationPlayerTest {

	private static OperationPlayer op;

	@BeforeClass
	public static void init() {
		op = new OperationPlayer("social-pu-test");
	}

	@AfterClass
	public static void end() throws Exception {
		op.close();
	}

	@Test
	public void testPost() {
		OperationPost opPost = new OperationPost();
		opPost.setUser("Test");
		opPost.setMessage("Message");
		List<String> list = op.play(opPost);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());

		PostEntity postEntity = op.em.find(PostEntity.class, 1l);
		Assert.assertNotNull(postEntity);
		Assert.assertEquals("Test", opPost.getUser());
		Assert.assertEquals("Message", opPost.getMessage());
	}

	@Test
	public void testRead() {
		OperationPost opPost = new OperationPost();
		opPost.setUser("TestRead");
		opPost.setMessage("Message");
		op.play(opPost);

		OperationRead opRead = new OperationRead();
		opRead.setUser("TestRead");
		List<String> list = op.play(opRead);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertTrue(list.get(0).startsWith("Message"));
	}

	@Test
	public void testFollow() {
		OperationFollow opFollow = new OperationFollow();
		opFollow.setUser("TestFollow");
		opFollow.setSubscription("Following");
		op.play(opFollow);

		SubscriptionId id = new SubscriptionId();
		id.setUser("TestFollow");
		id.setSubscription("Following");
		SubscriptionEntity entity = op.em.find(SubscriptionEntity.class, id);
		Assert.assertNotNull(entity);
		Assert.assertEquals("TestFollow", entity.getSubscriptionId().getUser());
		Assert.assertEquals("Following", entity.getSubscriptionId().getSubscription());
	}

	@Test
	public void testWall() {
		OperationPost opPost = new OperationPost();
		opPost.setUser("Follower");
		opPost.setMessage("Message1");
		op.play(opPost);
		opPost = new OperationPost();
		opPost.setUser("Following");
		opPost.setMessage("Message2");
		op.play(opPost);
		OperationFollow opFollow = new OperationFollow();
		opFollow.setUser("Follower");
		opFollow.setSubscription("Following");
		op.play(opFollow);

		OperationWall opWall = new OperationWall();
		opWall.setUser("Follower");
		List<String> list = op.play(opWall);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertTrue(list.get(0).startsWith("Following - Message2"));
		Assert.assertTrue(list.get(1).startsWith("Follower - Message1"));
	}
}
