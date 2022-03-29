package oblivion.content;

import arc.graphics.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.abilities.*;
import mindustry.ctype.ContentList;

public class OblivionUnits implements ContentList {
	public static UnitType slop, detra, tedri, taleni, kolete;

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
					reload = 30f;
					shootSound = Sounds.missile;
					bullet = new MissileBulletType(2f, 15) {{
						lifetime = 52f;
						status = StatusEffects.burning;
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
			weapons.add(
				new Weapon("oblivion-mesulfate-artillery") {{
					x = 4.5f;
					y = -1.5f;
					reload = 60f;
					shootSound = Sounds.artillery;
					bullet = new ArtilleryBulletType(1.5f, 30) {{
						lifetime = 101f;
						collides = true;
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
			weapons.add(
				new Weapon("oblivion-mesulfate-laser") {{
					x = 5.75f;
					y = -3.5f;
					reload = 120f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(50) {{
						width = 8f;
						length = range;
						colors = new Color[]{Color.valueOf("AD4747"), Color.valueOf("E86F6F"), Color.valueOf("F79797")};
					}};
				}},
				new Weapon("oblivion-mesulfate-cannon") {{
					x = 0f;
					y = 6f;
					reload = 150f;
					shots = 5;
					shotDelay = 10f;
					shake = 3;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(3f, 34) {{
						width = height = 9f;
						lifetime = 66.6f; //ohno ohfu-
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
			maxRange = range;
			weapons.add(
				new Weapon("oblivion-mesulfate-railgun") {{
					x = y = 0f;
					reload = 180f;
					shake = 3f;
					shootSound = Sounds.plasmadrop;
					bullet = new LaserBulletType(150) {{
						width = 10f;
						length = maxRange;
						colors = new Color[]{Color.valueOf("AD4747"), Color.valueOf("E86F6F"), Color.valueOf("F79797")};
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15.25f;
					y = 4f;
					reload = 60f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(50, 2f) {{
						width = height = 10f;
						lifetime = 128f;
					}};
				}},
				new Weapon("oblivion-mesulfate-big-mount") {{
					x = 15f;
					y = -14f;
					reload = 60f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(50, 2f) {{
						width = height = 10f;
						lifetime = 128f;
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
			abilities.add(
				new EnergyFieldAbility(40f, 70f, 160f) {{
					hitBuildings = false;
					color = Color.valueOf("E86F6F");
				}}
			);
			weapons.add(
				new Weapon("oblivion-mesulfate-big-laser") {{
					x = 26.5f;
					y = 25.75f;
					reload = 60f;
					shootSound = Sounds.laser;
					bullet = new LaserBulletType(100) {{
						width = 11f;
						length = maxRange;
						colors = new Color[]{Color.valueOf("AD4747"), Color.valueOf("E86F6F"), Color.valueOf("F79797")};
					}};
				}},
				new Weapon("oblivion-mesulfate-mine") {{
					x = 26.25f;
					y = -12f;
					reload = 30f;
					shootSound = Sounds.artillery;
					bullet = new BasicBulletType(4f, 200) {{
						drag = 0.04f;
						width = height = 12f;
						lifetime = 300f;
					}};
				}}
			);
		}};
	}
}