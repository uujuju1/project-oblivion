package oblivion.blocks.production;

import arc.func.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.blocks.production.*;
// a crafter that inutilizes drawers
public class DrawableCrafter extends GenericCrafter {
	public Cons<Building> draw = b -> {Draw.rect(region, b.x, b.y, build.rotate ? build.rotdeg() : 0f);};

	public class DrawableCrafterBuild extends GenericCrafterBuild {
		@Override
		public void draw() {
			draw.get(this);
		}
	}
}