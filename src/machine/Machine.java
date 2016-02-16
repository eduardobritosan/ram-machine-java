package machine;

import java.util.ArrayList;

import memory.InstructionRegister;
import memory.Memory;
import tapes.InTape;
import tapes.OutTape;

public class Machine {
	
	public static enum Mnemonics{
		LOAD,
		STORE,
		ADD,
		SUB,
		DIV,
		MULT,
		JUMP,
		JGTZ,
		JZERO,
		NONE
	}
	
	public static enum OperationTypes{
		DIRECT,
		INDIRECT,
		IMMEDIATE,
		NONE
	}
	
	private InTape inTape;
	private OutTape outTape;
	private Memory memory;
	private InstructionRegister instReg;
	
	public Machine() {
		setInTape(new InTape());
		setOutTape(new OutTape());
		setMemory(new Memory());
	}
	
	public Machine(ArrayList<Integer> inTape){
		setInTape(new InTape(inTape));
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

}
