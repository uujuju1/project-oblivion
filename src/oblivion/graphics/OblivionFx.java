package oblivion.graphics;

import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.graphics.*;
import mindustry.entities.*;

public class OblivionFx {
	public static final Effect 
		calciteSmelt = new Effect(30f, e -> {
			float offset = 6;
			Lines.stroke(e.fout());
			Angles.randLenVectors(e.id, 5, 30 * e.finpow(), 0, 10, (x, y) -> {
			  Fill.circle(e.x + x + offset, e.y + y, 2 * e.fout());
			});
			Angles.randLenVectors(e.id + 1, 5, 30 * e.finpow(), 90, 10, (x, y) -> {
			  Fill.circle(e.x + x, e.y + y + offset, 2 * e.fout());
			});
			Angles.randLenVectors(e.id + 2, 5, 30 * e.finpow(), 180, 10, (x, y) -> {
			  Fill.circle(e.x + x - offset, e.y + y, 2 * e.fout());
			});
			Angles.randLenVectors(e.id + 3, 5, 30 * e.finpow(), -90, 10, (x, y) -> {
			  Fill.circle(e.x + x, e.y + y - offset, 2 * e.fout());
			});
			Angles.randLenVectors(e.id, 5, 10 * e.finpow(), (x, y) -> {
			  Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 3 * e.fout());
			});
		}),
		poisonShoot = new Effect(45f, e -> {
			Draw.color(Color.valueOf("72C76F"));
			Drawf.tri(e.x, e.y, 8 * e.fout(), 12 * e.foutpow(), 0 + e.rotation);
			Drawf.tri(e.x, e.y, 6 * e.fout(), 12 * e.foutpow(), 135 + e.rotation);
			Drawf.tri(e.x, e.y, 6 * e.fout(), 12 * e.foutpow(), -135 + e.rotation);
			
			Angles.randLenVectors(e.id, 20, e.finpow() * 80, 0 + e.rotation, 10, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, e.fout());
			});
			Draw.color(Color.valueOf("559453"));
			Angles.randLenVectors(e.id + 1, 20, e.finpow() * 80, 0 + e.rotation, 10, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, e.fout());
			});
		}),
		bigFlameShoot = new Effect(45f, e -> {
			Draw.color(Pal.accent);
			Drawf.tri(e.x, e.y, 8 * e.fout(), 12 * e.foutpow(), 0 + e.rotation);
			Drawf.tri(e.x, e.y, 6 * e.fout(), 12 * e.foutpow(), 135 + e.rotation);
			Drawf.tri(e.x, e.y, 6 * e.fout(), 12 * e.foutpow(), -135 + e.rotation);
			
			Angles.randLenVectors(e.id, 20, e.finpow() * 150, 0 + e.rotation, 10, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, e.fout() * 3);
			});
			Draw.color(Pal.turretHeat);
			Angles.randLenVectors(e.id + 1, 20, e.finpow() * 150, 0 + e.rotation, 10, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, e.fout() * 3);
			});
			Draw.color(Color.white);
			Angles.randLenVectors(e.id + 2, 20, e.finpow() * 150, 0 + e.rotation, 10, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, e.fout() * 3);
			});
		}),
		instSapShoot = new Effect(30f, e -> {
			Draw.color(Pal.sap.cpy().mul(1.5f));
			Drawf.tri(e.x, e.y, 10f * e.fout(), 50f * e.fout(), e.rotation + 90f);
			Drawf.tri(e.x, e.y, 10f * e.fout(), 50f * e.fout(), e.rotation + 45f);
			Drawf.tri(e.x, e.y, 10f * e.fout(), 50f * e.fout(), e.rotation - 45f);
			Drawf.tri(e.x, e.y, 10f * e.fout(), 50f * e.fout(), e.rotation - 90f);
			Drawf.light(e.x, e.y, 180f, Pal.sapBulletBack, 0.9f * e.fout());
		}),
		instSapTrail = new Effect(30f, e -> {
			Draw.color(Pal.sap.cpy().mul(1.5f));
			Drawf.tri(e.x, e.y, 10f * e.fout(), 20f * e.fout(), e.rotation + 0f);
			Drawf.tri(e.x, e.y, 5f * e.fout(), 10f * e.fout(), e.rotation + 90f);
			Drawf.tri(e.x, e.y, 5f * e.fout(), 10f * e.fout(), e.rotation - 90f);
			Drawf.tri(e.x, e.y, 10f * e.fout(), 20f * e.fout(), e.rotation + 180f);
			Drawf.light(e.x, e.y, 60f, Pal.sapBullet, 0.6f * e.fout());
		}),
		instSapHit = new Effect(30f, e -> {
			e.scaled(10f, b -> {
				Draw.color(Color.white, Pal.sap, b.fin());
				Lines.stroke(b.fout() * 3f + 0.2f);
				Lines.circle(b.x, b.y, b.fin() * 50f);
			});
			
			Draw.color(Pal.sapBulletBack);
			
			for (int i = 0; i < 5; i++) {
				Drawf.tri(e.x, e.y, 6f * e.fout(), 50f, e.rotation + 90f * i);
				Drawf.tri(e.x, e.y, 6f * e.fout(), 25f, e.rotation + 45f + 90f * i);
			}
			Drawf.light(e.x, e.y, 180f, Pal.sapBulletBack, 0.9f * e.fout());		
		}),
		bloodmoonHit = new Effect(120f, e -> {
			float mfin = 180f * Interp.fastSlow.apply(e.finpow());
			Lines.stroke(1f * e.foutpow());
			Draw.color(Pal.accent);
			
			for (int i = 0; i < 4; i++) {
			  Drawf.tri(e.x, e.y, 8f , 40f * Interp.slowFast.apply(e.foutpow()), (i * 90f) + mfin);
			  Drawf.tri(e.x, e.y, 8f , 20f * Interp.slowFast.apply(e.foutpow()), (i * 90f) + (mfin * 2f) + 45f);
			}
			Lines.circle(e.x, e.y, 20f * Interp.slowFast.apply(e.foutpow()));
		}),
		calamityCharge = new Effect(900f, e -> {
			Draw.color(Color.valueOf("FF9191"));
			Lines.stroke(3f * e.fout());
			Lines.circle(e.x, e.y, e.fin() * 80f);
			Draw.alpha(0.7f * e.fout());
			Fill.circle(e.x, e.y, e.fin() * 80f);
			Draw.alpha(1f);
			for (int i = 0; i < 4; i++) {
				float x = e.x + Angles.trnsx(Time.time * 2f + (90f * i), e.fin() * 80f, 0f);
				float y = e.y + Angles.trnsy(Time.time * 2f + (90f * i), e.fin() * 80f, 0f);
				Drawf.tri(x, y, e.fout() * 18f, e.fin() * 60f, 90f * i + Time.time * 2f);
			}
			for (int i = 0; i < 4; i++) {
				float x = e.x + Angles.trnsx(Time.time + (90f * i), e.fin() * 80f, 0f);
				float y = e.y + Angles.trnsy(Time.time + (90f * i), e.fin() * 80f, 0f);
				Drawf.tri(x, y, e.fout() * 18f, e.fin() * 60f, 90f * i + Time.time + 180f);
			}
			Draw.color();
			for (int i = 0; i < 4; i++) {
				float x = e.x + Angles.trnsx(Time.time * 2f + (90f * i), e.fin() * 80f, 0f);
				float y = e.y + Angles.trnsy(Time.time * 2f + (90f * i), e.fin() * 80f, 0f);
				Drawf.tri(x, y, e.fout() * 12f, e.fin() * 40f, 90f * i + Time.time * 2f);
			}
			for (int i = 0; i < 4; i++) {
				float x = e.x + Angles.trnsx(Time.time + (90f * i), e.fin() * 80f, 0f);
				float y = e.y + Angles.trnsy(Time.time + (90f * i), e.fin() * 80f, 0f);
				Drawf.tri(x, y, e.fout() * 12f, e.fin() * 40f, 90f * i + Time.time + 180f);
			}
		}),
		calamityShoot = new Effect(600f, e -> {
			Draw.color(Color.valueOf("FF9191"));
			Lines.stroke(3f * e.fout());
			Lines.circle(e.x, e.y, e.fin() * 120f);
			Draw.alpha(0.7f * e.fout());
			Fill.circle(e.x, e.y, e.fin() * 120f);
			Draw.alpha(1f);
			Angles.randLenVectors(e.id, 20f, e.fin() * 120f, (x, y) -> {
				Fill.circle(e.x + x, e.y + y, 3f * e.fout());
			});
			Angles.randLenVectors(e.id + 1f, 20f, e.fin() * 120f, (x, y) -> {
				Fill.square(e.x + x, e.y + y, 2f * e.fout(), 45f);
			});
		});
}