package oblivion.blocks.production;

import arc.*;
import arc.func.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.world.blocks.production.*;
// a crafter that inutilizes drawers
public class DrawableCrafter extends GenericCrafter {
	public Cons<DrawableCrafterBuild> draw;
	public DrawableCrafter(String name, Cons<DrawableCrafterBuild> draw) {
		super(name);
		this.draw = draw;
	}
	public DrawableCrafter(String name) {
		super(name);
		this.draw = b -> {Draw.rect(region, b.x, b.y, rotate ? b.rotdeg() : 0f);};
	}

	@Override
	public TextureRegion[] icons() {
		return new TextureRegion[]{Core.atlas.find(name + "-full", name)};
	}

	public class DrawableCrafterBuild extends GenericCrafterBuild {
		@Override
		public void draw() {
			draw.get(this);
		}
	}
}