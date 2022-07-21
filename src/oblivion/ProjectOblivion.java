package oblivion;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.core.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.ui.fragments.*;
import mindustry.game.EventType.*;
import mindustry.ui.dialogs.SettingsMenuDialog.*;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.*;
import oblivion.content.*;
import oblivion.graphics.*;

public class ProjectOblivion extends Mod{

	public ProjectOblivion(){
		Log.info("Loaded ProjectOblivion constructor.");
		loadSettings();
		Events.on(ClientLoadEvent.class, e -> {
			if (Core.settings.getBool("oblivion-override-menu-renderer", true)) {
				try{
					Reflect.set(MenuFragment.class, Vars.ui.menufrag, "renderer", new OblivionMenuRenderer());
				}catch(Exception exep){
					Log.err("Failed to replace renderer", exep);
				}
			}
			
		});
	}

	public void loadSettings() {
		Ui.settings.graphics.checkpref("oblivion-override-menu-renderer", true);
	}

	@Override
	public void loadContent(){
		new OblivionStatuses().load();
		new OblivionResources().load();
		new OblivionUnits().load();
		new OblivionEnvironment().load();
		new OblivionBlocks().load();
		new OblivionSchematics().load();
		new OblivionPlanets().load();
		new OblivionSectors().load();
		new OblivionTechTree().load();
		new LamoniTechTree().load();
	}
}
