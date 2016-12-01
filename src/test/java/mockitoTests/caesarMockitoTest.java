package test.java.mockitoTests;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import main.java.algorithms.caesar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import main.java.algorithms.*;

public class caesarMockitoTest {
	@Mock
	caesar caesarAlgorithm;
	
	
	@Before
	public void create()
	{
		caesarAlgorithm = mock(caesar.class);
		
		String test = "testing string";
		int[] key = new int[1];
		key[0]=31;
		when(caesarAlgorithm.decrypt(caesarAlgorithm.encrypt(test, key),key)).thenReturn(test);
	}
	
	@Test
	public void test()
	{
		String test = "testing string";
		int[] key = new int[1];
		key[0]=31;
		
		assertEquals(caesarAlgorithm.decrypt(caesarAlgorithm.encrypt(test, key),key),test);
	}

	
}
