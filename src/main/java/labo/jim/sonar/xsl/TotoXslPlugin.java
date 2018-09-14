package labo.jim.sonar.xsl;

import org.sonar.api.Plugin;
import org.sonar.api.utils.log.Loggers;

import labo.jim.sonar.xsl.extensions.XslCommentSensor;
import labo.jim.sonar.xsl.extensions.XslLanguage;
import labo.jim.sonar.xsl.extensions.XslProfile;
import labo.jim.sonar.xsl.extensions.XslRules;

public class TotoXslPlugin implements Plugin{

	public void define(Context context) {
		Loggers.get(TotoXslPlugin.class).info("Helloooo ! INIT TOTO'S XSL SONAR PLUGIN !");
		
		// TODO Auto-generated method stub
		
		// Dans un premier temps :
		// ----------------------
		// * Définition du langage
		// * Définition du ruleRepository/des rules
		
		context.addExtension(XslLanguage.class);
		context.addExtension(XslLanguage.getProperties());
		context.addExtension(XslProfile.class);
		context.addExtension(XslRules.class);
		
		// Dans un deuxième temps :
		// ----------------------
		// * Création d'un sensor (faire simple et stupide pour le moment
		// * Créer des issues lorsque nécessaire.
		context.addExtension(XslCommentSensor.class);
		
		// (...) *à affiner/compléter au besoin*
		
		
		
	}

}
