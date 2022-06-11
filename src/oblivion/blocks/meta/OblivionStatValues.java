package oblivion.blocks.meta;

import arc.*;
import arc.scene.ui.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.meta.*;
// custom ui moment
public class OblivionStatValues {

	public static StatValue displayStatus(StatusEffect status, float duration) {
		return table -> {
			table.row();
			table.table(Tex.underline, bt -> {
			  bt.add(new Image(status.uiIcon)).size(30f);
			  bt.row();
			  bt.add(status.localizedName);
			  bt.row();
			  bt.add(Core.bundle.get("stat.duration") + ": " + duration/60f + " " + Core.bundle.get("unit.seconds"));
			});
		};
	}

	public static StatValue chargeDrill(float hold, float decay) {
		return table -> {
			table.row();
			table.add(new Image(Icon.settings)).size(30f);
			table.row();
			table.table(Tex.underline, bt -> {
				bt.add(Core.bundle.get("stat.manualDrill"));
			});
			table.row();
			table.table(bt -> {
				bt.add(Core.bundle.get("stat.hold") + ": " + hold/60f + " " + Core.bundle.get("unit.seconds"));
				bt.row();
				bt.add(Core.bundle.get("stat.decay") + ": " + decay/60f + " " + Core.bundle.get("unit.seconds"));
			});
		};
	}

	public static StatValue pressurizedUnit(float min, float max) {
		return table -> {
			table.table(Tex.underline, b -> {
				b.table(Tex.underline, c -> {
					c.add(new Image(Core.atlas.find("oblivion-pressure-icon"))).size(30).row();
					c.add(Core.bundle.get("stat.pressure"));
				}).center().row();
				b.add(Core.bundle.get("stat.minpressure" + ": " + min)).row();
				b.add(Core.bundle.get("stat.maxpressure" + ": " + max));
			});
		};
	}
}