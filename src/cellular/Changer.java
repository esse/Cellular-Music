package cellular;

import javax.sound.midi.*;

public class Changer implements Runnable {
	
	Sequence sequence;
	Sequencer sequencer;
	Track track;
	int[] instruments;
	Player player;
	CellWorld cw;
	
	public Changer(Sequence _sequence, Sequencer _sequencer,
			Track _track, int[] _instruments, Player _player, CellWorld _cw ) {
		sequence = _sequence;
		sequencer = _sequencer;
		track = _track;
		instruments = _instruments;
		player = _player;
		cw = _cw;
		
	}
	
	
	@Override
	public void run() {
		while (true) {
			if (!sequencer.isRunning()) {
				sequence.deleteTrack(track);
				track = sequence.createTrack();
				player.setTrack(track);
				int[] trackList = null;
				for(int i =0; i<15;i++) {
					trackList = new int[16]; 
					int key = instruments[i];
					for (int j = 0; j<15;j++) {
						if (cw.getCell(i, j).isAlive()) {
						trackList[j] = key;
						} else {
							trackList[j] = 0;
						}
					}
					player.makeTracks(trackList);
					track.add(player.makeEvent(176,1,127,0,16));
				}
				track.add(player.makeEvent(192,9,1,0,15));
				try {
					sequencer.setSequence(sequence);
				} catch (InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sequencer.setLoopCount(1);
				sequencer.start();
				cw.nextIteration();
				//cw.print();
			}
		}
		
	}

}
