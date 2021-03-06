package oblivion.blocks.defense;

import arc.*;
import arc.util.*;
import arc.util.io.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.ui.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import oblivion.blocks.meta.*;
// aka status field block with funky lore
public class StatusBomb extends Block {
	public StatusEffect status;
	public float statusDuration = 60f;
	public Effect craftEffect = Fx.none, shootEffect = Fx.none;
	public TextureRegion rotator;
	public float range = 80f, craftTime = 60f, cooldownTime = 60f;
	public int bombCapacity = 10;

	public StatusBomb(String name) {
		super(name);
		solid = destructible = true;
		sync = update = true;
		configurable = true;
	}

	@Override
	public TextureRegion[] icons() {
		return new TextureRegion[]{region, rotator};
	}

	@Override
	public void setBars() {
		super.setBars();
		addBar("charge", entity -> new Bar(Core.bundle.get("stat.reload"), Color.valueOf("E6875C"), () -> ((StatusBombBuild) entity).reload/cooldownTime));
		addBar("craftCharge", entity -> new Bar(Core.bundle.get("stat.productiontime"), Color.valueOf("FFA665"), () -> ((StatusBombBuild) entity).craftReload/craftTime));
		addBar("capacity", entity -> new Bar(Core.bundle.get("stat.ammo"), Pal.lancerLaser, () -> ((float) ((StatusBombBuild) entity).shots/bombCapacity)));
	}

	@Override
	public void load() {
		super.load();
		rotator = Core.atlas.find(name + "-rotator");
	}

	@Override
	public void setStats() {
		super.setStats();
		stats.add(Stat.range, range/8, StatUnit.blocks);
		stats.add(Stat.productionTime, craftTime/60, StatUnit.seconds);
		stats.add(Stat.abilities, OblivionStatValues.displayStatus(status, statusDuration));
	}

	public class StatusBombBuild extends Building {
		public int shots = 0;
		public float reload = 0f, craftReload = 0f;

		@Override
		public void buildConfiguration(Table table) {
			table.button(Icon.upload, () -> {shoot();});
		}

		@Override
		public void updateTile() {
			if (efficiency > 0f && shots < bombCapacity) {
				craftReload += Time.delta;
				if (craftReload >= craftTime) {
					craftEffect.at(x, y);
					craftReload = 0f;
					consume();
					shots++;
				}
			}
			if (reload >= 0) reload -= Time.delta;
		}

		@Override
		public void draw() {
			super.draw();
			Draw.rect(rotator, x, y, Time.time);
		}

		public void shoot() {
			if (shots > 0 && reload <= 0) {
				shootEffect.at(x, y);
				Damage.status(team, x, y, range, status, statusDuration, true, true);
				reload = cooldownTime;
				shots--;
			}
		}

		@Override
		public void write(Writes write){
			super.write(write);
			write.f(reload);
			write.f(craftReload);
			write.i(shots);
			
		}

		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			reload = read.f();
			craftReload = read.f();
			shots = read.i();
		}
	}
}