package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import oblivion.planets.*;

public class OblivionPlanets implements ContentList {
	public static Planet 
	berenit,
	lamoni, lonela;

	@Override
	public void load() {
		berenit = new Planet("benerit", Planets.sun, 0, 3f) {{
			bloom = true;
			accessible = false;
			orbitRadius = 750f;
			meshLoader = () -> new SunMesh(
				this, 6,
				5, 0.3, 1.7, 1.2, 1,
				1.1f,
				Color.valueOf("FF4D0C"),
				Color.valueOf("FF6625"),
				Color.valueOf("FF8948"),
				Color.valueOf("FFA564"),
				Color.valueOf("FFBC7B"),
				Color.valueOf("FFE3A2")
			);
		}};
		lonela = new Planet("lonela", Planets.sun, 3, 1f) {{
			generator = new LonelaPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			cloudMeshLoader = () -> new MultiMesh(
				new HexSkyMesh(this, 69, 0.1f, 0.16f, 5, Color.white.cpy().a(0.75f), 2, 0.45f, 1.1f, 0.45f),
				new HexSkyMesh(this, 420, 0.1f, 0.13f, 5, Color.gray.cpy().a(0.75f), 2, 0.45f, 1.1f, 0.45f)
			);
			atmosphereColor = Color.valueOf("ffffff");
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			startSector = 15;
			accessible = true;
			alwaysUnlocked = true;
			landCloudColor = Color.white.cpy().a(0.5f);
		}};
	}
}