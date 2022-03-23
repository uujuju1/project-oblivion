package oblivion.content;

import arc.graphics.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.ctype.ContentList;

public class OblivionUnits implements ContentList {
	public static UnitType slop, detra, tedri;

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
					shots = 5f;
					shotDelay = 10f;
					shake = 3f;
					shootSound = Sounds.shootBig;
					bullet = new BasicBulletType(3f, 34) {{
						width = height = 9f;
						lifetime = 66.6f; //ohno ohfu-
					}};
				}}
			);
		}};
	}
}