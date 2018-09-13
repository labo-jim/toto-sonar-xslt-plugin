package labo.jim.sonar.xsl.extensions;


import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

public class XslProfile implements BuiltInQualityProfilesDefinition{

	public void define(Context context) {
		
		NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile("XSLT Rules Quality Porfile", XslLanguage.KEY);
	    profile.setDefault(true);

	    
	    NewBuiltInActiveRule rule1 = profile.activateRule(XslRules.REPOSITORY, XslRules.SHOULD_HAVE_COMMENTS.rule());
	    // rule1.overrideSeverity("BLOCKER");

		profile.done();
	}

}
