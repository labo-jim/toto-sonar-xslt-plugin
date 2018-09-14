package labo.jim;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import net.sf.saxon.s9api.SaxonApiException;
import org.junit.Test;

import labo.jim.sonar.xsl.helpers.XpathTester;

public class TestXpathExist {
	
	
	private static final String EXISTS_AS_ATTR = "exists(/toto/foo/@as)";

	@Test
	public void TestExists(){
		URL avec = TestXpathExist.class.getClassLoader().getResource("toto-avec.xml");
		try {
			
			XpathTester xpathTeser = new XpathTester(EXISTS_AS_ATTR);
			assertTrue(xpathTeser.doTestXPath(new File(avec.toURI())));
			
		} catch (URISyntaxException | SaxonApiException e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	@Test
	public void TestNotExists(){
		URL sans = TestXpathExist.class.getClassLoader().getResource("toto-sans.xml");
		try {
			
			XpathTester xpathTeser = new XpathTester(EXISTS_AS_ATTR);
			assertFalse(xpathTeser.doTestXPath(new File(sans.toURI())));
			
		} catch (URISyntaxException | SaxonApiException e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	

}
