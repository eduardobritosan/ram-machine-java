package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import machine.Machine;
import memory.Instruction;

import org.apache.commons.lang3.StringUtils;

public class Parser {
	
	private static final Integer ZERO = 0;
	private BufferedReader reader;
	private ArrayList<String> unparsedStrings;
	private ArrayList<Instruction> parsedString;
	private ArrayList<String> tagList;
	
	public Parser(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		setReader(new BufferedReader(fileReader));
		setUnparsedStrings(new ArrayList<String>());
		setParsedString(new ArrayList<Instruction>());
		readAllLines();
		parseUnparsedStrings();
	}
	

	private void readAllLines() throws IOException{
		while (getReader().ready()) {
			String aux = getReader().readLine();
			if (aux != null) {
				addToUnparsedStrings(aux);
			}
		}
	}
	
	private void parseUnparsedStrings(){
		Instruction auxInstruction = new Instruction();
		for (String string : getUnparsedStrings()) {
			for (String elementsOfString : string.replaceAll("\t","").replaceAll(":", ": ").replaceAll(";.*", "").split(" ")) {
				StringBuilder stringBuilder = new StringBuilder(elementsOfString);
				if (elementsOfString.endsWith(":")) {
					auxInstruction.setTag(elementsOfString.replace(":", ""));
				}
				if (elementsOfString.contains("=")) {
					auxInstruction.setOpType(Machine.OperationTypes.IMMEDIATE);
					stringBuilder.deleteCharAt(stringBuilder.indexOf("="));
				}
				else if (elementsOfString.contains("*")) {
					auxInstruction.setOpType(Machine.OperationTypes.INDIRECT);
					stringBuilder.deleteCharAt(stringBuilder.indexOf("*"));
				}
				else {
					auxInstruction.setOpType(Machine.OperationTypes.DIRECT);
				}
				switch (elementsOfString.toUpperCase()) {
				case "LOAD":
					auxInstruction.setMnemonic(Machine.Mnemonics.LOAD);
					continue;
				case "STORE":
					auxInstruction.setMnemonic(Machine.Mnemonics.STORE);
					continue;
				case "ADD":
					auxInstruction.setMnemonic(Machine.Mnemonics.ADD);
					continue;
				case "SUB":
					auxInstruction.setMnemonic(Machine.Mnemonics.SUB);
					continue;
				case "MUL":
					auxInstruction.setMnemonic(Machine.Mnemonics.MUL);
					continue;
				case "DIV":
					auxInstruction.setMnemonic(Machine.Mnemonics.DIV);
					continue;
				case "WRITE":
					auxInstruction.setMnemonic(Machine.Mnemonics.WRITE);
					continue;
				case "READ":
					auxInstruction.setMnemonic(Machine.Mnemonics.READ);
					continue;
				case "JUMP":
					auxInstruction.setMnemonic(Machine.Mnemonics.JUMP);
					continue;
				case "JZERO":
					auxInstruction.setMnemonic(Machine.Mnemonics.JZERO);
					continue;
				case "JGTZ":
					auxInstruction.setMnemonic(Machine.Mnemonics.JGTZ);
					continue;
				case "HALT":
					auxInstruction.setMnemonic(Machine.Mnemonics.HALT);
					addToParsedStrings(auxInstruction);
					auxInstruction = new Instruction();
					continue;
				default:
					break;
				}
				if (StringUtils.isNumeric(stringBuilder.toString())) {
					auxInstruction.setOperand(Integer.parseInt(stringBuilder.toString()));
					addToParsedStrings(auxInstruction);
					auxInstruction = new Instruction();
				}
				
				else if (auxInstruction.getMnemonic() != Machine.Mnemonics.NONE && auxInstruction.getOperand() == null) {
					auxInstruction.setTargetTag(stringBuilder.toString());
					addToParsedStrings(auxInstruction);
					auxInstruction = new Instruction();
				}
			}
		}
	}
	
	private void addToUnparsedStrings(String newValue){
		getUnparsedStrings().add(newValue);
	}
	
	private void addToParsedStrings(Instruction newInstruction){
		getParsedString().add(newInstruction);
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


	/**
	 * @return the unparsedStrings
	 */
	private ArrayList<String> getUnparsedStrings() {
		return unparsedStrings;
	}


	/**
	 * @param unparsedStrings the unparsedStrings to set
	 */
	private void setUnparsedStrings(ArrayList<String> unparsedStrings) {
		this.unparsedStrings = unparsedStrings;
	}

	/**
	 * @return the parsedString
	 */
	public ArrayList<Instruction> getParsedString() {
		return parsedString;
	}

	/**
	 * @param parsedString the parsedString to set
	 */
	protected void setParsedString(ArrayList<Instruction> parsedString) {
		this.parsedString = parsedString;
	}

	/**
	 * @return the tagList
	 */
	private ArrayList<String> getTagList() {
		return tagList;
	}

	/**
	 * @param tagList the tagList to set
	 */
	private void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}
	
	private void addToTagList(String newString){
		getTagList().add(newString);
	}

}
