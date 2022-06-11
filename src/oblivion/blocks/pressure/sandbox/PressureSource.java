package oblivion.blocks.pressure.sandbox;

import mindustry.gen.*;
import oblivion.blocks.pressure.*;

public class PressureSource extends PressureBlock {

	public PressureSource(String name) {
		super(name);
		outputsPressure = true;
	}

	public class PressureSourceBuild extends PressureBuild {
		@Override
		public void updateTile() {
			setPressure(getMax(), this);
			for (int i = 0; i < this.proximity.size; i++) {
				Building next = this.proximity.get(i);
				if (next instanceof PressureBuild) {
					if (next.acceptsPressure) ((PressureBuild) next).setPressure(next.getMax(), this);
				}
			}
		}
	}
}