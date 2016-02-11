package tapes;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author eebritos
 *
 */
public class Tape {

	/**
	 * Clase cinta de la que heredaran la cinta de lectura y la cinta de escritura
	 */
	
	protected ArrayList<Integer> tape;
	protected Integer headPosition;
	
	public Tape() {
		setTape(new ArrayList<Integer>());
		setHeadPosition(0);
	}
	
	public Tape(ArrayList<Integer> newTape){
		setTape(newTape);
		setHeadPosition(0);
	}
	/**
	 * @return the tape
	 */
	protected ArrayList<Integer> getTape() {
		return tape;
	}

	/**
	 * @param tape the tape to set
	 */
	protected void setTape(ArrayList<Integer> tape) {
		this.tape = tape;
	}
	
	protected Integer getElement(Integer position){
		return getTape().get(position);
	}

	/**
	 * @return the headPosition
	 */
	protected Integer getHeadPosition() {
		return headPosition;
	}

	/**
	 * @param headPosition the headPosition to set
	 */
	protected void setHeadPosition(Integer headPosition) {
		this.headPosition = headPosition;
	}

}
