package oblivion.entities.draw;

import arc.*;
import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import oblivion.type.*;
import oblivion.entities.comp.*;
// drawRotor eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
public class RotorDrawer {
	public String suffix;
	public TextureRegion region, topRegion, cellRegion, blurRegion;
	public float x = 0f, y = 0f;
	public float speed = 1f;
	public float deathSlowdownScl = 3f;
	public int bladeCount = 4;
	public boolean mirror = false, alternate = false;

	public RotorDrawer(String suffix) {
		this.suffix = suffix;
	}

	public void load(UnitType unit) {
		region = Core.atlas.find(unit.name + suffix);
		topRegion = Core.atlas.find(unit.name + suffix + "-top");
		cellRegion = Core.atlas.find(unit.name + suffix + "-cell", "clear");
		blurRegion = Core.atlas.find(unit.name + suffix + "-blur");
	}

	public void draw(Unit unit) {
		CopterComp type = ((CopterComp) unit);
		for (int i = (mirror ? -1 : 0); i < (mirror ? 2 : 1); i += (mirror ? 2 : 1)) {
			float dx = type.x + Angles.trnsx(type.rotation - 90f, i * x, y);
			float dy = type.y + Angles.trnsy(type.rotation - 90f, i * x, y);
	
			for (int j = 0; j < bladeCount; j++) {
				Draw.alpha(type.slowdown);
				Draw.rect(region, dx, dy, type.rotation + type.id + ((alternate && i == 1 ? -Time.time : Time.time) * speed / deathSlowdownScl) + (360f / bladeCount * j));
				drawCell(type, dx, dy, (360f / bladeCount * j), i);
			}
			
			Draw.alpha(-type.slowdown + 1);
			Draw.rect(blurRegion, dx, dy, type.rotation + type.id + ((alternate && i == 1 ? -Time.time : Time.time) * speed));
			
			Draw.reset();
			Draw.rect(topRegion, dx, dy, type.rotation - 90f);
		}
	}

	public void drawCell(CopterComp unit, float x, float y, float rotation, int i) {
		Draw.color(cellColor(unit));
		Draw.rect(cellRegion, x, y, unit.rotation + rotation + unit.id + ((alternate && i == 1 ? -Time.time : Time.time) * speed / deathSlowdownScl));
		Draw.reset();
	}

	public Color cellColor(CopterComp unit){
		float f = Mathf.clamp(unit.healthf());
		return Tmp.c1.set(Color.black).lerp(unit.team.color, f + Mathf.absin(Time.time, Math.max(f * 5f, 1f), 1f - f));
	}

}