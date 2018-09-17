package labo.jim;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Path;

import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextPointer;
import org.sonar.api.batch.fs.TextRange;

public class FakeSonarInputFile implements InputFile{
	
	private static final String DEFAULT = "toto-avec.xml";
	
	private String srcFilename;
	
	public FakeSonarInputFile() {
		this.srcFilename = DEFAULT;
	}
	
	public FakeSonarInputFile(String srcFilename) {
		this.srcFilename = srcFilename;
	}
	
	
	
	@Override
	public URI uri() {
		try {
			return FakeSonarInputFile.class.getClassLoader().getResource(this.srcFilename).toURI();
		} catch (URISyntaxException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public String filename() {
		return this.srcFilename;
	}

	@Override
	public String key() {
		return null;
	}

	@Override
	public boolean isFile() {
		return true;
	}

	@Override
	public String relativePath() {
		return null;
	}

	@Override
	public String absolutePath() {
		return null;
	}

	@Override
	public File file() {
		return new File(uri());
	}

	@Override
	public Path path() {
		return null;
	}

	@Override
	public String language() {
		return null;
	}

	@Override
	public Type type() {
		return null;
	}

	@Override
	public InputStream inputStream() throws IOException {
		return FakeSonarInputFile.class.getClassLoader().getResourceAsStream(this.srcFilename);
	}

	@Override
	public String contents() throws IOException {
		return null;
	}

	@Override
	public Status status() {
		return null;
	}

	@Override
	public int lines() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public TextPointer newPointer(int line, int lineOffset) {
		return null;
	}

	@Override
	public TextRange newRange(TextPointer start, TextPointer end) {
		return null;
	}

	@Override
	public TextRange newRange(int startLine, int startLineOffset, int endLine, int endLineOffset) {
		return null;
	}

	@Override
	public TextRange selectLine(int line) {
		return null;
	}

	@Override
	public Charset charset() {
		return null;
	}

}
