package oblivion.blocks.pressure.sandbox;

import mindustry.gen.*;
import oblivion.blocks.pressure.*;

public class PressureSource extends PresusreBlock {

	public PressureSource(String name) {
		super(name);
		outputsPressure = true;
	}

	public class PressureSourceBuild extends PresusreBuild {
		@Override
		public void updateTile() {
			setPressure(maxPressure, this);
			for (int i = 0; i < this.proximity.size; i++) {
				Building next = this.proximity.get(i);
				if (next instanceof PresusreBuild) {
					if (next.acceptsPressure) ((PresusreBuild) next).setPressure(((PresusreBuild next)).block.maxPressure, null);
				}
			}
		}
	}
}