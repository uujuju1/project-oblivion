package oblivion.blocks.util;

import arc.math.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
// simple block that holds a efficiency value
public class EfficiencyBlock extends Block {
	public float effCap = 1f;
	public float warmup = 0.06f;
	public float consumeTime = 6f;
	public Cons<EfficiencyBlockBuild> drawer = build -> {Draw.rect(region, build.x, build.y, rotate ? build.rotDeg() : 0)};

	public EfficiencyBlock(String name, float efficiency) {
		super(name);
		this.efficiency = efficiency;
		solid = destructible = true;
	}

	public class EfficiencyBlockBuild extends Building {
		public float updateEfficiency;
		public float consumeT;
		@Override
		public void updateTile() {
			if (efficiency > 0) {
				updateEfficiency = Mathf.approachDelta(updateEfficiency, effCap, warmup);
				consumeT += Time.delta;
				if (consumeT >= consumeTime) {
					consume();
					consumeT = 0f;
				}
			} else {
				updateEfficiency = Mathf.approachDelta(updateEfficiency, 0f, warmup);
			}
		}

		@Override
		public void draw() {
			drawer.get(this);
		}
	}
}