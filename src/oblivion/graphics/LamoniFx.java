package oblivion.graphics;

import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.entities.*;

public class LamoniFx {
	public static final Effect 
	imperialSmelt = new Effect(300f, e -> {
		e.scaled(45f, b -> {
			Draw.color(Pal.accent, Pal.lancerLaser, b.fin());
			Lines.stroke(3f * b.foutpow());
			Angles.randLenVectors(b.id, 5, 40f * b.finpow(), (x, y) -> {
				Lines.lineAngle(b.x + x, b.y + y, Mathf.angle(x, y), 5f * b.fout());
			});
		});
		
		Draw.color(Pal.metalGrayDark);
		if (Mathf.randomSeed(e.id, 1) > 0.5f) {
			Angles.randLenVectors(e.id, 3, 40f * e.finpow(), (x, y) ->  {
				Fill.circle(e.x + x, e.y + y, 3f * Interp.slowFast.apply(e.fout()));
			});
		}
	}),

	hafniumSmelt = new Effect(120f, e -> {
		Draw.color(Color.black);
		Draw.alpha(0.5f);
		Angles.randLenVectors(e.id, 20, 40 * e.finpow(), (x, y) -> {
			Fill.circle(e.x + x, e.y + y, e.fout() * 2f);
		});
		Draw.color(Pal.accent);
		e.scaled(20f, b -> {
			Lines.stroke(b.fout());
			Angles.randLenVectors(e.id + 1, 10, 40f * b.finpow(), (x, y) -> {
				Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 10f * b.foutpow());
			});
		});
		e.scaled(40f, b -> {
			Lines.stroke(b.fout());
			Angles.randLenVectors(e.id + 2, 10, 40f * b.finpow(), (x, y) -> {
				Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 10f * b.foutpow());
			});
		});
		e.scaled(60f, b -> {
			Lines.stroke(b.fout());
			Angles.randLenVectors(e.id + 3, 10, 40f * b.finpow(), (x, y) -> {
				Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 10f * b.foutpow());
			});
		});
		e.scaled(80f, b -> {
			Lines.stroke(b.fout());
			Angles.randLenVectors(e.id + 4, 10, 40f * b.finpow(), (x, y) -> {
				Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 10f * b.foutpow());
			});
		});
	}),

	waterSpill = new Effect(30f, e -> {
		Draw.color(Liquids.water.color.cpy().mul(1.3f));
		Lines.stroke(e.fout() * 2f);
		Angles.randLenVectors(e.id, 5, 40f * e.finpow(), (x, y) -> {
			Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 10f * e.foutpow());
		});
		
		Lines.stroke(e.fout());
		Angles.randLenVectors(e.id + 1, 5, 40f * e.finpow(), (x, y) -> {
			Lines.circle(e.x + x, e.y + y, 5f * e.foutpow());
		});
	}),

	copterShoot = new Effect(30f, e -> {
		Draw.color(Pal.heal, Color.lightGray, Color.gray, e.fin());
		Angles.randLenVectors(e.id, 5, e.finpow() * 6f, e.rotation, 20f, (x, y) -> {
		  Fill.circle(e.x + x, e.y + y, e.fout() * 1.5f);
		});
	}),
	copterShootBig = new Effect(30f, e -> {
		Draw.color(Pal.heal, Color.lightGray, Color.gray, e.fin());
		Angles.randLenVectors(e.id, 5, e.finpow() * 18f, e.rotation, 20f, (x, y) -> {
		  Fill.circle(e.x + x, e.y + y, e.fout() * 3f);
		});
		Drawf.tri(e.x, e.y, 8f * e.fin(), 10f * e.fout(), e.rotation);
		Drawf.tri(e.x, e.y, 8f * e.fin(), 10f * e.fout(), e.rotation + 180f);
	}),
	copterSparkShoot = new Effect(30f, e -> {
		Draw.color(Pal.heal);
		for(int i : Mathf.signs){
		  Drawf.tri(e.x, e.y, 4f * e.fout(), 8f, e.rotation + 90f * i);
		}
	}),
	copterTrail = new Effect(30f, e -> {
		Draw.color(Color.valueOf("BAF2B7"));
		Fill.circle(e.x, e.y, 3f * e.foutpow());
	}),

	poloniumExplosion = new Effect(30f, e -> {
		float intensity = e.rotation;
		Draw.color(Color.valueOf("948E9F"));
		e.scaled(25f, b -> {
		  Draw.alpha(b.foutpow());
		  Fill.circle(e.x, e.y, 40f * intensity);
		});
		Draw.alpha(1f);
		Lines.stroke(3f * e.fout() * intensity);
		Lines.circle(e.x, e.y, 40f * intensity);
		for (var i = 0; i < 6; i++) {
		  float x = e.x + Angles.trnsx(360f/6f * i, 40f * intensity, 0f);
		  float y = e.y + Angles.trnsy(360f/6f * i, 40f * intensity, 0f);
		  Drawf.tri(x, y, 10f * e.fout() * intensity, 80f * intensity * e.fin(Interp.fastSlow), 360f/6f * i);
		}
		Angles.randLenVectors(e.id, 20, e.fin(Interp.fastSlow) * 80f * intensity, (x, y) -> {
		  Fill.circle(e.x + x, e.y + y, 10f * e.fout() * intensity);
		});
	}),
	sodiumExplosion = new Effect(30f, e -> {
		float intensity = e.rotation;
		Draw.color(Pal.heal);
		e.scaled(25f, b -> {
		  Draw.alpha(b.foutpow());
		  Fill.circle(e.x, e.y, 80f * intensity);
		});
		Draw.alpha(1f);
		Lines.stroke(3f * e.fout() * intensity);
		Lines.circle(e.x, e.y, 80f * intensity);
		for (var i = 0; i < 6; i++) {
		  float x = e.x + Angles.trnsx(360f/6f * i, 80f * intensity, 0f);
		  float y = e.y + Angles.trnsy(360f/6f * i, 80f * intensity, 0f);
		  Drawf.tri(x, y, 10f * e.fout() * intensity, 80f * e.fin(Interp.fastSlow) * intensity, 360f/6f * i + 180f);
		}
		Angles.randLenVectors(e.id, 20, e.fin(Interp.fastSlow) * 80f * intensity, (x, y) -> {
		  Fill.circle(e.x + x, e.y + y, 10f * e.fout() * intensity);
		});
	}),

	blankHit = new Effect(60f, e -> {
		if (!(e.data instanceof Bullet bullet)) return;

		Draw.color(Color.white, bullet.frontColor, e.finpow());
		for (var i = 0; i < 4; i++) {
			e.scaled(20f + (i * 10f), b -> {
				Angles.randLenVectors(b.id + i, 10, 20f * e.finpow(), (x, y) -> {
					Fill.circle(e.x + x, e.y + y, 2f * b.fin());
				});
			});
		}
	});
}