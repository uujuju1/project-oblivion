package oblivion.blocks.defense;

import arc.*;
import arc.util.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import oblivion.blocks.meta.*;
// aka status field block with funky lore
public class SigilExecuter extends Block {
	public StatusEffect status;
	public Effect preChargeEffect = Fx.none, posChargeEffect = Fx.none;
	public TextureRegion icon;
	public float range = 80f, chargeTime = 60f, statusDuration = 60f;

	public SigilExecuter(String name) {
		super(name);
		solid = destructible = true;
		sync = update = true;
		configurable = true;
	}

	@Override
	public void setBars() {
		super.setBars();
		bars.add("charge", entity -> new Bar(Core.bundle.get("bar.charge"), Pal.accent, () entity.reload/chargeTime));
	}

	@Override
	public void load() {
		super.load();
		icon = Core.atlas.find(name + "-icon");
	}

	@Override
	public void setStats() {
		super.setStats();
		stats.add(Stat.range, range/8, StatUnit.blocks);
		stats.add(Stat.launchTime, chargeTime/60, StatUnit.seconds);
		stats.add(Stat.abilities, OblivionStatValues.sigilStatus(status, statusDuration));
	}

	public class SigilExecuterBuild extends Building {
		public boolean shoots = false;
		public float reload = 0f;

		@Override
		public void buildConfiguration(Table table) {
			table.button(Icon.upload, () -> {
				if (!shoots) {
					preChargeEffect.at(x, y);
				}
				shoots = true;
			});
		}

		@Override
		public void updateTile() {
			if (shoots) {
				reload += Time.delta;
				if (reload >= chargeTime) {
					posChargeEffect.at(x, y);
					reload = 0f;
					shoots = false;
					Damage.status(team, x, y, range, status, statusDuration, true, true);
				}
			}
		}

		@Override
		public void draw() {
			super.draw();
			Draw.rect(icon, x, y, Time.time);
		}
	}
}