package tapes;

import java.util.ArrayList;

public class OutTape extends Tape {

	public OutTape() {
		super();
	}

	public OutTape(ArrayList<Integer> newTape) {
		super(newTape);
	}
	
	public void write(Integer newInteger){
		getTape().add(newInteger);
	}

}
