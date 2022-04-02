package oblivion;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.type.*;
import mindustry.ui.dialogs.*;
import mindustry.world.blocks.units.Reconstructor.*;
import oblivion.content.*;

public class ProjectOblivion extends Mod{

	public ProjectOblivion(){
		Log.info("Loaded ProjectOblivion constructor.");
		// Events.on(ClientLoadEvent.class, e -> {
		// 	((ReconstructorBuild) Blocks.additiveReconstructor).upgrades.addAll(new UnitType[]{OblivionUnits.slop, OblivionUnits.detra});
		// 	((ReconstructorBuild) Blocks.multiplicativeReconstructor).upgrades.addAll(new UnitType[]{OblivionUnits.detra, OblivionUnits.tedri});
		// 	((ReconstructorBuild) Blocks.exponentialReconstructor.upgrades).addAll(new UnitType[]{OblivionUnits.tedri, OblivionUnits.taleni});
		// 	((ReconstructorBuild) Blocks.tetrativeReconstructor.upgrades).addAll(new UnitType[]{OblivionUnits.taleni, OblivionUnits.kolete});
		// });
	}

	@Override
	public void loadContent(){
		new OblivionStatuses().load();
		new OblivionResources().load();
		new OblivionUnits().load();
		new OblivionBlocks().load();
		new OblivionPlanets().load();
		new OblivionSectors().load();
		new OblivionTechTree().load();
	}
}
