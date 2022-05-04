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
		Fill.circle(dx, dy, size + Mathf.absin(Time.time, 2, size / 4) * unit.elevation);
		Draw.color();
		Fill.circle(dx, dy, size + Mathf.absin(Time.time, 2, size / 4) * unit.elevation / 2);
	}
	public static void triangleEngine(Unit unit, float x, float y, float width, float length, float rotation) {
		float dx = unit.x + Angles.trnsx(unit.rotation - 90, x, y);
		float dy = unit.y + Angles.trnsy(unit.rotation - 90, x, y);
		Draw.color(unit.team.color);
		Drawf.tri(
			dx,
			dy,
			(width + Mathf.absin(Time.time, 2, width / 4)) * unit.elevation,
			(length + Mathf.absin(Time.time, 2, length / 4)) * unit.elevation,
			unit.rotation - rotation
		);
		Draw.color();
		Drawf.tri(
			dx,
			dy,
			(width + Mathf.absin(Time.time, 2, width / 4)) * unit.elevation / 2,
			(length + Mathf.absin(Time.time, 2, length / 4)) * unit.elevation / 2,
			unit.rotation - rotation
		);
	}
}