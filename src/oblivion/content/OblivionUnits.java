package oblivion.content;

import arc.*;
import arc.util.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.ai.types.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.entities.abilities.*;
import oblivion.type.*;
import oblivion.graphics.*;
import oblivion.entities.draw.*;
import oblivion.entities.comp.*;

import static mindustry.Vars.*;

public class OblivionUnits{
	public static UnitType 
	slop, detra, tedri, taleni, kolete,
	pioli, taneki, notremite, dopretile, niboletra,
	phi, root, multi, pow, expo,

	republic, giga, archaranid, bloodmoon, yetinus,
	
	citizen,
	mercurie, aphrodite, apollo, zeus, chronos,
	latrodectus/*, phoneutria, lycosidae, sparassidae, trichonephila*/;

	public void load() {
		slop = new UnitType("slop") {{
			health = 240;
			armor = 1f;
			speed = 2f;
			flying = true;
			constructor = UnitEntity::create;
			range = 104f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-mesulfate-missile") {{
					x = 0f;
					y = -1.25f;
					mirror = false;
					reload = 30f;
					shootSound = Sounds.missile;
					bullet = new MissileBulletType(2f, 15) {{
						lifetime = 52f;
						status = StatusEffects.burning;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
						trailColor = OblivionPal.mesoLight;
						hitEffect = despawnEffect = MesoFx.mesoHit;
					}};
				}}
			);
		}};
		detra = new UnitType("detra") {{
			health = 450;
			armor = 3f;
			speed = 1.7f;
			flying = true;
			constructor = UnitEntity::create;
			hitSize = 8f;
			range = 152f;
			maxRange = range;
			engineOffset = 2f;
			weapons.add(
				new Weapon("oblivion-mesulfate-artillery") {{
					x = 2.25f;
					y = 3.75f;
					reload = 60f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(1.5f, 30) {{
					hitEffect = Fx.blastExplosion;
						lifetime = 101f;
						knockback = 0.6f;
						collides = true;
						collidesTiles = true;
						splashDamageRadius = 35f;
						splashDamage = 20f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
						shootEffect = MesoFx.mesoShoot;
						hitEffect = despawnEffect = MesoFx.mesoHit;
					}};
				}}
			);
		}};
		tedri = new UnitType("tedri") {{
			health = 740;
			armor = 5f;
			speed = 1.5f;
			flying = true;
			constructor = UnitEntity::create;
			hitSize = 14f;
			range = 200f;
			maxRange = range;
			engineSize = 5f;
			engineOffset = 4.5f;
			weapons.add(
				new Weapon("oblivion-mesulfate-laser") {{
					x = 5.75f;
					y = -3.5f;
					reload = 120f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(50) {{
						width = 8f;
						length = 200;
						colors = new Color[]{OblivionPal.mesoDark, OblivionPal.mesoMedium, OblivionPal.mesoLight};
					}};
				}},
				new Weapon("oblivion-mesulfate-cannon") {{
					x = 0f;
					y = 6f;
					mirror = false;
					reload = 150f;
					shake = 3;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(3f, 34) {{
						width = height = 9f;
						lifetime = 66.6f; //ohno ohfu-
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
						for (int i = 1; i <= 5; i++) {
							float speedMultiplier = 3f / i;
							spawnBullets.addAll(
								new BasicBulletType(speedMultiplier, 7) {{
									width = height = 5f;
									lifetime = 66.6f;
									frontColor = OblivionPal.mesoMedium;
									backColor = OblivionPal.mesoDark;
								}}
							);
						}
					}};
				}}
			);
		}};
		taleni = new UnitType("taleni") {{
			health = 7500;
			armor = 7f;
			speed = 0.6f;
			flying = true;
			constructor = UnitEntity::create;
			hitSize = 30f;
			range = 256f;
			engineSize = 7f;
			engineOffset = 20f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-mesulfate-railgun") {{
					x = y = 0f;
					reload = 180f;
					shake = 3f;
					shootSound = Sounds.plasmadrop;
					mirror = false;
					bullet = new LaserBulletType(175) {{
						width = 10f;
						length = 256f;
						colors = new Color[]{OblivionPal.mesoDark, OblivionPal.mesoMedium, OblivionPal.mesoLight};
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15.25f;
					y = 4f;
					reload = 35f;
					shootSound = Sounds.shootBig;
					shoot = new ShootPattern() {{
						shots = 3;
						shotDelay = 5f;
					}};
					bullet = new BasicBulletType(6f, 40) {{
						width = height = 10f;
						lifetime = 50f;
						splashDamage = 50f;
						splashDamageRadius = 10f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15f;
					y = -14f;
					reload = 35f;
					shootSound = Sounds.shootBig;
					shoot = new ShootPattern() {{
						shots = 3;
						shotDelay = 5f;
					}};
					bullet = new BasicBulletType(6f, 40) {{
						width = height = 10f;
						lifetime = 50f;
						splashDamage = 50f;
						splashDamageRadius = 10f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		kolete = new UnitType("kolete") {{
			health = 22000;
			armor = 13f;
			speed = 0.7f;
			flying = true;
			constructor = UnitEntity::create;
			hitSize = 40f;
			range = 300f;
			maxRange = range;
			engineSize = 10f;
			engineOffset = 27f;
			abilities.add(
				new EnergyFieldAbility(40f, 70f, 160f) {{
					hitBuildings = false;
					color = Color.valueOf("E86F6F");
					x = 0f;
					y = -4f;
				}}
			);
			weapons.add(
				new Weapon("oblivion-mesulfate-big-laser") {{
					x = 26.5f;
					y = 16.75f;
					reload = 60f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(70) {{
						width = 11f;
						length = 300f;
						colors = new Color[]{OblivionPal.mesoDark, OblivionPal.mesoMedium, OblivionPal.mesoLight};
					}};
				}},
				new Weapon("oblivion-mesulfate-mine") {{
					x = 24.25f;
					y = -12f;
					reload = 90f;
					range = 300f;
					inaccuracy = 15f;
					velocityRnd = 0.5f;
					shootSound = Sounds.artillery;
					shoot = new ShootPattern() {{
						shots = 4;
					}};
					bullet = new BasicBulletType(11f, 100) {{
						drag = 0.04f;
						width = height = 12f;
						lifetime = 300f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
						fragBullets = 5;
						fragBullet = new BasicBulletType(8f, 30) {{
							homingPower = 1f;
							homingRange = 400f;
							lifetime = 65f;
							hitSound = despawnSound = Sounds.artillery;
							hitEffect = despawnEffect = Fx.blastExplosion;
							frontColor = OblivionPal.mesoMedium;
							backColor = OblivionPal.mesoDark;
						}};	
					}};
				}}
			);
		}};

		pioli = new UnitType("pioli") {{
			health = 200;
			armor = 0f;
			speed = 2.5f;
			flying = true;
			constructor = UnitEntity::create;
			range = 128f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-copremite-mount") {{
					x = 2f;
					y = 2.5f;
					reload = 30f;
					shootSound = Sounds.lasershoot;
					bullet = new LaserBoltBulletType(4f, 16) {{
						lifetime = 32f;
						status = OblivionStatuses.infested;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}},
				new Weapon("oblivion-copremite-mount") {{
					x = -2.75f;
					y = -3.5f;
					reload = 30f;
					shootSound = Sounds.lasershoot;
					flipSprite = true;
					bullet = new LaserBoltBulletType(4f, 16) {{
						lifetime = 32f;
						status = OblivionStatuses.infested;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}}
			);
		}};
		taneki = new UnitType("taneki") {{
			health = 530;
			armor = 1f;
			speed = 2f;
			flying = true;
			constructor = UnitEntity::create;
			engineOffset = 8f;
			range = 168f;
			maxRange = range;
			hitSize = 8f;
			weapons.add(
				new Weapon("oblivion-copremite-mount") {{
					x = 4.75f;
					y = 0.5f;
					reload = 30f;
					shootSound = Sounds.lasershoot;
					bullet = new LaserBoltBulletType(3f, 20) {{
						lifetime = 56f;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}},
				new Weapon("oblivion-copremite-artillery") {{
					x = 3.5f;
					y = -4f;
					reload = 60f;
					shootSound = Sounds.plasmadrop;
					bullet = new ArtilleryBulletType(3f, 20) {{
						width = 14f;
						height = 14f;
						lifetime = 56f;
						status = OblivionStatuses.infested;
						weaveMag = 5f;
						weaveScale = 4f;
						homingPower = 0.05f;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}}
			);
		}};
		notremite = new UnitType("notremite") {{
			health = 920;
			armor = 4f;
			speed = 1.7f;
			flying = true;
			constructor = UnitEntity::create;
			engineOffset = 14f;
			engineSize = 4f;
			range = 176f;
			maxRange = range;
			hitSize = 12f;
			weapons.add(
				new Weapon("oblivion-copremite-laser") {{
					x = 8.75f;
					y = -2.25f;
					reload = 60f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(50) {{
						width = 7f;
						length = 176f;
						status = OblivionStatuses.infested;
						colors = new Color[]{OblivionPal.copreDark, OblivionPal.copreMedium, OblivionPal.copreLight};
					}};
				}}
			);
		}};
		dopretile = new UnitType("dopretile") {{
			health = 13000;
			speed = 1.0f;
			armor = 6f;
			flying = true;
			constructor = UnitEntity::create;
			engineOffset = 27f;
			engineSize = 6f;
			range = 224f;
			maxRange = range;
			hitSize = 30f;
			weapons.add(
				new Weapon("oblivion-copremite-big-laser") {{
					x = 0f;
					y = -4f;
					reload = 60f;
					shootSound = Sounds.plasmadrop;
					mirror = false;
					bullet = new LaserBulletType(160) {{
						width = 9f;
						length = 224f;
						status = OblivionStatuses.infested;
						colors = new Color[]{OblivionPal.copreDark, OblivionPal.copreMedium, OblivionPal.copreLight};
					}};
				}},
				new Weapon("oblivion-copremite-laser") {{
					x = 6.25f;
					y = 12.75f;
					reload = 15f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(2.5f, 40) {{
						width = 9f;
						height = 12f;
						lifetime = 89.6f;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}},
				new Weapon("oblivion-copremite-laser") {{
					x = 12.25f;
					y = -8.25f;
					reload = 15f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(2.5f, 40) {{
						width = 9f;
						height = 12f;
						lifetime = 89.6f;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}}
			);
		}};
		niboletra = new OblivionUnitType("niboletra") {{
			health = 20000;
			armor = 10f;
			speed = 0.7f;
			flying = true;
			constructor = UnitEntity::create;
			engineDrawer = unit -> {
				Draw.color(unit.team.color);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, 0f, -30f), unit.y + Angles.trnsy(unit.rotation - 90f, 0f, -30f), 10 + Mathf.absin(Time.time, 2f, 10f / 4f));
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, -16f, -18f), unit.y + Angles.trnsy(unit.rotation - 90f, -16f, -18f), 8 + Mathf.absin(Time.time, 2f, 10f / 4f));
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, 16f, -18f), unit.y + Angles.trnsy(unit.rotation - 90f, 16f, -18f), 8 + Mathf.absin(Time.time, 2f, 10f / 4f));
				Drawf.tri(unit.x + Angles.trnsx(unit.rotation - 90f, 0f, -35f), unit.y + Angles.trnsy(unit.rotation - 90f, 0f, -35f), 20 + Mathf.absin(Time.time, 2f, 13f / 2f), 20 + Mathf.absin(Time.time, 2f, 10f / 2f), unit.rotation - 180f);
				
				Draw.color();
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, 0f, -27f), unit.y + Angles.trnsy(unit.rotation - 90f, 0f, -27f), (10 + Mathf.absin(Time.time, 2f, 10f / 4f)) / 2f);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, -16f, -18f), unit.y + Angles.trnsy(unit.rotation - 90f, -16f, -18f), (8 + Mathf.absin(Time.time, 2f, 10f / 4f))/ 2f);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90f, 16f, -18f), unit.y + Angles.trnsy(unit.rotation - 90f, 16f, -18f), (8 + Mathf.absin(Time.time, 2f, 10f / 4f))/ 2f);
				Drawf.tri(unit.x + Angles.trnsx(unit.rotation - 90f, 0f, -30f), unit.y + Angles.trnsy(unit.rotation - 90f, 0f, -30f), (20 + Mathf.absin(Time.time, 2f, 13f / 2f)) / 2f, (20 + Mathf.absin(Time.time, 2f, 10f / 2f)) / 2f, unit.rotation - 180f);
			};
			range = 304f;
			maxRange = range;
			hitSize = 36f;
			weapons.add(
				new Weapon("oblivion-copremite-plasma-cannon") {{
					x = 0f;
					y = 0.5f;
					reload = 300f;
					shootSound = Sounds.laserbig;
					mirror = false;
					continuous = true;
					bullet = new ContinuousLaserBulletType() {{
						damage = 125f;
						length = 304;
						drawSize = 200f;
						lifetime = 150f;
						status = OblivionStatuses.infested;
						shake = 1f;
						width = 6f;
						largeHit = true;
						incendChance = 0f;
						colors = new Color[]{OblivionPal.copreDark, OblivionPal.copreMedium, OblivionPal.copreLight};
					}};
				}}
			);
		}};


		republic = new OblivionUnitType("republic") {{
			health = 67700;
			speed = 0.2f;
			constructor = MechUnit::create;
			engineDrawer = unit -> {
				Draw.color(Color.valueOf("E7885C"));
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 0, -21) * unit.elevation, unit.y + Angles.trnsy(unit.rotation - 90, 0, -21) * unit.elevation, (6 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation);
				Drawf.tri(unit.x + Angles.trnsx(unit.rotation - 90, 0, -24) * unit.elevation, unit.y + Angles.trnsy(unit.rotation - 90, 0, -24) * unit.elevation, (13 + Mathf.absin(Time.time, 2, 13 / 2)) * unit.elevation, (15 + Mathf.absin(Time.time, 2, 10 / 2)) * unit.elevation, unit.rotation - 180);
				Draw.color(Color.white);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 0, -20), unit.y + Angles.trnsy(unit.rotation - 90, 0, -20), (6 + Mathf.absin(Time.time, 2, 10 / 4)) / 2 * unit.elevation);
				Drawf.tri(unit.x + Angles.trnsx(unit.rotation - 90, 0, -20 - unit.elevation), unit.y + Angles.trnsy(unit.rotation - 90, 0, -20 - unit.elevation), (14 + Mathf.absin(Time.time, 2, 13 / 2)) / 2 * unit.elevation, (15 + Mathf.absin(Time.time, 2, 10 / 2)) / 2 * unit.elevation, unit.rotation - 180);
			};
			rotateSpeed = 1.4f;
			armor = 20f;
			mechStepParticles = true;
			mechLandShake = 3f;
			canBoost = true;
			drownTimeMultiplier = 10f;
			mechFrontSway = 0.2f;
			mechSideSway = 0.7f;
			range = 400f;
			maxRange = range;
			hitSize = 58f;
			weapons.add(
				new Weapon("oblivion-republic-weapon"){{
					x = 30f;
					y = 0f;
					reload = 45f;
					recoil = 10f;
					shootY = 12f;
					shootSound = Sounds.shootBig;
					shake = 5f;
					top = false;
					shoot = new ShootPattern() {{
						shots = 3;
						shotDelay = 5f;
					}};
					bullet = new BasicBulletType(8f, 150) {{
						pierce = true;
						pierceCap = 7;
						lifetime = 40f;
						width = height = 15f;
						hitEffect = Fx.flakExplosion;
						splashDamage = 20f;
						splashDamageRadius = 10f;
						shootEffect = OblivionFx.bigFlameShoot;
					}};
				}},
				new Weapon("oblivion-republic-laser") {{
					x = 0f;
					y = -7f;
					reload = 30f;
					recoil = 3f;
					shootY = 5f;
					shootSound = Sounds.laser;
					shake = 1f;
					mirror = false;
					bullet = new LaserBulletType(50) {{
						width = 20f;
						length = 200f;
						colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
					}};
				}},
				new Weapon("oblivion-republic-laser") {{
					x = 13.25f;
					y = 5f;
					reload = 60f;
					recoil = 3f;
					shootY = 5f;
					shootSound = Sounds.artillery;
					shake = 3f;
					rotate = alternate = true;
					bullet = new ArtilleryBulletType(6f, 80) {{
						collides = true;
						homingPower = 0.08f;
						lifetime = 45f;
						splashDamageRadius = 40f;
						splashDamage = 20f;
						width = height = 16f;
					}};
				}}
			);
		}};
		giga = new UnitType("giga") {{
			health = 45000f;
			armor = 13f;
			speed = 0.3f;
			hitSize = 32f;
			rotateSpeed = 1.5f;
			drownTimeMultiplier = 6f;
			constructor = LegsUnit::create;
			legCount = 6;
			legLength = 30f;
			legBaseOffset = 15f;
			legMoveSpace = 1.5f;
			legForwardScl = 0.58f;
			hovering = true;
			groundLayer = Layer.legUnit;
			range = 400f;
			weapons.add(
				new Weapon("oblivion-giga-laser") {{
					x = 7.75f;
					y = -8.25f;
					reload = 60f;
					recoil = 3f;
					shootY = 6f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(80) {{
						healPercent = 0.2f;
						width = 50f;
						length = 400f;
						colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
					}};
				}},
				new Weapon("oblivion-giga-missile") {{
					x = 25.25f;
					y = -3f;
					reload = 30f;
					recoil = 3f;
					shootY = 2f;
					shootSound = Sounds.missile;
					alternate = true;
					shoot = new ShootPattern() {{
						shots = 6;
					}};
					inaccuracy = 7f;
					bullet = new MissileBulletType(4f, 40) {{
						healPercent = 0.1f;
						width = height = 8f;
						lifetime = 100f;
						collidesTeam = true;
						backColor = trailColor = hitColor = lightColor = lightningColor = Pal.heal;
						frontColor = Color.white;
					}};
				}},
				new Weapon() {{
					x = 0f;
					y = 6.5f;
					reload = 350f;
					recoil = 0f;
					shake = 20f;
					shootSound = Sounds.laserblast;
					chargeSound = Sounds.lasercharge;
					top = false;
					mirror = false;
					continuous = true;
					shoot = new ShootPattern() {{
						firstShotDelay = Fx.greenLaserCharge.lifetime;
					}};
					bullet = new ContinuousLaserBulletType(210) {{
						healPercent = 0.4f;
						lifetime = 60f;
						incendAmount = 0;
						incendSpread = 0;
						incendChance = 0f;
						colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
					}};
				}}
			);
		}};
		archaranid = new UnitType("archaranid") {{
			health = 63500;
			armor = 18f;
			speed = 0.3f;
			legCount = 8;
			legMoveSpace = 1.2f;
			legPairOffset = 3;
			legLength = 100f;
			legExtension = -20;
			legBaseOffset = 10f;
			legLengthScl = 0.93f;
			legSpeed = 0.19f;
			legSplashDamage = 80;
			legSplashRange = 60;
			hitSize = 32f;
			hovering = true;
			groundLayer = Layer.legUnit;
			constructor = LegsUnit::create;
			range = 45f * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-archanid-cannon") {{
					x = 0f;
					y = -15.25f;
					reload = 120f;
					recoil = 5f;
					mirror = false;
					rotate = true;
					rotateSpeed = 1f;
					shootY = 28f;
					shake = 10f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(2f, 200) {{
						width = height = 35f;
						collides = collidesTiles = true;
						lifetime = 180f;
						frontColor = Pal.sapBullet;
						backColor = Pal.sapBulletBack;
						hitShake = 10f;
						lightRadius = 40f;
						lightColor = Pal.sap;
						lightOpacity = 0.6f;
						status = StatusEffects.sapped;
						statusDuration = 60f * 20;
						splashDamage = 200;
						splashDamageRadius = 16f;
						fragBullets = 5;
						fragBullet = new LaserBulletType(120) {{
							width = 10f;
							length = 80f;
							collides = collidesTiles = true;
							shootEffect = OblivionFx.instSapShoot;
							hitEffect = OblivionFx.instSapHit;
							colors = new Color[]{Pal.sapBullet, Pal.sapBullet, Pal.sapBulletBack};
						}};
					}};
				}},
				new Weapon("oblivion-archanid-point") {{
					x = 19f;
					y = -5.5f;
					reload = 30f;
					recoil = 2f;
					shootSound = Sounds.railgun;
					bullet = new LaserBulletType(175) {{
						length = 180f;
						width = 12f;
						shootEffect = OblivionFx.instSapShoot;
						hitEffect = OblivionFx.instSapHit;
						colors = new Color[]{Pal.sapBullet, Pal.sapBullet, Pal.sapBulletBack};
					}};
				}}
			);
		}};
		bloodmoon = new OblivionUnitType("bloodmoon") {{
			health = 65000;
			armor = 18f;
			speed = 0.2f;
			flying = lowAltitude = true;
			constructor = UnitEntity::create;
			hitSize = 56f;
			range = 50f * 8f;
			engineDrawer = unit -> {
				DrawEx.circleEngine(unit, -20, -30, 10);
				DrawEx.circleEngine(unit, 20, -30, 10);
				DrawEx.circleEngine(unit, -25, 35, 10);
				DrawEx.circleEngine(unit, 25, 35, 10);
			};
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-bloodmoon-cannon") {{
					x = y = 0f;
					reload = 60f;
					recoil = 5f;
					shootY = 25f;
					shootSound = Sounds.artillery;
					mirror = false;
					bullet = new ArtilleryBulletType(1.25f, 250) {{
						width = height = 20;
						lifetime = 320f;
						splashDamageRadius = 60f;
						splashDamage = 80f;
						collides = collidesTiles = collidesAir = collidesGround = true;
						hitSound = despawnSound = Sounds.plasmaboom;
						hitEffect = despawnEffect = OblivionFx.bloodmoonHit;
						fragBullets = 3;
						fragBullet = new BasicBulletType(1f, 60) {{
							homingPower = 0.03f;
							homingRange = 400f;
							lifetime = 300f;
							splashDamageRadius = 40f;
							splashDamage = 30f;
							hitSound = despawnSound = Sounds.plasmaboom;
							hitEffect = despawnEffect = OblivionFx.bloodmoonHit;
							trailChance = 5f;
							trailWidth = 1.8f;
							trailLength = 8;
						}};
					}};
				}}
			);
		}};

		yetinus = new UnitType("yetinus") {{
			health = 65000;
			speed = 0.3f;
			drag = 0.18f;
			hitSize = 58f;
			armor = 25f;
			accel = 0.19f;
			rotateSpeed = 0.3f;
			constructor = UnitWaterMove::create;
			trailLength = 70;
			waveTrailX = 23f;
			waveTrailY = -32f;
			range = 600f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-yetinus-railgun") {{
					x = 0f;
					y = 41.25f;
					reload = 90f;
					mirror = false;
					recoil = 5f;
					shootY = 12f;
					shake = 8f;
					shootCone = 45f;
					shootSound = Sounds.railgun;
					bullet = new BasicBulletType(6f, 800) {{
						shootEffect = Fx.railShoot;
						lifetime = 100;
						splashDamageRadius = 180f;
						splashDamage =400f;
						width = height = 40;
						homingRange = 600f;
						homingPower = 0.1f;
						hitEffect = despawnEffect = Fx.spawnShockwave;
						hitColor = Pal.bulletYellow;
						smokeEffect = Fx.shootBig2;
					}};
				}},
				new Weapon("oblivion-yetinus-laser") {{
					x = 0f;
					y = 0f;
					reload = 45f;
					rotate = true;
					rotateSpeed = 1.5f;
					mirror = false;
					recoil = 2f;
					shootY = 4f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(100) {{
						width = 20f;
						length = 300f;
					}};
				}}
			);
		}};

		phi = new OblivionUnitType("phi") {{
			health = 450;
			speed = 2.5f;
			flying = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("3F424D");
			engineDrawer = unit -> {
				DrawEx.circleEngine(unit, 2.5f, -5.5f, 2f);
				DrawEx.circleEngine(unit, -2.5f, -5.5f, 2f);
			};
			hitSize = 7f;
			range = 18f * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-phi-weapon") {{
					x = 5.25f;
					y = 0.5f;
					reload = 30f;
					top = false;
					bullet = new BasicBulletType(2.5f, 15) {{
						lifetime = 18 * 4f;
						hitEffect = OblivionFx.carmaniteHit;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}}
			);
		}};
		root = new OblivionUnitType("root") {{
			health = 920;
			armor = 2f;
			speed = 2f;
			flying = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("3F424D");
			engineDrawer = unit -> {
				DrawEx.circleEngine(unit, 0f, -5.5f, 4f);
			};
			hitSize = 13f;
			range = 23 * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-root-weapon") {{
					x = 8f;
					y = -0.75f;
					reload = 60f;
					top = false;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(3f, 40) {{
						lifetime = 23 * 8f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						width = height = 12f;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
						homingRange = 80f;
						homingPower = 0.05f;
						collides = collidesTiles = collidesGround = collidesAir = true;
					}};
				}}
			);
		}};
		multi = new OblivionUnitType("multi") {{
			health = 1200;
			armor = 5f;
			speed = 1.65f;
			flying = lowAltitude = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("3F424D");
			engineDrawer = unit -> {
				DrawEx.circleEngine(unit, 8f, 7f, 4f);
				DrawEx.circleEngine(unit, -8f, 7f, 4f);
				DrawEx.circleEngine(unit, 6f, -11f, 4f);
				DrawEx.circleEngine(unit, -8f, -11f, 4f);
			};
			hitSize = 20f;
			range = 26f * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-multi-weapon") {{
					x = 15.75f;
					y = 0f;
					reload = 90f;
					shake = 3f;
					top = false;
					shootY = 12f;
					shootSound = Sounds.shootBig;
					shoot = new ShootPattern() {{
						shots = 3;
						shotDelay = 5f;
					}};
					bullet = new BasicBulletType(4f, 45) {{
						width = height = 13f;
						lifetime = 6.6f * 8f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}},
				new Weapon("oblivion-multi-cannon") {{
					x = y = 0f;
					reload = 120f;
					mirror = false;
					shake = 2;
					shootSound = Sounds.plasmadrop;
					bullet = new ArtilleryBulletType(2f, 80) {{
						lifetime = 13 * 8;
						width = height = 16f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						collidesAir = collidesGround = collidesTiles = collides = true;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}}
			);
		}};
		pow = new OblivionUnitType("pow") {{
			health = 6500;
			armor = 7f;
			speed = 1.05f;
			flying = lowAltitude = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("3F424D");
			engineDrawer = unit -> {
				for (int i = -1; i <= 2; i += 2) {
					DrawEx.circleEngine(unit, i * 10f, 11f, 4f);
					DrawEx.circleEngine(unit, i * 10f, 0f, 4f);
					DrawEx.circleEngine(unit, i * 10f, -15f, 4f);
				}
			};
			hitSize = 25f;
			range = 33 * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-pow-weapon") {{
					x = 18f;
					y = 0f;
					reload = 50f;
					shoot = new ShootPattern() {{
						shots = 5;
						shotDelay = 5f;
					}};
					shake = 5f;
					shootY = 18f;
					shootCone = 15f;
					top = false;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(6f, 65) {{
						lifetime = 5.5f * 8f;
						width = height = 16f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}},
				new Weapon("oblivion-pow-artillery") {{
					x = 8.25f;
					y = -6.5f;
					reload = 90f;
					shoot = new ShootPattern() {{
						shots = 3;
					}};
					inaccuracy = 10;
					shake = 7f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(2f, 40) {{
						lifetime = 12 * 8f;
						width = height = 22f;
						splashDamage = 70f;
						splashDamageRadius = 30f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						collidesAir = collidesGround = collidesTiles = collides = true;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}},
				new Weapon("oblivion-pow-artillery") {{
					x = -8f;
					y = 7.75f;
					flipSprite = true;
					reload = 90f;
					shoot = new ShootPattern() {{
						shots = 3;
					}};
					inaccuracy = 10;
					shake = 7f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(2f, 40) {{
						lifetime = 12 * 8f;
						width = height = 22f;
						splashDamage = 70f;
						splashDamageRadius = 30f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						collidesAir = collidesGround = collidesTiles = collides = true;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}}
			);
		}};
		expo = new OblivionUnitType("expo") {{
			health = 22000;
			speed = 1.05f;
			armor = 12f;
			flying = lowAltitude = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("3F424D");
			engineDrawer = unit -> {
				for (int i = -1; i <= 2; i += 2) {
					DrawEx.circleEngine(unit, i * 12f, 14f, 4f);
					DrawEx.circleEngine(unit, i * 9f, -11f, 4f);
				}
				DrawEx.circleEngine(unit, 0f, -13f, 8f);
			};
			hitSize = 28f;
			range = 33 * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-pow-weapon") {{
					x = 24.25f;
					y = 0f;
					reload = 10f;
					shake = 5f;
					shootY = 18f;
					shootCone = 15f;
					top = false;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(6f, 100) {{
						lifetime = 5.5f * 8f;
						width = height = 22f;
						despawnEffect = hitEffect = OblivionFx.carmaniteHit;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}}
			);
		}};

		citizen = new UnitType("citizen") {{
			coreUnitDock = true;
			controller = u -> new BuilderAI(true, 500f);
			isEnemy = false;
			envDisabled = 0;
			targetPriority = -2;
			lowAltitude = false;
			mineWalls = false;
			mineFloor = true;
			mineHardnessScaling = false;
			flying = true;
			mineSpeed = 6f;
			mineTier = 3;
			buildSpeed = 1.2f;
			drag = 0.08f;
			speed = 5.6f;
			rotateSpeed = 7f;
			accel = 0.09f;
			itemCapacity = 60;
			health = 300f;
			armor = 1f;
			hitSize = 9f;
			engineSize = 0;
			payloadCapacity = 2f * 2f * tilesize * tilesize;
			pickupUnits = false;
			vulnerableWithPayloads = true;
			constructor = UnitEntity::create;

      fogRadius = 0f;
      targetable = false;
      hittable = false;

			setEnginesMirror(
				new UnitEngine(5.25f, 0f, 2.2f, 45f),
				new UnitEngine(0f, -4.25f, 2.2f, 0f)
			);

			weapons.add(new RepairBeamWeapon(){{
				widthSinMag = 0.11f;
				reload = 20f;
				x = 0f;
				y = 6.5f;
				rotate = false;
				shootY = 0f;
				beamWidth = 0.7f;
				repairSpeed = 3.1f;
				fractionRepairSpeed = 0.06f;
				aimDst = 0f;
				shootCone = 15f;
				mirror = false;
				targetUnits = false;
				targetBuildings = true;
				autoTarget = false;
				controllable = true;
				laserColor = Pal.accent;
				healColor = Pal.accent;
				bullet = new BulletType(){{
					maxRange = 60f;
				}};
			}});
		}};
		mercurie = new OblivionUnitType("mercurie") {{
			health = 250;
			speed = 4f;
			flying = true;
			fallSpeed = 0.01f;
			constructor = CopterComp::new;
			engineSize = 0f;
			envDisabled = Env.space;
			outlineColor = Color.valueOf("3F424D");
			range = 20 * 8f;
			maxRange = range;
			topDrawers.addAll(
				new RotorDrawer("-rotor") {{
					x = 0f;
					y = 2f;
					bladeCount = 4;
					speed = 15f;
				}}
			);
			hitSize = 7;
			weapons.addAll(
				new Weapon("oblivion-mercurie-weapon") {{
					x = 4f;
					y = 5f;
					reload = 10f;
					recoil = 0.5f;
					top = false;
					shootSound = Sounds.lasershoot;
					bullet = new BasicBulletType(2f, 15) {{
						lifetime = 80f;
						shootEffect = LamoniFx.copterShoot;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						weaveScale = 2f;
						weaveMag = 5f;
						trailEffect = LamoniFx.copterTrail;
						trailInterval = 5f;
						trailWidth = 1.8f;
						trailLength = 12;
					}};
				}}
			);
		}};
		aphrodite = new OblivionUnitType("aphrodite") {{
			health = 560;
			speed = 3.5f;
			flying = true;
			fallSpeed = 0.009f;
			engineSize = 0f;
			envDisabled = Env.space;
			range = 25f * 8f;
			hitSize = 9f;
			maxRange = range;
			constructor = CopterComp::new;
			topDrawers.addAll(
				new RotorDrawer("-rotor") {{
					x = 0f;
					y = 4f;
					bladeCount = 4;
					speed = 15.5f;
				}}
			);
			weapons.addAll(
				new Weapon("oblivion-aphrodite-missile") {{
					x = 5f;
					y = 6f;
					reload = 45f;
					top = false;
					shootSound = Sounds.missile;
					inaccuracy = 4f;
					shoot.shots = 2;
					bullet = new MissileBulletType(2.5f, 30) {{
						lifetime = 80f;
						shootEffect = LamoniFx.copterShoot;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						weaveScale = 2f;
						weaveMag = 5f;
						trailEffect = LamoniFx.copterTrail;
						trailInterval = 5f;
						trailWidth = 1.8f;
						trailLength = 12;
					}};
				}},
				new Weapon("oblivion-aphrodite-mount") {{
					x = 3.75f;
					y = -4f;
					reload = 75f;
					top = false;
					shootSound = Sounds.artillery;
					maxRange = 12.5f * 8f;
					bullet = new ArtilleryBulletType(1f, 50) {{
						lifetime = 100f;
						shootEffect = LamoniFx.copterShootBig;
						width = height = 10f;
						trailWidth = 2f;
                                                trailLength = 5;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						collidesAir = collidesGround = collidesTiles = collides;
					}};
				}}
			);
		}};
		apollo = new OblivionUnitType("apollo") {{
			health = 1160;
			speed = 3f;
			flying = true;
			fallSpeed = 0.008f;
			engineSize = 0f;
			envDisabled = Env.space;
			range = 28f * 8f;
			hitSize = 14f;
			maxRange = range;
			constructor = CopterComp::new;
			topDrawers.addAll(
				new RotorDrawer("-rotor") {{
					x = 0f;
					y = 4f;
					bladeCount = 4;
					speed = 17f;
				}}
			);
			weapons.addAll(
				new Weapon("oblivion-apollo-diffuse") {{
					x = 0f;
					y = 10f;
					reload = 10f;
					recoil = 1.5f;
					mirror = false;
					shoot = new ShootSpread(5, 5f);
					shootSound = Sounds.bigshot;
					bullet = new BasicBulletType(2f, 20) {{
						lifetime = 60f;
						shootEffect = Fx.none;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						weaveScale = 2f;
						weaveMag = 5f;
						trailEffect = LamoniFx.copterTrail;
						trailInterval = 5f;
						trailWidth = 1.8f;
						trailLength = 12;
					}};
				}},
				new Weapon("oblivion-apollo-laser") {{
					x = 5f;
					y = -5.5f;
					reload = 60f;
					recoil = 2f;
					top = false;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(50) {{
						width = 12f;
						shootEffect = LamoniFx.copterSparkShoot;
						length = 28f * 8f;
						colors = new Color[]{Color.valueOf("BAF2B7"), Color.valueOf("87B085"), Color.white};
					}};
				}}
			);
		}};
		zeus = new OblivionUnitType("zeus") {{
			health = 8830;
			speed = 2f;
			flying = true;
			fallSpeed = 0.007f;
			engineSize = 0f;
			envDisabled = Env.space;
			range = 32f * 8f;
			hitSize = 20f;
			maxRange = range;
			constructor = CopterComp::new;
			topDrawers.addAll(
				new RotorDrawer("-rotor") {{
					x = 12f;
					y = 2.5f;
					speed = 20f;
					bladeCount = 4;
					mirror = true;
				}}
			);
			weapons.addAll(
				new Weapon("oblivion-zeus-cannon") {{
					x = y = 0f;
					reload = 60f;
					recoil = 4f;
					mirror = false;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(2f, 50) {{
						lifetime = 128f;
						shootEffect = LamoniFx.copterShootBig;
						width = height = 20f;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						collidesAir = collidesGround = collidesTiles = collides;
						bulletInterval = 10f;
						intervalBullet = new LightningBulletType(){{
							damage = 25;
							lightningColor = Color.valueOf("BAF2B7");
							lightningLength = 3;
							lightningLengthRand = 6;
							lightningType = new BulletType(0.0001f, 0f){{
								lifetime = Fx.lightning.lifetime;
								hitEffect = Fx.hitLancer;
								despawnEffect = Fx.none;
								status = StatusEffects.shocked;
								statusDuration = 10f;
								hittable = false;
								lightColor = Color.valueOf("BAF2B7");
							}};
						}};
					}};
				}},
				new Weapon("oblivion-zeus-arc") {{
					x = 8.5f;
					y = 9.5f;
					reload = 10f;
					recoil = 2f;
					shootSound = Sounds.spark;
					bullet = new LightningBulletType(){{
						damage = 70;
						shootEffect = LamoniFx.copterSparkShoot;
						lightningColor = Color.valueOf("BAF2B7");
						lightningLength = 3;
						lightningLengthRand = 6;
						lightningType = new BulletType(0.0001f, 0f){{
							lifetime = Fx.lightning.lifetime;
							hitEffect = Fx.hitLancer;
							despawnEffect = Fx.none;
							status = StatusEffects.shocked;
							statusDuration = 10f;
							hittable = false;
							lightColor = Color.valueOf("BAF2B7");
						}};
					}};
				}},
				new Weapon("oblivion-zeus-arc") {{
					x = 8.5f;
					y = -9.5f;
					reload = 10f;
					recoil = 2f;
					shootSound = Sounds.spark;
					bullet = new LightningBulletType(){{
						damage = 70;
						shootEffect = LamoniFx.copterSparkShoot;
						lightningColor = Color.valueOf("BAF2B7");
						lightningLength = 3;
						lightningLengthRand = 6;
						lightningType = new BulletType(0.0001f, 0f){{
							lifetime = Fx.lightning.lifetime;
							hitEffect = Fx.hitLancer;
							despawnEffect = Fx.none;
							status = StatusEffects.shocked;
							statusDuration = 10f;
							hittable = false;
							lightColor = Color.valueOf("BAF2B7");
						}};
					}};
				}}
			);
		}};
		chronos = new OblivionUnitType("chronos") {{
			health = 25000f;
			speed = 1f;
			flying = true;
			fallSpeed = 0.006f;
			engineSize = 0f;
			envDisabled = Env.space;
			range = 38f * 8f;
			hitSize = 32f;
			maxRange = range;
			constructor = CopterComp::new;
			topDrawers.addAll(
				new RotorDrawer("-rotor") {{
					x = 18f;
					y = 4f;
					speed = 25f;
					bladeCount = 4;
					mirror = true;
				}},
				new RotorDrawer("-rotor") {{
					x = 0f;
					y = -14f;
					speed = 25f;
					bladeCount = 4;
				}}
			);
			weapons.addAll(
				new Weapon("oblivion-chronos-cannon") {{
					x = y = 0f;
					reload = 600f;
					recoil = 6f;
					mirror = false;
					shootSound = Sounds.plasmadrop;
					bullet = new BasicBulletType(1f, 200, "large-orb") {{
						width = height = 30f;
						lifetime = 10f;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
						trailChance = 5f;
						trailWidth = 4f;
						trailLength = 12;
					}};
				}},
				new Weapon() {{
					x = 12f;
					y = 4f;
					reload = 10f;
					recoil = 0f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(4f, 69, "missile-large") {{
						width = height = 18f;
						lifetime = 76f;
						frontColor = trailColor = Color.valueOf("BAF2B7");
						backColor = Color.valueOf("87B085");
					}};
				}}
			);
		}};
 
		latrodectus = new UnitType("latrodectus") {{
			health = 320;
			speed = 1f;
			range = 20f * 8f;
			maxRange = range;
			constructor = LegsUnit::create;

			legStraightness = 0.3f;
			stepShake = 0f;
			legCount = 6;
			legLength = 8f;
			lockLegBase = true;
			legContinuousMove = true;
			legExtension = -2f;
			legBaseOffset = 3f;
			legMaxLength = 1.1f;
			legMinLength = 0.2f;
			legLengthScl = 0.96f;
			legForwardScl = 1.1f;
			legGroupSize = 3;
			rippleScale = 0.2f;
			legMoveSpace = 1f;
			allowLegStep = true;
			hovering = true;
			legPhysicsLayer = false;

			weapons.add(
				new Weapon("oblivion-latrodectus-mount") {{
					x = y = 0f;
					reload = 120f;
					shootY = 6f;
					bullet = new DelayDamageBulletType(2f, 12) {{
						draw = b -> {
							float p = b.lifetime/b.type.lifetime;
							float sin = Mathf.absin(5f, 1f);
							Draw.color(Pal.lancerLaser);
							Lines.stroke((1f + sin));
							Lines.circle(b.x, b.y, (40f + sin));
							for (int i = 0; i < 4; i++) {
								float lx = b.x + Angles.trnsx(Time.time + (i * 90f), (40f + sin) * p, 0f);
								float ly = b.y + Angles.trnsy(Time.time + (i * 90f), (40f + sin) * p, 0f);
								Lines.lineAngle(lx, ly, Angles.angle(x, y, lx, ly), 10f);
							}
							for (int i = 0; i < 4; i++) {
								float lx = b.x + Angles.trnsx(-(Time.time + (i * 90f)), (40f + sin) * p, 0f);
								float ly = b.y + Angles.trnsy(-(Time.time + (i * 90f)), (40f + sin) * p, 0f);
								Lines.lineAngle(lx, ly, Angles.angle(x, y, lx, ly) + 180f, 10f);
							}
							Draw.color();
							Lines.stroke(((1f + sin)/2f) * p);
							Lines.circle(b.x, b.y, (40f + sin) * p);

							Draw.color(Pal.lancerLaser);
							Fill.circle(b.x, b.y, 3f + sin);
							Draw.color();
							Fill.circle(b.x b.y, (3f + sin)/2f);
						};
						delayTime = 40f;
						damageRadius = 20f;
						extraDamage = 0.6f;
					}};
				}}
			);
		}};
	}
}
