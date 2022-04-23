package oblivion.blocks.production;

import arc.func.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.blocks.production.*;
// a crafter that inutilizes drawers
public class DrawableCrafter extends GenericCrafter {
	public Cons<Building> draw;

	public DrawableCrafter(String name, Cons<Building> draw) {
		super(name);
		this.draw = draw;
	}
	public DrawableCrafter(String name) {
		super(name);
		this.draw = b -> {Draw.rect(region, b.x, b.y, rotate ? b.rotdeg() : 0f);};
	}

	public class DrawableCrafterBuild extends GenericCrafterBuild {
		@Override
		public void draw() {
			draw.get(this);
		}
	}
}