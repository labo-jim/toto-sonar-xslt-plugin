package labo.jim.sonar.xsl;

import org.sonar.api.Plugin;

import labo.jim.sonar.xsl.extensions.XslLanguage;
import labo.jim.sonar.xsl.extensions.XslProfile;
import labo.jim.sonar.xsl.extensions.XslRules;

public class TotoXslPlugin implements Plugin{

	public void define(Context context) {
		// TODO Auto-generated method stub
		
		// Dans un premier temps :
		// ----------------------
		// * Définition du langage
		// * Définition du ruleRepository/des rules
		
		context.addExtension(XslLanguage.class);
		context.addExtension(XslProfile.class);
		context.addExtension(XslRules.class);
		
		// Dans un deuxième temps :
		// ----------------------
		// * Création d'un sensor (faire simple et stupide pour le moment
		// * Créer des issues lorsque nécessaire.
		
		// (...) *à affiner/compléter au besoin*
		
		
		
	}

}
