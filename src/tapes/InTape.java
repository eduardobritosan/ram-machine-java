package tapes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InTape extends Tape {

	public InTape() {
		super();
	}
	
	public InTape(ArrayList<Integer> newTape){
		super(newTape);
	}
	
	public InTape(String filename){
		super();
		loadFile(filename);
	}
	
	public Integer read(){
		Integer returnInteger = getElement(getHeadPosition());
		setHeadPosition(getHeadPosition() + 1);
		getTape().add(0);
		return returnInteger;
	}
	
	public void loadFile(String filename){
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader buffer = new BufferedReader(fileReader);
			try {
				while (buffer.ready()) {
					String aux = buffer.readLine();
					for (String string : aux.split(" ")) {
						getTape().add(Integer.parseInt(string));
					}
				}
				getTape().add(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
