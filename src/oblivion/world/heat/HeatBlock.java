package oblivion.world.heat;

import mindustry.gen.*;
import mindustry.world.*;

public class HeatBlock extends Block {
	public boolean acceptHeat = true, outputHeat = true;
	public float minHeat = 0f, maxHeat = 200f; 

	public HeatBlock(String name) {
		super(name);
		solid = destructible = sync = true;
	} 

	public class HeatBlockBuild extends Building implements HeatComp {
		public HeatModule heat;

		@Override
		public HeatModule heatModule() {
			return heat;
		}

		@Override
		public boolean acceptHeat(float heat, Building source) {
			return acceptHeat ? false : source instanceof HeatBlockBuild;
		}
		@Override
		public boolean outputsHeat(float heat, Building to) {
			return outputHeat ? false : to instanceof HeatBlockBuild;
		}

		@Override
		public void updateTile() {
			setHeat(Mathf.sin(Time.time) * 5, null);
		}

		@Override
		public void draw() {
			super.draw();
			Draw.rect(Blocks.router.region, x, y + Mathf.sin(Time.time) * 5, 0f);
		}
	}
}