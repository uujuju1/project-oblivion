package oblivion.graphics;

import arc.*;
import arc.graphics.Blending;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class OblivionMenuRenderer extends MenuRenderer{
	// draw a simple image in the bg
	@Override
	public void render(){
		Draw.rect(Core.atlas.find("oblivion-bg"), Core.graphics.getWidth(), Core.graphics.getHeight());
	}
}
