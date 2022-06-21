package oblivion.entities.bullet;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.blocks.environment.*;
import mindustry.entities.Effect;

import static mindustry.Vars.tilesize;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class DelayDamageBulletType extends BulletType {
	public Cons<Bullet> draw = b -> {};
	public float delayTime = 60f, damageRadius = 80f, extraDamage = 1f;

	public DelayDamageBulletType(float speed, float dmg) {
		super(speed, dmg);
		collides = collidesAir = collidesGround = false;
		drag = 0.1f;
		lifetime = delayTime * 2f;
	}

	@Override
	public void update(Bullet b) {
		if (b.time >= delayTime) {
			Damage.damage(b.team, b.x, b.y, explodeRadius, explodeDamage);
		}
	}

	@Override
	public void draw(Bullet b) {
		draw.get(b);
	}
}