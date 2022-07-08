package oblivion.content;

import mindustry.type.*;
import mindustry.ctype.*;
import oblivion.content.*;

public class OblivionSectors {
	public static SectorPreset
		newWorld, brokenOutpost,

		// lamoni
		freshBeggining, reactiveMonolith/*,
		sodaicOutpost, rangedDefender, newCraters,
		erasedPath, forgottenHighway*/;

	public void load(){
		newWorld = new SectorPreset("newWorld", OblivionPlanets.lonela, 15) {{
			alwaysUnlocked = true;
			addStartingItems = true;
			captureWave = 10;
			difficulty = 1;
			startWaveTimeMultiplier = 3f;
		}};
		brokenOutpost = new SectorPreset("brokenOutpost", OblivionPlanets.lonela, 35) {{
			captureWave = 15;
			difficulty = 2;
			startWaveTimeMultiplier = 2f;
		}};

		// lamoni
		freshBeggining = new SectorPreset("FreshBeggining", OblivionPlanets.lamoni, 0) {{
			alwaysUnlocked = true;
			captureWave = 10;
			difficulty = 1;
			startWaveTimeMultiplier = 2f;
		}};
		reactiveMonolith = new SectorPreset("ReactiveMonolith", OblivionPlanets.lamoni, 54) {{
			captureWave = 20;
			difficulty = 1;
			startWaveTimeMultiplier = 2f;
		}};
	}
}
