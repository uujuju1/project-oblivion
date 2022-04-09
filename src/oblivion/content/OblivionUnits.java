package oblivion.content;

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
import oblivion.type.draw.*;

public class OblivionUnits implements ContentList {
	public static UnitType 
	slop, detra, tedri, taleni, kolete,
	pioli, taneki, notremite, dopretile, niboletra;

	@Override
	public void load() {
		slop = new UnitType("slop") {{
			health = 240;
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
						lifetime = 101f;
						collides = true;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		tedri = new UnitType("tedri") {{
			health = 860;
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
			health = 6000;
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
					bullet = new LaserBulletType(150) {{
						width = 10f;
						length = 256f;
						colors = new Color[]{OblivionPal.mesoDark, OblivionPal.mesoMedium, OblivionPal.mesoLight};
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15.25f;
					y = 4f;
					reload = 60f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(2f, 50) {{
						width = height = 10f;
						lifetime = 128f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15f;
					y = -14f;
					reload = 60f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(2f, 50) {{
						width = height = 10f;
						lifetime = 128f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};
		kolete = new UnitType("kolete") {{
			health = 22000;
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
					reload = 30f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(4f, 200) {{
						drag = 0.04f;
						width = height = 12f;
						lifetime = 300f;
						frontColor = OblivionPal.mesoMedium;
						backColor = OblivionPal.mesoDark;
					}};
				}}
			);
		}};

		pioli = new UnitType("pioli") {{
			health = 200;
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
			speed = 0.7f;
			flying = true;
			constructor = UnitEntity::create;
			engineDrawer = new EngineDrawer(unit -> {
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
			});
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
						lifetime = 60f;
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
	}
}