<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:generate="http://jimetevenard.com/ns/generate-xsl"
    xmlns:intermediate-xsl="generate::intermediate-xsl"
    xmlns:target-xsl="generate::generate-target-stylesheet"
    xmlns:generate-utils="generate::internal-functions"
    xmlns:foo="http://exemple.com/foo"
    xmlns:saxon="http://saxon.sf.net/"
    xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl"
    version="3.0">
    
    
    
    <xsl:variable name="totoon" select="'#all'"/>
    
    
    <xsl:function name="foo:hello">
        <xsl:sequence select="'Hello !'" />
    </xsl:function>
  
    
    
</xsl:stylesheet>