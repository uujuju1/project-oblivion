package oblivion;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ui.fragments.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.type.*;
import mindustry.ui.dialogs.*;
import oblivion.content.*;
import oblivion.graphics.*;

public class ProjectOblivion extends Mod{

	public ProjectOblivion(){
		Log.info("Loaded ProjectOblivion constructor.");
		Events.on(ClientLoadEvent.class, e -> {
			try{
				Reflect.set(MenuFragment.class, Vars.ui.menufrag, "renderer", new OblivionMenuRenderer());
			}catch(Exception exep){
				Log.err("Failed to replace renderer", exep);
			}
		});
	}

	@Override
	public void loadContent(){
		new OblivionStatuses().load();
		new OblivionResources().load();
		new OblivionUnits().load();
		new OblivionEnvironment().load();
		new OblivionBlocks().load();
		new OblivionPlanets().load();
		new OblivionSectors().load();
		new OblivionTechTree().load();
		new LamoniTechtree().load();
	}
}
