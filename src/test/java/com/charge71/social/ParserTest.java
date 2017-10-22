package com.charge71.social;

import org.junit.Assert;
import org.junit.Test;

import com.charge71.social.impl.ParserImpl;
import com.charge71.social.operations.Operation;
import com.charge71.social.operations.OperationFollow;
import com.charge71.social.operations.OperationPost;
import com.charge71.social.operations.OperationRead;
import com.charge71.social.operations.OperationWall;

public class ParserTest {

	@Test
	public void testRead() {
		ParserImpl parser = new ParserImpl();
		Operation op = parser.parse("Test");
		Assert.assertNotNull(op);
		Assert.assertTrue(op instanceof OperationRead);
		Assert.assertEquals("Test", op.getUser());
	}

	@Test
	public void testPost() {
		ParserImpl parser = new ParserImpl();
		Operation op = parser.parse("Test -> Hi there!");
		Assert.assertNotNull(op);
		Assert.assertTrue(op instanceof OperationPost);
		Assert.assertEquals("Test", op.getUser());
		OperationPost opPost = (OperationPost) op;
		Assert.assertEquals("Hi there!", opPost.getMessage());
	}

	@Test
	public void testFollow() {
		ParserImpl parser = new ParserImpl();
		Operation op = parser.parse("Test follows Foo");
		Assert.assertNotNull(op);
		Assert.assertTrue(op instanceof OperationFollow);
		Assert.assertEquals("Test", op.getUser());
		OperationFollow opFollow = (OperationFollow) op;
		Assert.assertEquals("Foo", opFollow.getSubscription());
	}

	@Test
	public void testWall() {
		ParserImpl parser = new ParserImpl();
		Operation op = parser.parse("Test wall");
		Assert.assertNotNull(op);
		Assert.assertTrue(op instanceof OperationWall);
		Assert.assertEquals("Test", op.getUser());
	}

	@Test
	public void testUnknown() {
		ParserImpl parser = new ParserImpl();
		Operation op = parser.parse("Test test");
		Assert.assertNull(op);
	}

}
