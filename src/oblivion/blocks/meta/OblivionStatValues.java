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
			  bt.add(new Image(status.uiIcon)).size(30);
			  bt.row();
			  bt.add(status.localizedName);
			  bt.row();
			  bt.add(Core.bundle.get("stat.duration") + ": " + duration/60 + " Seconds");
			});
		};
	}
}