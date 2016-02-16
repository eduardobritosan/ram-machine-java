package memory;

import machine.Machine;


public class Instruction {
	
	private Machine.Mnemonics mnemonic;
	private String tag;
	private Machine.OperationTypes opType;
	private Integer operand;
	private final static String NULLTAG = "NULL";
	
	
	public Instruction() {
		setMnemonic(Machine.Mnemonics.NONE);
		setOpType(Machine.OperationTypes.NONE);
		setTag(NULLTAG);
	}
	
	public Instruction(Machine.Mnemonics mnemonic, Machine.OperationTypes opType){
		setMnemonic(mnemonic);
		setOpType(opType);
		setTag(NULLTAG);
	}
	
	public Instruction(Machine.Mnemonics mnemonic, Machine.OperationTypes opType, String tag){
		setMnemonic(mnemonic);
		setOpType(opType);
		setTag(tag);
	}
	/**
	 * @return the mnemonic
	 */
	private Machine.Mnemonics getMnemonic() {
		return mnemonic;
	}

	/**
	 * @param mnemonic the mnemonic to set
	 */
	public void setMnemonic(Machine.Mnemonics mnemonic) {
		this.mnemonic = mnemonic;
	}

	/**
	 * @return the tag
	 */
	private String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	private void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the opType
	 */
	private Machine.OperationTypes getOpType() {
		return opType;
	}

	/**
	 * @param opType the opType to set
	 */
	public void setOpType(Machine.OperationTypes opType) {
		this.opType = opType;
	}

	/**
	 * @return the operand
	 */
	private Integer getOperand() {
		return operand;
	}

	/**
	 * @param operand the operand to set
	 */
	public void setOperand(Integer operand) {
		this.operand = operand;
	}

}
