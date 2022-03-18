package oblivion.world.blocks.defense;

import arc.util.*;
import arc.struct.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.entities.*;
import mindustry.world.meta.*;
import mindustry.world.consumers.*;
import mindustry.entities.bullet.*;
import mindustry.annotations.Annotations.*;
// basically a remake of pa's scatter silo with 
public class BulletSpreader extends Block {
	// misc
	public @Load("@-launcher") TextureRegion launcherRegion;
	// visual range for the block(will be set to the bullets'range)
	public float range;
	// reload of the block
	public float reloadTime;
	// shots and spacing stuff
	public int shots = 1;
	public float spread = 1f, offset = 0f;
	// on shoot stuff
	public Effect shootEffect = Fx.none;
	public Sound shootSound = Sounds.bigshot;
	public BulletType bullet = Bullets.standardCopper;

	public BulletSpreader(String name) {
		super(name);
		rotate = true;
	}

	@Override
	public void init() {
		range = bullet.lifetime/bullet.speed;
	}

	@Override
	public void setBars() {
		super.setBars();
		bars.add("reload", entity -> new Bar(
			"Reload",
			Pal.turretHeat,
			() -> ((BulletSpreaderBuild) entity).reloadf()
		));
	}

	@Override
	public TextureRegion[] icons() {
		return new TextureRegion[]{region, launcherRegion};
	}

	@Override
	public void drawPlace() {
		super.drawPlace();
		Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
	}

	@Override
	public void setStats() {
		super.setStats();
		stats.remove(Stat.itemCapacity);
		stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(consumes.get(ConsumeType.item).item, bullet)));
	}

	public class BulletSpreaderBuild extends Building {
		boolean shoot = false;
		float reload = 0f;

		// method for getting shoot pattern angle, intended to be used inside a for loop
		// i might transfer it into a separate Mathf class but only if this is used more on the src
		public float getAngle(int i, int amount, float spacing, float rotation) {
			return (spacing * i) - (spacing * amount/2) + (spacing/2) + rotation;
		}

		public float reloadf() {
			return reload/reloadTime;
		}

		public void shoot() {
			consume();
			reload = 0f;
			shootEffect.at(x, y);
			shootSound.at(x, y);
			for (int i = 0; i < shots; i++) {
				float dx = x + Angles.trnsx(getAngle(i, shots, spread, 0f), offset, 0);
				float dy = y + Angles.trnsy(getAngle(i, shots, spread, 0f), offset, 0);
				bullet.create(this, team, dx, dy, Angles.angle(x, y, dx, dy));
			}
		}

		@Override
		public void drawSelect() {
			super.drawSelect();
			Drawf.dashCircle(x, y, range, team.color);
			Lines.stroke(3f);
			for (int i = 0; i < shots; i++) {
				float dx = x + Angles.trnsx(getAngle(i, shots, spread, 0f), size * 8, 0);
				float dy = y + Angles.trnsy(getAngle(i, shots, spread, 0f), size * 8, 0);
				Lines.lineAngle(dx, dy, Angles.angle(x, y, dx, dy), 7);
			}
		}

		@Override
		public void buildConfiguration(Table table) {
			table.button(Icon.upload, b -> shoot = !shoot);
		}

		@Override
		public void updateTile(){
			if (shoot && cons.valid()) {
				reload += Time.delta;
				if (reload >= reloadTime) shoot();
			}
		}

		@Override
		public void draw() {
			Draw.rect(region, x, y, 0f);
			Draw.rect(launcherRegion, x, y, block.rotate ? rotDeg() : 0);
		}
	}
}