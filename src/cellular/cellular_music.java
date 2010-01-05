package cellular;

import javax.sound.midi.InvalidMidiDataException;


public class cellular_music {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CellWorld cw = new CellWorld(15, 15);
		Player P = new Player(cw);
		cw.print();
		
		try {
			P.play();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
