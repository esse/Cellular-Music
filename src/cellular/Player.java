package cellular;

import java.util.Random;

import javax.sound.midi.*;


public class Player {
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	CellWorld cw;
	public Player(CellWorld _cw) {
		cw = _cw;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			//sequencer.setLoopCount(1);
			sequencer.setTempoInBPM((int)(Math.random() * 50)+30);
			
		} catch(Exception e) {
			sequencer.close();
			e.printStackTrace();
		}
		
	}
	
	public void play() throws InvalidMidiDataException {
		Changer changer = new Changer(sequence, sequencer, track, instruments, this, cw);
		Thread changerThread = new Thread(changer);
		changerThread.start();
	}

	public void makeTracks(int[] list) {
		   
		      Random random = new Random();
		      
		for(int i = 0;i<16;i++) {
			int key = list[i];
			if (key != 0) {
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
			e.printStackTrace();
			sequencer.close();
		}
		return event;
	}
	
	public void setTrack(Track _track) {
		track = _track;
	}
	
}
