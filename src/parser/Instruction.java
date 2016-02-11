package parser;

public abstract class Instruction {
	
	protected static enum InstructionID {
		LOAD,
		STORE,
		ADD,
		SUB,
		MULT,
		DIV,
		JUMP,
		JZERO,
		JGTZ
	}
	
	public Instruction() {
		// TODO Auto-generated constructor stub
	}

}
