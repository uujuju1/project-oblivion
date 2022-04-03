package oblivion.world.heat.sandbox;

import arc.*;
import arc.math.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import oblivion.world.heat.*;

public class HeatSource extends HeatBlock {
	public TextureRegion top;

	public HeatSource(String name) {
		super(name);
		acceptHeat = false;
		canExplode = false;
	}

	@Override
	public void load() {
		super.load();
		top = Core.atlas.find(name + "-top");
	}

	public class HeatSourceBuild extends HeatBlock.HeatBlockBuild {
		@Override
		public void updateTile() {
			setHeat(maxHeat, null);
			for (int i = 0; i < this.proximity.size; i++) {
				Building next = this.proximity.get(i);
				if (next instanceof HeatBlockBuild) {
					if (((HeatBlockBuild) next).recievesHeat(0f, this)) ((HeatBlockBuild) next).setHeat(((HeatBlockBuild) next).block.maxHeat, this);
				}
			}
		}

		@Override
		public void draw() {
			super.draw();
			Draw.color(heatColor);
			Draw.alpha(0.3f + Mathf.sin(Time.time/20f));
			Draw.rect(top, x, y, 0f);
		}
	}
}