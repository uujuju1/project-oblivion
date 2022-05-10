package oblivion.blocks.production;

import arc.*;
import arc.util.*;
import arc.scene.ui.layout.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.production.*;
import oblivion.blocks.meta.*;
// like abyss's 
public class ManualDrill extends Drill {
	public float holdTime = 60f, decayTime = 60f;

	public ManualDrill(String name) {
		super(name);
	}

	@Override
	public void setBars() {
		super.setBars();
		bars.add("holdTime", entity -> new Bar(Core.bundle.get("stat.hold"), Pal.accent, () -> ((ManualDrillBuild) entity).hold/holdTime));
		bars.add("decayTime", entity -> new Bar(Core.bundle.get("stat.decay"), Pal.accent, () -> ((ManualDrillBuild) entity).decay/decayTime));
	}

	@Override
	public void setStats() {
		super.setStats();
		stats.add(Stat.abilities, OblivionStatValues.chargeDrill(holdTime, decayTime));
	}

	public class ManualDrillBuild extends DrillBuild {
		public float hold = 0f, decay = 0f;

		@Override
		public float efficiency() {
			return decay/decayTime;
		}

		@Override
		public void buildConfiguration(Table table) {{
			table.button(Icon.settings, () -> {
				if (hold <= 0f) {
					hold = holdTime;
					decay = decayTime;
				}
			});
		}}

		// @Override
		// public void updateTile() {
		// 	super.update();
		// 	if (hold >= 0f) {
		// 		hold -= Time.delta;
		// 	} else {
		// 		decay -= Time.delta;
		// 	}
		// }
	}
}