package labo.jim.sonar.xsl.extensions;

import org.sonar.api.rule.RuleKey;
import org.sonar.api.rule.Severity;
import org.sonar.api.server.rule.RulesDefinition;

public class XslRules implements RulesDefinition {
	
	public static final String REPOSITORY = "xlst-toto";
	
	public static final RuleKey SHOULD_HAVE_COMMENTS = RuleKey.of(REPOSITORY, "comments");
	public static final RuleKey VARIABLES_MUST_BE_TYPED = RuleKey.of(REPOSITORY, "typed-variables");

	public void define(Context context) {
		NewRepository repository = context.createRepository(REPOSITORY, XslLanguage.KEY).setName("Repository Bonnes pratiques de Toto");

	    NewRule commentRule = repository.createRule(SHOULD_HAVE_COMMENTS.rule())
	      .setName("XSLT Comments")
	      .setMarkdownDescription("An XSLT file *should* have at least one comment inside it")

	      // optional tags
	      .setTags("style", "toto")

	      // default severity when the rule is activated on a Quality profile. Default value is MAJOR.
	      .setSeverity(Severity.MINOR);
	    
	   repository.createRule(VARIABLES_MUST_BE_TYPED.rule())
	  	      .setName("Variables should be typed")
	  	      .setMarkdownDescription("I's important for code reliability that variable has an @as attribute.")
	  	      .setSeverity(Severity.MAJOR);

	    commentRule.setDebtRemediationFunction(commentRule.debtRemediationFunctions().linearWithOffset("1h", "30min"));

	    // don't forget to call done() to finalize the definition
	    repository.done();
		
	}

}
