package memory;

import machine.Machine;


public class Instruction {
	
	private Machine.Mnemonics mnemonic;
	private String tag;
	private Machine.OperationTypes opType;
	private Integer operand;
	private String targetTag;
	private final static String NULLTAG = "NULL";
	
	
	public Instruction() {
		setMnemonic(Machine.Mnemonics.NONE);
		setOpType(Machine.OperationTypes.NONE);
		setTag(NULLTAG);
		setTargetTag(NULLTAG);
	}
	
	public Instruction(Machine.Mnemonics mnemonic, Machine.OperationTypes opType){
		setMnemonic(mnemonic);
		setOpType(opType);
		setTag(NULLTAG);
		setTargetTag(NULLTAG);
	}
	
	public Instruction(Machine.Mnemonics mnemonic, Machine.OperationTypes opType, String tag){
		setMnemonic(mnemonic);
		setOpType(opType);
		setTag(tag);
		setTargetTag(NULLTAG);
	}
	
	public Instruction(Machine.Mnemonics mnemonic, Machine.OperationTypes opType, String tag, String targetTag){
		setMnemonic(mnemonic);
		setOpType(opType);
		setTag(tag);
		setTargetTag(targetTag);
	}
	/**
	 * @return the mnemonic
	 */
	public Machine.Mnemonics getMnemonic() {
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
	public String getTag() {
		return tag;
	}

	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * @return the opType
	 */
	public Machine.OperationTypes getOpType() {
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
	public Integer getOperand() {
		return operand;
	}

	/**
	 * @param operand the operand to set
	 */
	public void setOperand(Integer operand) {
		this.operand = operand;
	}

	/**
	 * @return the targetTag
	 */
	public String getTargetTag() {
		return targetTag;
	}

	/**
	 * @param targetTag the targetTag to set
	 */
	public void setTargetTag(String targetTag) {
		this.targetTag = targetTag;
	}

}
