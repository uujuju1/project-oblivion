package oblivion.world.heat.distribution;

import arc.*;
import arc.util.*;
import arc.math.*;
import arc.graphics.g2d.*;
import oblivion.world.heat.*;
import oblivion.world.heat.HeatBlock.*;

public class HeatConveyor extends HeatBlock {
	public TextureRegion baseRegion, heatRegion, closedRegion, topRegion;

	public float conductivityScl = 1f, cooldownScl = 0.001f;

	public HeatConveyor(String name) {
		super(name);
		minHeat = 15f;
		maxHeat = 2500f;
	}

	@Override
	public void load() {
		super.load();
		topRegion = Core.atlas.find(name + "-top");
		baseRegion = Core.atlas.find(name + "-base");
		heatRegion = Core.atlas.find(name + "-heat");
		closedRegion = Core.atlas.find(name + "-closed");
	}

	public class HeatConveyorBuild extends HeatBlockBuild {

		@Override
		public void updateTile() {
			if (front() instanceof HeatBlockBuild) {
				if (((HeatBlockBuild) front()).recievesHeat(heat.heat * conductivityScl, this)) {
					((HeatBlockBuild) front()).addHeat(heat.heat * conductivityScl, this);
					subHeat(heat.heat * conductivityScl, this);
				}
			}
			subHeat(heat.heat * cooldownScl, this);
		}

		@Override
		public void draw() {
			Draw.rect(base, x, y, 0f);
			Draw.color(heatColor);
			Draw.alpha(0.3f + Mathf.sin(Time.time/20f));
			Draw.rect(heatRegion, x, y, 0f);
			super.draw();
		}

		public void drawConnections() {
			if (front() instanceof HeatBlockBuild) if (((HeatBlockBuild) front()).recievesHeat(heat.heat * conductivityScl, this)) else Draw.rect(closedRegion, x, y, block.rotate ? rotDeg() : 0f);
			if (right() instanceof HeatBlockBuild) if (((HeatBlockBuild) right()).recievesHeat(heat.heat * conductivityScl, this)) else Draw.rect(closedRegion, x, y, block.rotate ? rotDeg() + 90 : 90f);
			if (left() instanceof HeatBlockBuild) if (((HeatBlockBuild) left()).recievesHeat(heat.heat * conductivityScl, this)) else Draw.rect(closedRegion, x, y, block.rotate ? rotDeg() + -90 : -90f);
			if (back() instanceof HeatBlockBuild) if (((HeatBlockBuild) back()).recievesHeat(heat.heat * conductivityScl, this)) else Draw.rect(closedRegion, x, y, block.rotate ? rotDeg() + 180 : 180f);
		}
	}
}