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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OutTape [tape=" + tape + ", headPosition=" + headPosition + "]";
	}
	

}
