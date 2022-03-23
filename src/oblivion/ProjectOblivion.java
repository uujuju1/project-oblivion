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
	}

	@Override
	public void loadContent(){
		new OblivionResources().load();
		new OblivionUnits().load();
		new OblivionBlocks().load();
		new OblivionTechTree().load();
	}
}
