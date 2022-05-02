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
import mindustry.entities.bullet.*;
import mindustry.entities.abilities.*;
import mindustry.ctype.ContentList;
import oblivion.type.*;
import oblivion.graphics.*;

public class OblivionUnits implements ContentList {
	public static UnitType 
	slop, detra, tedri, taleni, kolete,
	pioli, taneki, notremite, dopretile, niboletra,
	phi, root,

	republic, giga, archaranid, bloodmoon, yetinus;

	@Override
	public void load() {
		slop = new UnitType("slop") {{
			health = 240;
			armor = 3f;
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
					}};
				}}
			);
		}};
		detra = new UnitType("detra") {{
			health = 450;
			armor = 4f;
			speed = 1.7f;
			flying = true;
			constructor = UnitEntity::create;
			hitSize = 8f;
			range = 152f;
			maxRange = range;
			engineOffset = 2f;
			weapons.add(
				new Weapon("oblivion-mesulfate-artillery") {{
					x = 4.5f;
					y = -1.5f;
					reload = 60f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(1.5f, 30) {{
					        hitEffect = Fx.blastExplosion;
						lifetime = 101f;
						knockback = 0.6f;
						collides = true;
						collidesTiles = true;
		                                splashDamageRadius = 35f;
                                                splashDamage = 60f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		tedri = new UnitType("tedri") {{
			health = 740;
			armor = 6f;
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
					shots = 5;
					shotDelay = 10f;
					shake = 3;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(3f, 34) {{
						width = height = 9f;
						lifetime = 66.6f; //ohno ohfu-
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		taleni = new UnitType("taleni") {{
			health = 5500;
			armor = 11f;
			speed = 1f;
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
					shots = 3;
					shotDelay = 5f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(6f, 60) {{
						width = height = 10f;
						lifetime = 50f;
						splashDamage = 70f;
                                                splashDamageRadius = 10f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15f;
					y = -14f;
					reload = 35f;
					shots = 3;
					shotDelay = 5f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(6f, 60) {{
						width = height = 10f;
						lifetime = 50f;
						splashDamage = 70f;
                                                splashDamageRadius = 10f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		kolete = new UnitType("kolete") {{
			health = 22000;
			armor = 15f;
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
					bullet = new LaserBulletType(100) {{
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
					shots = 6;
					inaccuracy = 15f;
					velocityRnd = 0.6f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(11f, 200) {{
						drag = 0.04f;
						width = height = 12f;
						lifetime = 300f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
						fragBullets = 5;
						fragBullet = new BasicBulletType(8f, 45) {{
					                homingPower = 1f;
							homingRange = 400f;
							lifetime = 100f;
							hitSound = despawnSound = Sounds.artilery;
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
			armor = 1f;
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
			armor = 3f;
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
			armor = 6f;
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
			speed = 1.2f;
			armor = 8f;
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
					bullet = new LaserBulletType(150) {{
						width = 9f;
						length = 224f;
						colors = new Color[]{OblivionPal.copreDark, OblivionPal.copreMedium, OblivionPal.copreLight};
					}};
				}},
				new Weapon("oblivion-copremite-laser") {{
					x = 6.25f;
					y = 12.75f;
					reload = 15f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(2.5f, 65) {{
						width = 9f;
						height = 12f;
						lifetime = 89.6f;
						status = OblivionStatuses.infested;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}},
				new Weapon("oblivion-copremite-laser") {{
					x = 12.25f;
					y = -8.25f;
					reload = 15f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(2.5f, 65) {{
						width = 9f;
						height = 12f;
						lifetime = 89.6f;
						status = OblivionStatuses.infested;
						frontColor = OblivionPal.copreLight;
						backColor = OblivionPal.copreMedium;
					}};
				}}
			);
		}};
		niboletra = new OblivionUnitType("niboletra") {{
			health = 20000;
			armor = 12f;
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
						lifetime = 80f;
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
			rotateSpeed = 0.7f;
			armor = 20f;
			mechStepParticles = true;
			mechStepShake = 0.75f;
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
					shots = 3;
					shotDelay = 5f;
					shake = 5f;
					top = false;
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
			landShake = 1.5f;
			rotateSpeed = 1.5f;
			drownTimeMultiplier = 6f;
			commandLimit = 8;
			constructor = LegsUnit::create;
			legCount = 6;
			legLength = 30f;
			legBaseOffset = 15f;
			legMoveSpace = 1.5f;
			legTrns = 0.58f;
			hovering = true;
			visualElevation = 0.2f;
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
					alternate = false;
					bullet = new MissileBulletType(4f, 75) {{
						width = height = 15f;
						lifetime = 100f;
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
					firstShotDelay = Fx.greenLaserCharge.lifetime;
					top = false;
					mirror = false;
					continuous = true;
					bullet = new ContinuousLaserBulletType(210) {{
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
			health = 60000;
			armor = 17f;
			speed = 0.3f;
			legCount = 8;
			legMoveSpace = 1.2f;
			legPairOffset = 3;
			legLength = 100f;
			legExtension = -20;
			legBaseOffset = 10f;
			landShake = 1f;
			legLengthScl = 0.93f;
			legSpeed = 0.19f;
			legSplashDamage = 80;
			legSplashRange = 60;
			hitSize = 32f;
			hovering = true;
			visualElevation = 0.95f;
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
					bullet = new ArtilleryBulletType(2f, 180) {{
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
					bullet = new LaserBulletType(150) {{
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
				Draw.color(unit.team.color);;
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, -15, -35), unit.y + Angles.trnsy(unit.rotation - 90, -15, -35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 15, -35), unit.y + Angles.trnsy(unit.rotation - 90, 15, -35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, -20, 35), unit.y + Angles.trnsy(unit.rotation - 90, -20, 35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 20, 35), unit.y + Angles.trnsy(unit.rotation - 90, 20, 35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation);
				
				Draw.color();
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, -20, -30), unit.y + Angles.trnsy(unit.rotation - 90, -20, -30), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation / 2);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 20, -30), unit.y + Angles.trnsy(unit.rotation - 90, 20, -30), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation / 2);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, -25, 35), unit.y + Angles.trnsy(unit.rotation - 90, -25, 35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation / 2);
				Fill.circle(unit.x + Angles.trnsx(unit.rotation - 90, 25, 35), unit.y + Angles.trnsy(unit.rotation - 90, 25, 35), (10 + Mathf.absin(Time.time, 2, 10 / 4)) * unit.elevation / 2);
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
						splashDamage = 150f;
						collides = collidesTiles = collidesAir = collidesGround = true;
						hitSound = despawnSound = Sounds.plasmaboom;
						hitEffect = despawnEffect = OblivionFx.bloodmoonHit;
						fragBullets = 3;
						fragBullet = new BasicBulletType(1f, 60) {{
							homingPower = 0.03f;
							homingRange = 400f;
							lifetime = 300f;
							splashDamageRadius = 40f;
						        splashDamage = 50f;
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

		yetinus = new OblivionUnitType("yetinus") {{
			health = 65000;
			speed = 0.3f;
			drag = 0.18f;
			hitSize = 58f;
			armor = 25f;
			accel = 0.19f;
			rotateSpeed = 0.3f;
			rotateShooting = true;
			constructor = UnitWaterMove::create;
			trailLength = 70;
			trailX = 23f;
			trailY = -32f;
			trailScl = 3.5f;
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
					bullet = new BasicBulletType(6f, 0) {{
						shootEffect = Fx.railShoot;
						lifetime = 100;
						splashDamageRadius = 180f;
						splashDamage = 850f;
						width = height = 40;
						homingRange = 600f;
						homingPower = 0.1f;
						hitEffect = Fx.impactShockwave;
						hitColor = Pal.bulletYellow;
						smokeEffect = Fx.shootBig2;
					}};
				}},
				new Weapon("oblivion-yetinus-laser") {{
					x = 0f;
					y = 0f;
					reload = 30f;
					rotate = true;
					rotateSpeed = 1.5f;
					mirror = false;
					rotate = true;
					recoil = 2f;
					shootY = 4f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(150) {{
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
			outlineColor = Color.valueOf("2E2B2A");
			range = 18f * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-phi-weapon") {{
					x = 5.25f;
					y = 0.5f;
					reload = 30f;
					top = false;
					bullet = new BasicBulletType(2f, 20) {{
						lifetime = 18 * 4f;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
					}};
				}}
			);
		}};
		root = new OblivionUnitType("root") {{
			health = 920;
			speed = 2f;
			flying = true;
			constructor = UnitEntity::create;
			outlineColor = Color.valueOf("2E2B2A");
			range = 23 * 8f;
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-root-weapon") {{
					x = 8f;
					y = -0.75f;
					reload = 60f;
					top = false;
					bullet = new ArtilleryBulletType(1f, 60) {{
						lifetime = 23 * 8f;
						frontColor = Color.white;
						backColor = Color.valueOf("DCDCDC");
						homingRange = 80f;
						homingPower = 0.05f;
					}};
				}}
			);
		}};
	}
}
