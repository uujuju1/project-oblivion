package oblivion.content;

import mindustry.*;
import mindustry.game.*;

public class OblivionSchematics {
	public static Schematic villageLaunch;

	public void load() {
		villageLaunch = Schematics.read(Vars.tree.get("schematics/" + name));
	}
}