package oblivion.content;

import mindustry.gen.*;
import mindustry.type.*;
import mindustry.entities.bullet.*;
import mindustry.ctype.ContentList;

public class OblivionUnits implements ContentList {
	public static UnitType slop, detra;

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
					bullet = new MissileBulletType(2f, 15) {{lifetime = 52f;}};
				}}
			);
		}};
		detra = new UnitType("detra") {{
			health = 450;
			speed = 1.7f;
			flying = true;
			constructor = new UnitEntity::create;
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
	}
}