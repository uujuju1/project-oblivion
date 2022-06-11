package oblivion.blocks.pressure;

import arc.util.io.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.world.*;
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
		addBar("pressure", entity -> new Bar(Core.bundle.get("bar.pressure"), Pal.accent, PressureBuild::pressureModule.pressure));
	}

	@Override
	public void setStats() {
		stats.add(Stat.abilities, OblivionStatValues.pressurizedUnit(minPressure, maxPressure));
	}

	public class PressureBuild extends Building implements PressureBuilding {
		public PressureModule pressure;

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
			return pressure;
		}

		@Override
		public void addPressure(float presure, @Nullable Building src) {pressure.pressure += pressure;}
		@Override
		public void subPressure(float presure, @Nullable Building src) {pressure.pressure -= pressure;}
		@Override
		public void setPressure(float presure, @Nullable Building src) {pressure.pressure = pressure;}

		@Override
		public void acceptsPressure(float presure, Building src) {return acceptsPressure;}
		@Override
		public void outputsPressure(float presure, Building src) {return outputsPressure;}

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
			setPressure(read.f);
		}
	}
}