package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import oblivion.planets.*;

public class OblivionPlanets {
	public static Planet 
	berenit,
	lamoni, lonela, vita, test;

	public void load() {
		berenit = new Planet("berenit", Planets.sun, 3f, 0) {{
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

		lamoni = new Planet("lamoni", berenit, 1f, 3) {{
			generator = new LamoniPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			cloudMeshLoader = () -> new MultiMesh(
				new HexSkyMesh(this, 6, 0.1f, 0.28f, 5, Color.valueOf("F7E8C1").a(0.75f), 2, 0.45f, 1.13f, 0.45f),
				new HexSkyMesh(this, 3, 0.2f, 0.23f, 5, Color.valueOf("E3DAC3").a(0.65f), 3, 0.25f, 1.22f, 0.45f),
				new HexSkyMesh(this, 2, 0.3f, 0.32f, 5, Color.valueOf("F0CE86").a(0.55f), 4, 0.35f, 1.35f, 0.45f)
			);
			hiddenItems.addAll(Items.serpuloItems).addAll(Items.erekirItems).removeAll(OblivionResources.lamoniItems);
			atmosphereColor = Color.valueOf("F0B73C");
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			solarSystem = berenit;
			startSector = 0;
			accessible = true;
			alwaysUnlocked = true;
			ruleSetter = r -> {
				r.fog = true;
			};
		}};
		vita = new Planet("vita", berenit, 1f, 2) {{
			generator = new TestPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 5);
			hiddenItems.addAll(Items.serpuloItems).addAll(Items.erekirItems).removeAll(OblivionResources.lamoniItems);
			atmosphereColor = Color.valueOf("8FD595");
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			solarSystem = berenit;
			startSector = 0;
			accessible = true;
			alwaysUnlocked = true;
			ruleSetter = r -> {
				r.fog = true;
				r.lighting = true;
				r.ambientLight = new Color(0.1f, 0.4f, 0.1f, 0.5f);	
			};
		}};

		lonela = new Planet("lonela", Planets.sun, 1f, 3) {{
			generator = new LonelaPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			cloudMeshLoader = () -> new MultiMesh(
				new HexSkyMesh(this, 69, 0.1f, 0.16f, 5, Color.white.cpy().a(0.75f), 2, 0.45f, 1.1f, 0.45f),
				new HexSkyMesh(this, 420, 0.1f, 0.13f, 5, Color.gray.cpy().a(0.75f), 2, 0.45f, 1.1f, 0.45f)
			);
			atmosphereColor = Color.valueOf("FFFFFF");
			atmosphereRadIn = 0.02f;
			atmosphereRadOut = 0.3f;
			startSector = 15;
			accessible = true;
			alwaysUnlocked = true;
			landCloudColor = Color.white.cpy().a(0.5f);
		}};
		test = new Planet("test", Planets.sun, 1f, 3) {{
			generator = new TestPlanetGenerator();
			meshLoader = () -> new HexMesh(this, 6);
			accessible = true;
			alwaysUnlocked = true;
		}};
	}
}
