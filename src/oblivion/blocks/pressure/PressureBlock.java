package oblivion.blocks.pressure;

import arc.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import oblivion.blocks.meta.*;

public class PressureBlock extends Block {
	public boolean acceptsPressure = false, outputsPressure = false;
	public float minPressure = -35f, maxPressure = 35f;

	public PressureBlock(String name) {
		super(name);
	}

	@Override
	public void setBars() {
		super.setBars();
		addBar("pressure", entity -> new Bar(Core.bundle.get("bar.pressure"), Pal.accent, () -> ((PressureBuild) entity).pressureModule().pressure));
	}

	@Override
	public void setStats() {
		stats.add(Stat.abilities, OblivionStatValues.pressurizedUnit(minPressure, maxPressure));
	}

	public class PressureBuild extends Building implements PressureBuilding {
		public PressureModule pressureMod;

		// making my life easier
		public float getMin() {
			return ((PressureBlock) block).minPressure;
		}
		public float getMax() {
			return ((PressureBlock) block).maxPressure;
		}

		@Override
		public void updateTile() {overPressure();}

		@Override
		public PressureModule pressureModule() {
			return pressureMod;
		}

		@Override
		public void addPressure(float pressure, @Nullable Building src) {pressureMod.pressure += pressure;}
		@Override
		public void subPressure(float pressure, @Nullable Building src) {pressureMod.pressure -= pressure;}
		@Override
		public void setPressure(float pressure, @Nullable Building src) {pressureMod.pressure = pressure;}

		@Override
		public boolean acceptsPressure(float pressure, Building src) {return acceptsPressure;}
		@Override
		public boolean outputsPressure(float pressure, Building src) {return outputsPressure;}

		@Override
		public void overPressure() {
			if (pressureModule().pressure < minPressure && pressureModule().pressure > maxPressure) kill();
		}

		@Override
		public void write(Writes write){
			super.write(write);
			write.f(pressureModule().pressure);	
		}

		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			setPressure(read.f());
		}
	}
}