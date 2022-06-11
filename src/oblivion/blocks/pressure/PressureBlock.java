package oblivion.blocks.pressure;

import arc.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import oblivion.blocks.meta.*;

public class PressureBlock extends Block {
	public TextureRegion pressureRegion;
	public boolean acceptsPressure = false, outputsPressure = false;
	public float minPressure = -35f, maxPressure = 35f;
	public float pressureLeakPercent = 0.01f, pressureFlowPercent = 0.01f;

	public PressureBlock(String name) {
		super(name);
		solid = destructible = sync = update = true;
	}

	@Override
	public void load() {
		super.load();
		pressureRegion = Core.atlas.find(name + "-pressure");
	}

	@Override
	public void setBars() {
		super.setBars();
		addBar("pressure", entity -> new Bar(Core.bundle.get("bar.pressure"), Pal.accent, () -> ((PressureBuild) entity).getPercentage()));
	}

	@Override
	public void setStats() {
		stats.add(Stat.abilities, OblivionStatValues.pressurizedUnit(minPressure, maxPressure));
	}

	public class PressureBuild extends Building implements PressureBuilding {
		public PressureModule pressureMod = new PressureModule();

		// making my life easier
		public float getMin() {
			return ((PressureBlock) block).minPressure;
		}
		public float getMax() {
			return ((PressureBlock) block).maxPressure;
		}
		public float getPercentage() {
			return pressureMod().pressure/maxPressure;
		}

		@Override
		public void updateTile() {
			overPressure();
			if (outputsPressure()) {
				for (int i = 0; i < this.proximity.size; i++) {
					Building next = this.proximity.size;
					if (((PressureBuild) next).acceptsPressure()) {
						((PressureBuild) next).addPressure(pressureModule().pressure * pressureFlowPercent, this);
						((PressureBuild) next).subPressure(pressureModule().pressure * pressureFlowPercent, this);
					}
				}
			}
			subPressure(pressureModule().pressure * pressureLeakPercent, this);
		}

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
		public void drawPressure(Color color) {
			Draw.alpha((0.25f + Mathf.absin(1f, 0.5f)) * getPercentage());
			Draw.color(color);
			Draw.rect(presusreRegion, x, y, rotate ? rotdeg() : 0f);
			Draw.reset();
		}

		@Override
		public void write(Writes write){
			super.write(write);
			write.f(pressureModule().pressure);	
		}

		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			setPressure(read.f(), this);
		}
	}
}