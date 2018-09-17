package labo.jim;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import labo.jim.sonar.xsl.error.XmlAnalysisException;
import labo.jim.sonar.xsl.extensions.XslAsAttributeSensor;
import labo.jim.sonar.xsl.helpers.XpathLocator;
import labo.jim.sonar.xsl.helpers.XpathOccurence;

public class TestUntypedVariable {
	
	
	
	@Test
	public void testAsAtttributeLocation() {
		XpathLocator loc = new XpathLocator(XslAsAttributeSensor.UNTYPED_VARIABLE);
		try {
			List<XpathOccurence> occurences = loc.occurences(new FakeSonarInputFile("sans-as.xsl"));
			assertFalse(occurences.isEmpty());	
		} catch (XmlAnalysisException e) {
			fail("An exception was thrown : "+e.getMessage());
		}
	}

}
