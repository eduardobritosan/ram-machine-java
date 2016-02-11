package tapes;

import java.util.ArrayList;

public class InTape extends Tape {

	public InTape() {
		super();
	}
	
	public InTape(ArrayList<Integer> newTape){
		super(newTape);
	}
	
	public Integer read(){
		Integer returnInteger = getElement(getHeadPosition());
		setHeadPosition(getHeadPosition() + 1);
		return returnInteger;
	}
}
