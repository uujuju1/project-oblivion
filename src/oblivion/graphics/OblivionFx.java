package oblivion.graphics;

import mindustry.entities.*;

public class OblivionFx {
	public Effect 
		calciteSmelt = new Effect(30f, e -> {
			public float offset = 6;
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
		});
}