package labo.jim.sonar.xsl.extensions;


import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

public class XslProfile implements BuiltInQualityProfilesDefinition{

	public void define(Context context) {
		
		NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile("XSLT Rules Quality Porfile", XslLanguage.KEY);
	    profile.setDefault(true);

	    // ======================
	    //    Comments rule
	    // ======================
	    NewBuiltInActiveRule rule1 = profile.activateRule(XslRules.REPOSITORY, XslRules.SHOULD_HAVE_COMMENTS.rule());
	    // rule1.overrideSeverity("BLOCKER");
	    
	    
	    // ==========================
	    //    Typed Variables Rule
	    // ==========================
	    profile.activateRule(XslRules.REPOSITORY, XslRules.VARIABLES_MUST_BE_TYPED.rule());

		profile.done();
	}

}
