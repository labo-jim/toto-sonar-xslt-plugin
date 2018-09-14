package labo.jim;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import labo.jim.sonar.xsl.error.XmlAnalysisException;
import labo.jim.sonar.xsl.helpers.XpathLocator;
import labo.jim.sonar.xsl.helpers.XpathOccurence;

public class TestXpathLocator {
	
	private static final String AS_ATTRIBUTE = "//@*";
	
	
	@Test
	public void testAsAtttributeLocation() {
		XpathLocator loc = new XpathLocator(AS_ATTRIBUTE);
		try {
			List<XpathOccurence> occurences = loc.occurences(new FakeSonarInputFile());
			
			// in the sample file, the first (and only) @as is at line 3
			assertTrue(occurences.get(0).getLine().intValue() == 3);	
		} catch (XmlAnalysisException e) {
			fail("An exception was thrown : "+e.getMessage());
		}
	}

}
