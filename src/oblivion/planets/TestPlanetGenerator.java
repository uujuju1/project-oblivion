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
	public float heightScl = 0.9f, minHeight = 0.1f, octaves = 12, persistence = 0.6f;
	public float humidScl = 0.5f, humidOctaves = 7, humidPersistence = 0.5f;
	public static int seed = 69, humidSeed = 420;

	public Block[] arr = {
		OblivionEnvironment.paletolime, 
		OblivionEnvironment.paletolime,
		OblivionEnvironment.paletolime, 
		OblivionEnvironment.paletolime,
		OblivionEnvironment.malenatite,
		OblivionEnvironment.goletenira,
		OblivionEnvironment.argeletine,
		OblivionEnvironment.mudone,
		OblivionEnvironment.mudone
	};
 
	float rawHeight(Vec3 pos) {
		return Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}
	float humidity(Vec3 pos) {
		return Simplex.noise3d(humidSeed, humidOctaves, humidPersistence, humidScl, pos.x, pos.y, pos.z);
	}

	Block getBlock(Vec3 pos) {
		float height = 1 - rawHeight(pos);
		float humidity = humidity(pos);
		return arr[Mathf.clamp((int) (height + humidity * arr.length), 0, arr.length -1)];
	}

	@Override
	public float getHeight(Vec3 pos) {
		return Math.max(minHeight, rawHeight(pos));
	}

	@Override
	public void generateSector(Sector sector) {}

	@Override
	public Color getColor(Vec3 pos) {
		return getBlock(pos).mapColor;
	}

	@Override
	protected float noise(float x, float y, float octaves, float falloff, float scale, float magnitude) {
		return Simplex.noise2d(seed, octaves, falloff, 1f / scale, x, y) * (float)magnitude;
	}

	@Override
	protected void generate() {
		float sHeight = rawHeight(sector.tile.v);
		float sHumidity = humidity(sector.tile.v);

		pass((x, y) -> {
			// sand plains as base
			floor = OblivionEnvironment.goletenira;
			if (sHeight < 0.35f) {
				float noise  = noise(x, y, 12, 0.7f, 40f, 1f);
				if (noise > 0.6) {
					floor = OblivionEnvironment.paletolime;			
				}
			}
			// mounainous dry regions;
			if (sHumidity < 0.4f && sHeight > 0.60f) {
				float noise  = noise(x, y, 12, 0.7f, 60f, 1f);
				if (noise > 0.75f) {
					floor = OblivionEnvironment.goletenira;
				} else {
					floor = OblivionEnvironment.malenatite;
				}
			}
			// mountain base wetter regions
			if (sHumidity > 0.6f && sHeight < 0.5f) {
				float noise  = noise(x, y, 12, 0.7f, 60f, 1f);
				if (noise > 0.56f) {
					floor = OblivionEnvironment.mudone;
				} else {
					floor = OblivionEnvironment.argeletine;
				}
			}
		});
		Schematics.placeLaunchLoadout(100, 100);
	}
}