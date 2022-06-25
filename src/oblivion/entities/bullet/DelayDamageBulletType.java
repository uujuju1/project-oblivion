package oblivion.entities.bullet;

import arc.func.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;

public class DelayDamageBulletType extends BulletType {
	public Cons<Bullet> draw = b -> {};
	public float delayTime = 60f, damageRadius = 80f, extraDamage = 1f;

	public DelayDamageBulletType(float speed, float dmg) {
		super(speed, dmg);
		lifetime = delayTime * 2f;
	}

	@Override
	public void update(Bullet b) {
		if (b.time <= delayTime) {
			Damage.damage(b.team, b.x, b.y, damageRadius, extraDamage);
		}
	}

	@Override
	public void draw(Bullet b) {
		draw.get(b);
	}
}