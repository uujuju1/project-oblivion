package oblivion;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import oblivion.content.*;

public class ProjectOblivion extends Mod{

	public ProjectOblivion(){
		Log.info("Loaded ProjectOblivion constructor.");
		Events.on(ClientLoadEvent.class, e -> {
			Blocks.additiveReconstructor.upgrades.addAll(new UnitType[]{UnitType.slop, UnitType.deltaa});
			Blocks.multiplicativeReconstructor.upgrades.addAll(new UnitType[]{UnitType.delta, UnitType.tedri});
			Blocks.exponentialReconstructor.upgrades.addAll(new UnitType[]{UnitType.tedri, UnitType.taleni});
			Blocks.tetrativeReconstructor.upgrades.addAll(new UnitType[]{UnitType.taleni, UnitType.kolete});
		});
	}

	@Override
	public void loadContent(){
		new OblivionResources().load();
		new OblivionUnits().load();
		new OblivionBlocks().load();
		new OblivionPlanets().load();
		new OblivionSectors().load();
		new OblivionTechTree().load();
	}
}
