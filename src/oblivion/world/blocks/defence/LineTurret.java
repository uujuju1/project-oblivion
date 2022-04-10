package oblivion.world.blocks.defence;

import arc.*;
import arc.util.*;
import arc.struct.*;
import arc.graphics.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.world.meta.*;
import mindustry.entities.bullet.*;

public class LineTurret extends Block {
	public TextureRegion baseRegion;
	public BulletType bullet = Bullets.standardCopper;
	public float reloadTime = 60f;

	public LineTurret(String name) {
		super(name);
		update = true;
		solid = true;
		rotate = true;
		outlineIcon = true;
		hasItems = true;
		priority = TargetPriority.turret;
		group = BlockGroup.turrets;
		flags = EnumSet.of(BlockFlag.turret);
		updateInUnits = false;
	}

	@Override
	public void load() {
		super.load();
		baseRegion = Core.atlas.find(name + "-base");
	}

	@Override
	public void setStats() {
		super.setStats();

		stats.add(Stat.reload, 60f / reloadTime, StatUnit.seconds);
		stats.add(Stat.ammo, StatValues.ammo(ammoTypes));
	}

	public class LineTurretBuild extends Building {
		boolean shoots = false;
		float reload = 0f;
		
		public void shoot() {
			if (!shoots) = return;
			if (reload <= 0) {
				bullet.create(this, x, y, block.rotate ? rotdeg() : 0f);
				reload = reloadTime;
			}
		}

		@Override
		public void buildConfiguration(Table table) {
			table.button(Icon.refresh, () -> shoots = !shoots);
			table.image(Core.atlas.find("oblivion-omnipage"))
		}

		@Override
		public void updateTile() {
			reload -= Time.delta;
			shoot();
		}
	}
}