package oblivion.graphics;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.graphics.*;

import static mindustry.Vars.*;

public class OblivionMenuRenderer extends MenuRenderer{
	private Camera camera = new Camera();
	// draw a simple image in the bg
	@Override
	public void render(){
		camera.position.set(0f, 0f);
		Draw.rect(Core.atlas.find("oblivion-bg"), 0f, 0f);
	}
}
