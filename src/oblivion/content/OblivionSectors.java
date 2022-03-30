package mindustry.content;

import mindustry.type.*;
import mindustry.ctype.*;

import static mindustry.content.Planets.*;

public class SectorPresets implements ContentList{
	public static SectorPreset
		newWorld, oldTown;

	@Override
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
