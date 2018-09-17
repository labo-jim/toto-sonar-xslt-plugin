package labo.jim.sonar.xsl.extensions;

import java.io.IOException;
import java.util.List;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import labo.jim.sonar.xsl.error.XmlAnalysisException;
import labo.jim.sonar.xsl.helpers.XpathLocator;
import labo.jim.sonar.xsl.helpers.XpathOccurence;
import labo.jim.sonar.xsl.helpers.XpathTester;
import net.sf.saxon.s9api.SaxonApiException;

public class XslAsAttributeSensor implements Sensor{
	
	public static final String UNTYPED_VARIABLE = "//Q{" + XslLanguage.XSL_NS + "}variable[not(exists(@as))]";
	
	private static final Logger LOG = Loggers.get(XslAsAttributeSensor.class);
	
	private XpathLocator locator;
	
	public XslAsAttributeSensor() {
		this.locator = new XpathLocator(UNTYPED_VARIABLE);
	}


	@Override
	public void describe(SensorDescriptor descriptor) {
		descriptor.name("Sensor to check if there is untyped variables");
		
		// optimisation to disable execution of sensor if project does
	    // not contain Java files or if the example rule is not activated
	    // in the Quality profile
		descriptor.onlyOnLanguage(XslLanguage.KEY);
		descriptor.createIssuesForRuleRepository(XslRules.REPOSITORY);
		
	}

	@Override
	public void execute(SensorContext context) {
		LOG.info("HEY ! executing AsAttribute sensor");
		
		FileSystem fs = context.fileSystem();
	    Iterable<InputFile> xslFiles = fs.inputFiles(fs.predicates().hasLanguage(XslLanguage.KEY));
	    for (InputFile inputFile : xslFiles) {
	    	LOG.info("HEY ! AsAttribute sensor works on " + inputFile.filename());
	    	
	    	createIssueForOccurences(context, inputFile);
		}
		
	}


	private void createIssueForOccurences(SensorContext context, InputFile inputFile) {
		try {
			List<XpathOccurence> occurences = this.locator.occurences(inputFile);
			LOG.info("HEY ! here the occrurences (Untypede variables)" + occurences);
			
			for (XpathOccurence xpathOccurence : occurences) {
				NewIssue newIssue = context.newIssue();
				
				newIssue.forRule(XslRules.VARIABLES_MUST_BE_TYPED);
				
				NewIssueLocation location = newIssue.newLocation();
				xpathOccurence.wrapLocation(location);
				newIssue.at(location);
				
				newIssue.save();
			}
		} catch (XmlAnalysisException e) {
			LOG.error("An error occured analysing "+inputFile.filename(), e);
		}
		
	}

	

	

	

}
