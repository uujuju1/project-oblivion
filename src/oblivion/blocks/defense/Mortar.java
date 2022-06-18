package oblivion.blocks.defense;

import arc.util.*;
import arc.func.*;
import arc.math.geom.*;
import mindustry.gen.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;

public class Mortar extends Block {
	public float range = 80f, reload = 60f;
	public boolean targetsAir = true, targetsGround = true;
	public Boolf<Unit> unitFilter = u -> true;
	public Boolf<Building> buildingFilter = b -> !b.block.underBullets;
	public Sortf unitSort = UnitSorts.closest;
	public BulletType bullet = Bullets.placeholder;
	public ShootPattern shoot = new ShootPattern();

	public Mortar(String name) {
		super(name);
		solid = destructible = true;
		update = sync = true;
	}

	public class MortarBuild extends Building implements Ranged {
		float time = 0f, angle = 0f;
		@Nullable Posc target;
		public int totalShots;
		public Vec2 shootPos;

		@Override
		public float range() {return range;}

		@Override
		public void updateTile() {
			if (efficiency > 0f) {
				time += Time.delta * efficiency;
				if (time > reload) {
					time = 0f;
					shoot.shoot(totalShots, (offsetx, offsety, angle, delay, mover) -> {
						shoot(bullet, angle, mover);
						totalShots++;
					});	
				}
			}
		}

		public void shoot(BulletType bullet, float angle, Mover mover) {
			findTarget();
			if (target != null) {
				bullet.create(this, team, x, y, angle, -1f, 1f, 1f, null, mover, x, y);
			}
		}

		public void findTarget() {
			if(targetsAir && !targetsGround){
				target = Units.bestEnemy(team, x, y, range, e -> !e.dead() && !e.isGrounded() && unitFilter.get(e), unitSort);
			}else{
				target = Units.bestTarget(team, x, y, range, e -> !e.dead() && unitFilter.get(e) && (e.isGrounded() || targetsAir) && (!e.isGrounded() || targetsGround), b -> targetsGround && buildingFilter.get(b), unitSort);
      }

      if (target != null) {
      	shootPos.set(target);
      } else {
      	shootPos.setZero();
      }
		}
	}
}