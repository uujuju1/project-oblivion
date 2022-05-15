package oblivion.content;

import mindustry.type.*;
import mindustry.ctype.*;
import oblivion.content.*;

public class OblivionSectors {
	public static SectorPreset
		newWorld, oldTown;

	public void load(){
		newWorld = new SectorPreset("newWorld", OblivionPlanets.lonela, 15) {{
			alwaysUnlocked = true;
			addStartingItems = true;
			captureWave = 10;
			difficulty = 1;
			startWaveTimeMultiplier = 3f;
		}};
		oldTown = new SectorPreset("oldTown", OblivionPlanets.lonela, 130) {{
			captureWave = 20;
			difficulty = 2;
			startWaveTimeMultiplier = 2f;
		}};
	}
}
