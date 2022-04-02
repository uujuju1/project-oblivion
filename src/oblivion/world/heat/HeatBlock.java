package oblivion.world.heat;

import arc.util.*;
import arc.graphics.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.graphics.*;

public class HeatBlock extends Block {
	public boolean acceptHeat = true, outputHeat = true, canExplode = false;
	public float minHeat = 0f, maxHeat = 200f; 

	public Color heatColor = Pal.turretHeat;

	public HeatBlock(String name) {
		super(name);
		solid = destructible = sync = true;
	}

	@Override
	public void setBars() {
		super.setBars();
		bars.add("heat", entity -> new Bar("Heat", heatColor, () -> heat/maxHeat));
	}

	public class HeatBlockBuild extends Building implements HeatComp {
		public HeatModule heat;

		@Override
		public HeatModule heatModule() {
			return heat;
		}

		@Override
		public boolean recievesHeat(float heat, @Nullable Building source) {
			return acceptHeat && source instanceof HeatBlockBuild;
		}
		@Override
		public boolean outputsHeat(float heat, @Nullable Building source) {
			return outputHeat && source instanceof HeatBlockBuild;
		}

		@Override
		public void overheat() {
			if (canExplode && heat.heat > maxHeat) kill();
		}

		@Override
		public void updateTile() {overheat();}
	}
}