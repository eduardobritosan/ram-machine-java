package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import machine.Machine;
import memory.Instruction;

import org.apache.commons.lang3.StringUtils;

public class Parser {
	
	private BufferedReader reader;
	private ArrayList<String> unparsedStrings;
	private ArrayList<Instruction> parsedString;
	
	public Parser(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		setReader(new BufferedReader(fileReader));
		setUnparsedStrings(new ArrayList<String>());
		setParsedString(new ArrayList<Instruction>());
		readAllLines();
		parseUnparsedStrings();
	}
	
	public static void main(String[] args) throws IOException {
		Parser parse = new Parser("test.txt");
		System.out.println(parse.toString());
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
			for (String elementsOfString : string.split(" ")) {
				StringBuilder stringBuilder = new StringBuilder(elementsOfString);
				
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
				switch (elementsOfString) {
				case "LOAD":
					auxInstruction.setMnemonic(Machine.Mnemonics.LOAD);
					break;
				case "STORE":
					auxInstruction.setMnemonic(Machine.Mnemonics.STORE);
					break;
				case "ADD":
					auxInstruction.setMnemonic(Machine.Mnemonics.ADD);
					break;
				case "SUB":
					auxInstruction.setMnemonic(Machine.Mnemonics.SUB);
					break;
				case "MULT":
					auxInstruction.setMnemonic(Machine.Mnemonics.MULT);
					break;
				case "DIV":
					auxInstruction.setMnemonic(Machine.Mnemonics.DIV);
					break;
				case "JUMP":
					auxInstruction.setMnemonic(Machine.Mnemonics.JUMP);
					break;
				case "JZERO":
					auxInstruction.setMnemonic(Machine.Mnemonics.JZERO);
					break;
				case "JGTZ":
					auxInstruction.setMnemonic(Machine.Mnemonics.JGTZ);
					break;
				default:
					break;
				}
				if (StringUtils.isNumericSpace(stringBuilder.toString())) {
					auxInstruction.setOperand(Integer.parseInt(stringBuilder.toString()));
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
	protected ArrayList<Instruction> getParsedString() {
		return parsedString;
	}

	/**
	 * @param parsedString the parsedString to set
	 */
	protected void setParsedString(ArrayList<Instruction> parsedString) {
		this.parsedString = parsedString;
	}

}
