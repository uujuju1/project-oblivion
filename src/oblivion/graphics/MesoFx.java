package oblivion.graphics;

import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.entities.*;

public class MesoFx {
	public static final Effect 
	mesoHit = new Effect(30f, e -> {
		Draw.color(OblvionPal.mesoMedium);
		Lines.stroke(e.foutpow() * 5f);
		Lines.circle(e.x, e.y, e.finpow() * 10f);
		for (var i = 0; i < 4; i++) {
			float x = e.x + Angles.trnsx(e.rotation + (90f * i), e.finpow() * 10f, 0f);
			float y = e.y + Angles.trnsy(e.rotation + (90f * i), e.finpow() * 10f, 0f);
		Drawf.tri(x, y, 10f * e.foutpow(), 25f * e.foutpow(), (90f * i) + e.finpow() * -90f);
		}
		Draw.color(OblvionPal.mesoLight);
		Lines.stroke(e.foutpow() * 2.5f);
		Lines.circle(e.x, e.y, e.finpow() * 10f);
		for (var i = 0; i < 4; i++) {
			float x = e.x + Angles.trnsx(e.rotation + (90f * i), e.finpow() * 10f, 0f);
			float y = e.y + Angles.trnsy(e.rotation + (90f * i), e.finpow() * 10f, 0f);
		Drawf.tri(x, y, 5f * e.foutpow(), 12.5f * e.foutpow(), (90f * i) + e.finpow() * -90f);
		}
	}),
	mesoChargeSmall = new Effect(30f, e -> {
		Fill.circle(e.x, e.y, e.finpow() * 5f);
		for (var i = -1; i < 2; i += 2) {
			float x = e.x + Angles.trnsx(e.rotation + i * (90f * Mathf.pow(e.fout(), 0.2f)), e.finpow() * 5f, 0f);
			float y = e.y + Angles.trnsy(e.rotation + i * (90f * Mathf.pow(e.fout(), 0.2f)), e.finpow() * 5f, 0f);
			Drawf.tri(x, y, 5f * Mathf.pow(e.fout(), 0.2f), 12.5f * Mathf.pow(e.fout(), 0.2f), 90f * Mathf.pow(e.fout(), 0.2f) * i);
		}
	}),
	mesoShoot = new Effect(60f, e -> {
		Fill.circle(e.x, e.y, e.finpow() * 5f);
		for (var i = -1; i < 2; i += 2) {
			float x = e.x + Angles.trnsx(e.rotation + i * -(180f * Mathf.pow(e.fout(), 0.2f)) + 180f, e.finpow() * 5f, 0f);
			float y = e.y + Angles.trnsy(e.rotation + i * -(180f * Mathf.pow(e.fout(), 0.2f)) + 180f, e.finpow() * 5f, 0f);
			Drawf.tri(x, y, 5f * Mathf.pow(e.fout(), 0.2f), 12.5f * Mathf.pow(e.fout(), 0.2f), e.rotation -180f * Mathf.pow(e.fout(), 0.2f) * i + 180f);
		}
		Drawf.tri(e.x, e.y, 5f * e.finpow(), 20f * e.finpow(), e.rotation);
	});
}