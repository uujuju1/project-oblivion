package oblivion.graphics;

import arc.util.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.graphics.*;
/** an Drawf like system
	* usable with oblivion's drawer system
	* @author Uujuju
*/
public class DrawEx {
	// simply draws 2 circles with some unit engine configurations
	public static void circleEngine(Unit unit, float x, float y, float size) {
		float dx = unit.x + Angles.trnsx(unit.rotation - 90, x, y);
		float dy = unit.y + Angles.trnsy(unit.rotation - 90, x, y);
		Draw.color(unit.team.color);
		Fill.circle(dx, dy, (size + Mathf.absin(Time.time, 2, size / 4)) * unit.elevation);
		Draw.color();
		Fill.circle(dx, dy, ((size + Mathf.absin(Time.time, 2, size / 4)) * unit.elevation) / 2);
		Draw.reset();
	}
	public static void spikedCircle(float x, float y, float radius, int spikes, float scl, Color color) {
		float sin = Mathf.absin(5f, 1f);
		Draw.color(color);
		Lines.stroke((1.5f + sin) * scl);
		Lines.circle(x, y, radius);
		for (int i = 0; i < spikes; i++) {
			float dx = x + Angles.trnsx(Time.time + ((360/spikes) * i), (radius + sin) * scl, 0);
			float dy = y + Angles.trnsy(Time.time + ((360/spikes) * i), (radius + sin) * scl, 0);
			Lines.lineAngle(dx, dy, Angles.angle(x, y, dx, dy), (10f + sin) * scl);
		}

		Draw.color();
		Lines.stroke(((1.5f + sin) * scl) / 2f);
		Lines.circle(x, y, radius);
		for (int i = 0; i < spikes; i++) {
			float dx = x + Angles.trnsx(Time.time + ((360/spikes) * i), (radius + sin) * scl, 0);
			float dy = y + Angles.trnsy(Time.time + ((360/spikes) * i), (radius + sin) * scl, 0);
			Lines.lineAngle(dx, dy, Angles.angle(x, y, dx, dy), ((10f + sin) * scl) / 2f);
		}
		Draw.reset();
	}
}