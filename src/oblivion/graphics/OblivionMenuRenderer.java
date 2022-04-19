package oblivion.graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class OblivionMenuRenderer extends MenuRenderer{
	@Override
	public void render(){
		Draw.rect(Core.atlas.find("oblivion-bg"), Core.graphics.getWidth()/2, Core.graphics.getHeight()/2);
	}
}
