package labo.jim.sonar.xsl.extensions;


import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

public class XslProfile implements BuiltInQualityProfilesDefinition{

	public void define(Context context) {
		
		NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile("XSLT Rules Quality Porfile", XslLanguage.KEY);
	    profile.setDefault(true);

	    // TODO todoooooooo !!
	    
//	    NewBuiltInActiveRule rule1 = profile.activateRule(REPO_KEY, "ExampleRule1");
//	    rule1.overrideSeverity("BLOCKER");
//	    NewBuiltInActiveRule rule2 = profile.activateRule(REPO_KEY, "ExampleRule2");
//	    rule2.overrideSeverity("MAJOR");
//	    NewBuiltInActiveRule rule3 = profile.activateRule(REPO_KEY, "ExampleRule3");
//	    rule3.overrideSeverity("CRITICAL");
		
	}

}
