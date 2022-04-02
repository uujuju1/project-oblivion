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
		});
}