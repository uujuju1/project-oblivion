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
	protected void generate() {
		float sHeight = 1 - rawHeight(sector.tile.v);
		float sHumidity = humidity(sector.tile.v);
		pass((x, y) -> {
			float height = noise(x + 150, y, 3, 0.5f, 1, 1);
			float humidity = noise(x, y + 150, 6, 0.4f, 1, 1);
			floor = getBlock(sector.tile.v);

			int offset = 0;
			if (humidity > 0.5f) {
				offset++;
			} else offset--;
			if (height > 0.5f) {
				offset--;
			} else offset++;

			floor = arr[Mathf.clamp(((int) (sHeight + sHumidity * arr.length)) + offset, 0, arr.length -1)];
		});
		Schematics.placeLaunchLoadout(100, 100);
	}
}