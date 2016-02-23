package memory;

import java.util.ArrayList;

public class InstructionRegister {
	
	private final static int ZERO = 0;
	private final static int ONE = 1;
	private Integer currentPosition;
	private ArrayList<Instruction> instMemory;
	
	public InstructionRegister() {
		setCurrentPosition(ZERO);
		setInstMemory(new ArrayList<Instruction>());
	}

	public InstructionRegister(ArrayList<Instruction> loadedMemory) {
		setInstMemory(loadedMemory);
		setCurrentPosition(ZERO);
		// TODO Auto-generated constructor stub
	}
	
	public InstructionRegister(ArrayList<Instruction> loadedMemory, Integer currentPosition){
		setInstMemory(loadedMemory);
		setCurrentPosition(currentPosition);
	}

	/**
	 * @return the currentPosition
	 */
	private Integer getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	public Instruction getCurrentInstruction(){
		Instruction newInstruction = getInstMemory().get(getCurrentPosition());
		if ((getCurrentPosition() + ONE) <= getInstMemory().size()) {
			setCurrentPosition(getCurrentPosition() + ONE);
			return newInstruction;
		}
		return null;
	}
	
	public Instruction inspectCurrentInstruction(){
		return getInstMemory().get(getCurrentPosition());
	}

	/**
	 * @return the instMemory
	 */
	public ArrayList<Instruction> getInstMemory() {
		return instMemory;
	}

	/**
	 * @param instMemory the instMemory to set
	 */
	private void setInstMemory(ArrayList<Instruction> instMemory) {
		this.instMemory = instMemory;
	}
	
	public Instruction getInstruction(Integer pos) throws IndexOutOfBoundsException{
		return getInstMemory().get(pos);
	}
	
	public boolean hasNext(){
		return getInstMemory().size() > getCurrentPosition();
	}
	
	

}
