package oblivion.graphics;

import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
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
	});
}