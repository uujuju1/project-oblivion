package oblivion.content;

import mindustry.*;
import mindustry.game.*;

public class oblivionSchematics {
	public Schematic loadSchematic(String schemName) {
		String name = "Schematics/" + schemName;
    String path = name + ".msch";

    return Schematics.loadFile(Vars.tree.get(path));
	}
	public static Schematic villageLaunch = loadSchematic("villageLaunch");
}