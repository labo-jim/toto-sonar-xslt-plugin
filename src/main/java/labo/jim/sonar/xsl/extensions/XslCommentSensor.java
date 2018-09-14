package labo.jim.sonar.xsl.extensions;

import java.io.IOException;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import labo.jim.sonar.xsl.helpers.XpathTester;
import net.sf.saxon.s9api.SaxonApiException;

public class XslCommentSensor implements Sensor{
	
	private static final String EXISTS_COMMENT = "exists(//comment())";
	
	private static final Logger LOG = Loggers.get(XslCommentSensor.class);
	private static final String PREFIX = "[XSL SENSOR]\t";

	@Override
	public void describe(SensorDescriptor descriptor) {
		descriptor.name("Sensor to check if XSL has at least a comment");
		
		// optimisation to disable execution of sensor if project does
	    // not contain Java files or if the example rule is not activated
	    // in the Quality profile
		descriptor.onlyOnLanguage(XslLanguage.KEY);
		descriptor.createIssuesForRuleRepository(XslRules.REPOSITORY);
		
	}

	@Override
	public void execute(SensorContext context) {
		LOG.info(PREFIX+"Begin execute method");
		
		FileSystem fs = context.fileSystem();
	    Iterable<InputFile> xslFiles = fs.inputFiles(fs.predicates().hasLanguage(XslLanguage.KEY));
	    for (InputFile inputFile : xslFiles) {
			try {
				LOG.info("Traitement de "+inputFile.filename());
				
				boolean hasComments = testComments(inputFile);
				
				if(!hasComments) {
					LOG.info(PREFIX+inputFile.filename()+"Has no comments => ISSUE !");
					createCommentIssue(context,inputFile);
				} else {
					LOG.info(PREFIX+inputFile.filename()+"Has comments => OK.");
				}
			} catch (SaxonApiException | IOException e) {
				LOG.error("Erreur dans le traitement de "+inputFile.filename(), e);
			}
		}
		
	}

	

	private void createCommentIssue(SensorContext context, InputFile inputFile) {
		 NewIssue newIssue = context.newIssue();
		 newIssue.forRule(XslRules.SHOULD_HAVE_COMMENTS);
		 newIssue.at(newIssue.newLocation().on(inputFile));
		 newIssue.save();
		
	}

	private boolean testComments(InputFile inputFile) throws SaxonApiException, IOException {
		XpathTester xpathTester = new XpathTester();
		return xpathTester.doTestXPath(inputFile.inputStream(), EXISTS_COMMENT);
	}

}
