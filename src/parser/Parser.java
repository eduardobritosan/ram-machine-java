package parser;

import java.io.BufferedReader;

public class Parser {
	
	private BufferedReader reader;
	
	public Parser() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the reader
	 */
	private BufferedReader getReader() {
		return reader;
	}
	/**
	 * @param reader the reader to set
	 */
	private void setReader(BufferedReader reader) {
		this.reader = reader;
	}

}
