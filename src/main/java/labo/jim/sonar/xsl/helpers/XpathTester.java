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


	public XpathTester() {
		Processor proc = new Processor(false);
		this.docBuilder = proc.newDocumentBuilder();
		this.xpathCompilo = proc.newXPathCompiler();
	}
	
	
	public boolean doTestXPath(File xml,String xpath) throws SaxonApiException {	
		return executeXpathTest(docBuilder.build(xml), xpath);
	}
	
	public boolean doTestXPath(InputStream xml,String xpath) throws SaxonApiException {	
		return executeXpathTest(docBuilder.build(new StreamSource(xml)), xpath);
	}
	
	private boolean executeXpathTest(XdmItem xml,String xpath) throws SaxonApiException {
		XPathExecutable exec = xpathCompilo.compile(xpath);
		XPathSelector sel = exec.load();
		sel.setContextItem(xml);
		return sel.effectiveBooleanValue();
	}
}
