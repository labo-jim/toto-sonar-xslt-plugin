package labo.jim.sonar.xsl.helpers;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;

public class XpathOccurence {
	
	private InputFile file;
	private Integer line;
	
	public XpathOccurence(InputFile file) {
		super();
		this.file = file;
	}

	public void setLine(Integer line) {
		this.line = line;
	}
	
	

	public InputFile getFile() {
		return file;
	}

	public Integer getLine() {
		return line;
	}

	public void wrapLocation(NewIssueLocation location) {
		location.on(this.file);
		if(this.line != null) {
			location.at(this.file.selectLine(this.line.intValue()));
		}
	}

	@Override
	public String toString() {
		return "XpathOccurence [file=" + file.filename() + ", line=" + line + "]";
	}
	
	
	
	 
	
	

}
