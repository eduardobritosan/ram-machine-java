package memory;

import java.util.ArrayList;

public class Memory {
	
	private ArrayList<Integer> memory;
	private final Integer ZERO = 0;
	
	public Memory() {
		setMemory(new ArrayList<Integer>());
		getMemory().add(ZERO);
	}
	
	public Memory(ArrayList<Integer> loadedMemory){
		setMemory(loadedMemory);
	}
	
	public void store(Integer position, Integer value){
		getMemory().add(position, value);
	}
	
	public Integer load(Integer position){
		return getMemory().get(position);
	}
	
	public Integer getAccumulator(){
		return load(ZERO);
	}
	
	public void writeInAccumulator(Integer newAcc){
		store(ZERO, newAcc);
	}

	/**
	 * @return the memory
	 */
	private ArrayList<Integer> getMemory() {
		return memory;
	}


	/**
	 * @param memory the memory to set
	 */
	private void setMemory(ArrayList<Integer> memory) {
		this.memory = memory;
	}

}
