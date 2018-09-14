package labo.jim.sonar.xsl.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.sonar.api.batch.fs.InputFile;

import labo.jim.sonar.xsl.error.XmlAnalysisException;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;

public class XpathLocator {
	
	private DocumentBuilder docBuilder;
	private XPathCompiler xpathCompilo;
	private XPathExecutable exec;


	public XpathLocator(String xpath) {
		Processor proc = new Processor(false);
		this.docBuilder = proc.newDocumentBuilder();
		this.docBuilder.setLineNumbering(true);
		this.xpathCompilo = proc.newXPathCompiler();
		try {
			this.exec = xpathCompilo.compile(xpath);
		} catch (SaxonApiException e) {
			throw new UnsupportedOperationException(e);
		}
	}
	
	

	
	public List<XpathOccurence> occurences(InputFile xml) throws XmlAnalysisException{
		List<XpathOccurence> foundOccurences = new ArrayList<>();
		
		try {
			locateOccurences(foundOccurences, xml);
		} catch (SaxonApiException | IOException e) {
			throw new XmlAnalysisException(e);
		}
		
		return foundOccurences;
	}
	
	private void locateOccurences(List<XpathOccurence> foundOccurences, InputFile xml) throws SaxonApiException, IOException {
		XdmNode xmlNode = docBuilder.build(new StreamSource(xml.inputStream()));
		
		XPathSelector sel = exec.load();
		sel.setContextItem(xmlNode);
		Iterator<XdmItem> foundItems = sel.iterator();
		
		while(foundItems.hasNext()) {
			XdmItem item = foundItems.next();
			XpathOccurence o = new XpathOccurence(xml);
			
			if(item instanceof XdmNode) {		
				o.setLine(((XdmNode) item).getLineNumber());
			}
			
			foundOccurences.add(o);
		}
	}
}
