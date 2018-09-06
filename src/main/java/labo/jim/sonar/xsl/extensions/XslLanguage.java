package labo.jim.sonar.xsl.extensions;

import org.sonar.api.resources.AbstractLanguage;

public class XslLanguage extends AbstractLanguage{
	
	public static final String NAME = "XSLT";
	public static final String KEY = "xslt";
	


	public XslLanguage() {
		super(KEY, NAME);
	}

	public String[] getFileSuffixes() {
		return new String[] {"xsl","xslt"};
	}

}
