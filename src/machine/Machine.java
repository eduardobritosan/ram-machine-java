package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EnumSet;

import memory.Instruction;
import memory.InstructionRegister;
import memory.Memory;
import parser.Parser;
import tapes.InTape;	
import tapes.OutTape;

public class Machine {
	
	public static enum Mnemonics{
		LOAD,
		STORE,
		ADD,
		SUB,
		DIV,
		MUL,
		JUMP,
		JGTZ,
		JZERO,
		WRITE,
		READ,
		HALT,
		NONE
	}
	
	public static enum OperationTypes{
		DIRECT,
		INDIRECT,
		IMMEDIATE,
		NONE
	}
	
	private final static EnumSet<Mnemonics> arithmetics = EnumSet.of(Mnemonics.ADD, Mnemonics.SUB, Mnemonics.DIV, Mnemonics.MUL);
	private final static EnumSet<Mnemonics> jumps = EnumSet.of(Mnemonics.JUMP, Mnemonics.JGTZ, Mnemonics.JZERO);
	private final static EnumSet<Mnemonics> memAccess = EnumSet.of(Mnemonics.LOAD, Mnemonics.STORE);
	private InTape inTape;
	private OutTape outTape;
	private Memory memory;
	private InstructionRegister instReg;
	private Parser parser;
	
	public Machine() {
		setInTape(new InTape());
		setOutTape(new OutTape());
		setMemory(new Memory());
		setInstReg(new InstructionRegister());
	}
	
	public Machine(String inTapeFilename, ArrayList<Integer> memory, String filename) throws IOException{
		setInTape(new InTape(inTapeFilename));
		setOutTape(new OutTape());
		setMemory(new Memory(memory));
		setParser(new Parser(filename));
		setInstReg(new InstructionRegister(getParser().getParsedString()));
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> memory = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			memory.add(0);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduzca el nombre del fichero de entrada: ");
		String inTapeFilename = in.readLine();
		System.out.println("Introduzca el nombre del fichero .ram");
		String text = in.readLine();
		
		Machine ram = new Machine(inTapeFilename, memory, text);
		ram.run();
	}
	
	public boolean run() throws Exception{
		while (getInstReg().hasNext() && checkInstruction(getInstReg().inspectCurrentInstruction())) {
			Instruction current = getInstReg().getCurrentInstruction();
			Integer value = null;
			if (current.getMnemonic() == Mnemonics.STORE || current.getMnemonic() == Mnemonics.READ  && current.getOpType() == OperationTypes.DIRECT) {
				value = current.getOperand();
			}
			else if (current.getOpType() == OperationTypes.DIRECT && !jumps.contains(current.getMnemonic()) && current.getMnemonic() != Mnemonics.HALT && current.getMnemonic() != Mnemonics.STORE || current.getMnemonic() == Mnemonics.WRITE || current.getMnemonic() == Mnemonics.LOAD) {
				value = getMemory().load(current.getOperand());
			}
			else if (current.getOpType() == OperationTypes.IMMEDIATE && !jumps.contains(current.getMnemonic()) && current.getMnemonic() != Mnemonics.HALT) {
				value = current.getOperand();
			}
			else if (current.getOpType() == OperationTypes.INDIRECT && !jumps.contains(current.getMnemonic()) && current.getMnemonic() != Mnemonics.HALT) {
				value = getMemory().load(getMemory().load(current.getOperand()));
			}
			switch (current.getMnemonic()) {
			case ADD:
				add(value);
				break;
			case MUL:
				mult(value);
				break;
			case SUB:
				sub(value);
				break;
			case DIV:
				div(value);
				break;
			case JUMP:
				jump(current.getTargetTag());
				break;
			case JGTZ:
				jgtz(current.getTargetTag());
				break;
			case JZERO:
				jzero(current.getTargetTag());
				break;
			case READ:
				read(value);
				break;
			case WRITE:
				write(value);
				break;
			case LOAD:
				load(value);
				break;
			case STORE:
				store(value);
				break;
			default:
				halt();
			}
		}
		return false;
	}
	
	private boolean checkInstruction(Instruction inst){
		
		if (jumps.contains(inst.getMnemonic()) && (inst.getOperand() == null && inst.getTargetTag() != "NULL")) {
			return true;
		}
		else if (arithmetics.contains(inst.getMnemonic()) && inst.getOperand() != null && inst.getTargetTag() == "NULL" ) {
			return true;
		}
		else if(inst.getMnemonic().equals(Mnemonics.WRITE) && inst.getOperand() != null){
			return true;
		}
		else if (inst.getMnemonic().equals(Mnemonics.READ) && inst.getOperand() != null && getInTape().movePossible()) {
			return true;
		}
		else if (inst.getMnemonic().equals(Mnemonics.HALT) && inst.getOperand() == null && inst.getTargetTag() == "NULL") {
			return true;
		}
		else if (memAccess.contains(inst.getMnemonic()) && inst.getOperand() != null && inst.getTargetTag() == "NULL") {
			return true;
		}
		return false;
	}
	/**
	 * @return the instReg
	 */
	private InstructionRegister getInstReg() {
		return instReg;
	}

	/**
	 * @param instReg the instReg to set
	 */
	private void setInstReg(InstructionRegister instReg) {
		this.instReg = instReg;
	}

	/**
	 * @return the inTape
	 */
	private InTape getInTape() {
		return inTape;
	}

	/**
	 * @param inTape the inTape to set
	 */
	private void setInTape(InTape inTape) {
		this.inTape = inTape;
	}

	/**
	 * @return the outTape
	 */
	private OutTape getOutTape() {
		return outTape;
	}

	/**
	 * @param outTape the outTape to set
	 */
	private void setOutTape(OutTape outTape) {
		this.outTape = outTape;
	}

	/**
	 * @return the memory
	 */
	private Memory getMemory() {
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	private void setMemory(Memory memory) {
		this.memory = memory;
	}

	/**
	 * @return the parser
	 */
	private Parser getParser() {
		return parser;
	}

	/**
	 * @param parser the parser to set
	 */
	private void setParser(Parser parser) {
		this.parser = parser;
	}
	
	private void add(Integer value){
		getMemory().store(0, value + getMemory().getAccumulator());
	}
	
	private void sub(Integer value){
		getMemory().store(0, getMemory().getAccumulator() - value );
	}
	
	private void mult(Integer value){
		getMemory().store(0, value * getMemory().getAccumulator());
	}
	
	private void div(Integer value){
		getMemory().store(0, value / getMemory().getAccumulator());
	}
	
	private void read(Integer targetPos){
		getMemory().store(targetPos, getInTape().read());
	}
	
	private void write(Integer value){
		getOutTape().write(value);
	}
	
	private void load(Integer v){
		getMemory().setAccumulator(v);
	}
	
	private void store(Integer v){
		getMemory().store(v, getMemory().getAccumulator());
	}
	
	private void jump(String tag){
		boolean jumpMade = false;
		
		for (int i = 0; i < getInstReg().getInstMemory().size() && !jumpMade; i++) {
			if (getInstReg().getInstruction(i).getTag().equals(tag)) {
				getInstReg().setCurrentPosition(i);
				jumpMade = true;
			}
		}
	}
	
	private boolean jgtz(String tag){
		if (getMemory().getAccumulator() > 0) {
			jump(tag);
			return true;
		}
		return false;
	}
	
	private boolean jzero(String tag){
		if (getMemory().getAccumulator() == 0) {
			jump(tag);
			return true;
		}
		return false;
	}
	
	private void halt(){
		System.out.println("Salida por HALT");
		System.out.println(getOutTape());
		System.exit(0);
	}
	
	
}
