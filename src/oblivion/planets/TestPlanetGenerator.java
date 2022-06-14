package oblivion.planets;

import arc.math.*;
import arc.util.*;
import arc.struct.*;
import arc.graphics.*;
import arc.math.geom.*;
import arc.util.noise.*;
import mindustry.ai.*;
import mindustry.game.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.maps.planet.*;
import mindustry.ai.BaseRegistry.*;
import mindustry.maps.generators.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.world.blocks.environment.*;
import oblivion.content.*;

import static mindustry.Vars.*;

public class TestPlanetGenerator extends PlanetGenerator {
	public float heightScl = 1f, minHeight = 0.1f, octaves = 12, persistence = 0.5f;
	public float tempScl = 1f, tempOctaves = 3, tempPersistence = 0.5f;
	public static int seed = 69, tempSeed = 420, dustSeed = 96; 

	public Block[][] arr = {
		{
			OblivionEnvironment.paletolime, 
			OblivionEnvironment.malenatite,
			OblivionEnvironment.goletenira,
			OblivionEnvironment.argeletine,
			OblivionEnvironment.mudone
		},
		{
			OblivionEnvironment.argeletine,
			OblivionEnvironment.mudone, 
			OblivionEnvironment.boronite, 
			OblivionEnvironment.tarrobonite, 
			OblivionEnvironment.carmebonite,
		},
		{
			OblivionEnvironment.argeletine,
			OblivionEnvironment.mudone, 
			OblivionEnvironment.boronite, 
			OblivionEnvironment.tarrobonite, 
			OblivionEnvironment.carmebonite,
		}
	};

	float getTemperature(Vec3 pos) {
		return Simplex.noise3d(tempSeed, tempOctaves, tempPersistence, tempScl, pos.x, pos.y, pos.z);
	}
 
	float rawHeight(Vec3 pos) {
		return Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}

	Block getBlock(Vec3 pos, int offset1, int offset2) {
		float temp = getTemperature(pos) * 3f;
		float height = rawHeight(pos) * 5f;
		for (int i = 0; i < 3; i++) {
			if (temp > i && temp < i + 1) {
				for (int j = 0; j < 5; j++) {
					if (height > j && height < j + 1) {
						return arr[(int) Mathf.clamp(i + offset1, 0, 2)][(int) Mathf.clamp(j + offset2, 0, 4)];
					}
				}
			}
		}

		return Blocks.stone;
	}

	@Override
	public float getHeight(Vec3 pos) {
		return Math.max(minHeight, rawHeight(pos));
	}

	@Override
	public void generateSector(Sector sector) {}

	@Override
	public Color getColor(Vec3 pos) {
		return getBlock(pos, 0, 0).mapColor;
	}

	@Override
	protected void generate() {
		float temp = getTemperature(sector.tile.v);
		float height = rawHeight(sector.tile.v);

		pass((x, y) -> {
			floor = getBlock(sector.tile.v, 0, 0);
		});

		pass((x, y) -> {
			float noise = noise(x, y, 7, 0.8f, 280f, 1f);
			if (noise > 0.5f) {
				floor = getBlock(sector.tile.v, 0, 1);
			}
		});

		Schematics.placeLaunchLoadout(50, 50);
	}
}