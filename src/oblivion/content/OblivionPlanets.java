package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import oblivion.planets.*;

public class OblivionPlanets implements ContentList {
	public static Planet lonela;

	@Override
	public void load() {
		lonela = new Planet("lonela", Planets.sun, 1.5f, 3) {{
			generator = new LonelaPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			cloudMeshLoader = () -> new MultiMesh(
				new HexSkyMesh(this, 69, 0.1f, 0.2f, 5, Color.white.cpy().a(0.75f), 2, 0.45f, 1.1f, 0.45f),
				new HexSkyMesh(this, 69, 0.1f, 0.2f, 5, Color.white.cpy().a(0.50f), 2, 0.45f, 1.1f, 0.45f)
			);
			atmosphereColor = Color.valueOf("7f7f7f");
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			startSector = 15;
			alwaysUnlocked = true;
			landCloudColor = Color.white.cpy().a(0.5f);
		}};
	}
}