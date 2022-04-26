package oblivion.type;

import arc.func.*;
import arc.math.*;
import arc.util.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.entities.*;
import mindustry.graphics.*;

// engineSize and engineOffset is now useless {:
public class OblivionUnitType extends UnitType {
	private static final Vec2 legOffset = new Vec2();
	public Cons<Unit> engineDrawer = unit -> {}, topDrawer = unit -> {};

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void drawEngine(Unit unit) {
		engineDrawer.get(unit);
	}
	
	@Override
	public void draw(Unit unit) {
		Mechc mech = unit instanceof Mechc ? (Mechc)unit : null;
		float z = unit.elevation > 0.5f ? (lowAltitude ? Layer.flyingUnitLow : Layer.flyingUnit) : groundLayer + Mathf.clamp(hitSize / 4000f, 0, 0.01f);

		if(unit.controller().isBeingControlled(player.unit())){
			drawControl(unit);
		}

		if(unit.isFlying() || visualElevation > 0){
			Draw.z(Math.min(Layer.darkness, z - 1f));
			drawShadow(unit);
		}

		Draw.z(z - 0.02f);

		if(mech != null){
			drawMech(mech);

			//side
			legOffset.trns(mech.baseRotation(), 0f, Mathf.lerp(Mathf.sin(mech.walkExtend(true), 2f/Mathf.PI, 1) * mechSideSway, 0f, unit.elevation));

			//front
			legOffset.add(Tmp.v1.trns(mech.baseRotation() + 90, 0f, Mathf.lerp(Mathf.sin(mech.walkExtend(true), 1f/Mathf.PI, 1) * mechFrontSway, 0f, unit.elevation)));

			unit.trns(legOffset.x, legOffset.y);
		}

		if(unit instanceof Legsc){
			drawLegs((Unit & Legsc)unit);
		}

		Draw.z(Math.min(z - 0.01f, Layer.bullet - 1f));

		if(unit instanceof Payloadc){
			drawPayload((Unit & Payloadc)unit);
		}

		drawSoftShadow(unit);

		Draw.z(z);

		if(drawBody) drawOutline(unit);
		weapons.each(w -> {
			if(w instanceof OblivionWeapon) {
				if(((OblivionWeapon) w).above) {
					drawWeaponOutlines(unit);
				}
			} else {
				drawWeaponOutlines(unit);
			}
		});
		if(engineSize > 0) drawEngine(unit);
		if(drawBody) drawBody(unit);
		if(drawCell) drawCell(unit);
		weapons.each(w -> {
			if(w instanceof OblivionWeapon) {
				if(((OblivionWeapon) w).above) {
					drawWeapons(unit);
				}
			} else {
				drawWeapons(unit);
			}
		});		if(drawItems) drawItems(unit);
		weapons.each(w -> {
			if(w instanceof OblivionWeapon) {
				if(!((OblivionWeapon) w).above) {
					drawWeaponOutlines(unit);
				}
			}
		});
		weapons.each(w -> {
			if(w instanceof OblivionWeapon) {
				if(!((OblivionWeapon) w).above) {
					drawWeapons(unit);
				}
			}
		});
		drawLight(unit);

		if(unit.shieldAlpha > 0 && drawShields){
			drawShield(unit);
		}

		if(mech != null){
			unit.trns(-legOffset.x, -legOffset.y);
		}

		if(decals.size > 0){
			float base = unit.rotation - 90;
			for(var d : decals){
				Draw.z(d.layer);
				Draw.scl(d.xScale, d.yScale);
				Draw.color(d.color);
				Draw.rect(d.region, unit.x + Angles.trnsx(base, d.x, d.y), unit.y + Angles.trnsy(base, d.x, d.y), base + d.rotation);
			}
			Draw.reset();
			Draw.z(z);
		}

		if(unit.abilities.size > 0){
			for(Ability a : unit.abilities){
				Draw.reset();
				a.draw(unit);
			}
		}

		Draw.reset();
		topDrawer.get(unit);
	}
}