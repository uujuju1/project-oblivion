package oblivion.blocks.pressure.sandbox;

import mindustry.gen.*;
import oblivion.blocks.pressure.*;

public class PressureVoid extends PressureBlock {

	public PressureVoid(String name) {
		super(name);
		acceptsPressure = true;
	}

	public class PressureVoidBuild extends PressureBuild {
		@Override
		public void updateTile() {
			setPressure(0f, this);
		}
	}
}