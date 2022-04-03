package oblivion.world.heat.distribution;

import arc.*;
import arc.graphics.g2d.*;
import oblivion.world.heat.*;
import oblivion.world.heat.HeatBlock.*;

public class HeatConveyor extends HeatBlock {
	public TextureRegion base, heat, open, closed;

	public float conductivityScl = 1f;

	public HeatConveyor(String name) {
		super(name);
		minHeat = 15f;
		maxHeat = 2500f;
	}

	@Override
	public void load() {
		base = Core.atlas.find(name + "-base");
		heat = Core.atlas.find(name + "-heat");
		open = Core.atlas.find(name + "-open");
		closed = Core.atlas.find(name + "-closed");
	}

	public class HeatConveyorBuild extends HeatBlockBuild {

		@Override
		public void updateTile() {
			if (front() instanceof HeatBlockBuild) {
				if (((HeatBlockBuild) front()).recievesHeat(heat.heat * conductivityScl, this)) {
					((HeatBlockBuild) front()).addHeat(heat.heat * conductivityScl, this);
				}
			}
		}

		@Override
		public void draw() {
			Draw.rect(base, x, y, 0f);
			Draw.color(heatColor);
			Draw.alpha(0.3f + Mathf.sin(Time.time/20f));
			Draw.rect(heat, x, y, 0f);
			super.draw();
		}
	}
}