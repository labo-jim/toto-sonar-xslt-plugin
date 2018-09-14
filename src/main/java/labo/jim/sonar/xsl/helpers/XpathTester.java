package labo.jim.sonar.xsl.helpers;

import java.io.File;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;

public class XpathTester {
	
	private DocumentBuilder docBuilder;
	private XPathCompiler xpathCompilo;
	private XPathExecutable exec;


	public XpathTester(String xpath) {
		Processor proc = new Processor(false);
		this.docBuilder = proc.newDocumentBuilder();
		this.xpathCompilo = proc.newXPathCompiler();
		try {
			this.exec = xpathCompilo.compile(xpath);
		} catch (SaxonApiException e) {
			throw new UnsupportedOperationException(e);
		}
	}
	
	
	public boolean doTestXPath(File xml) throws SaxonApiException {	
		return executeXpathTest(docBuilder.build(xml));
	}
	
	public boolean doTestXPath(InputStream xml) throws SaxonApiException {	
		return executeXpathTest(docBuilder.build(new StreamSource(xml)));
	}
	
	private boolean executeXpathTest(XdmItem xml) throws SaxonApiException {
		XPathSelector sel = exec.load();
		sel.setContextItem(xml);
		return sel.effectiveBooleanValue();
	}
}
