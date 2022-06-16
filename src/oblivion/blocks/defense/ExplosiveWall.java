package oblivion.blocks.defense;

import mindustry.entities.*;
import mindustry.world.blocks.defense.*;

public class ExplosiveWall extends Wall {
	public float damage = 1f, range = 80f;
	public float explosiveness = 0f, flammability = 0f;
	public boolean damages = true, combusts = true;

	public ExplosiveWall(String name) {
		super(name);
	}

	public class ExplosiveWallBuild extends WallBuild {
		@Override
		public void onDestroyed() {
			Damage.dynamicExplosion(x, y, exlosiveness, flammability, damage, range, damages, combusts, team, destroyEffect);
		}
	}
}