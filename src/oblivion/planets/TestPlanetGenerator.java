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
	public float dustScl = 1f, dustOctaves = 6, dustPersistence = 0.5f;
	public static int seed = 69, tempSeed = 420, dustSeed = 96; 

	public Block[] heightMap = 
		{
		OblivionEnvironment.paletolime,
		OblivionEnvironment.malenatite,
		OblivionEnvironment.goletenira, 
		OblivionEnvironment.argeletine,
		OblivionEnvironment.mudone,
		OblivionEnvironment.tarrobonite,
		OblivionEnvironment.tarrobonite,
		OblivionEnvironment.boronite,
		OblivionEnvironment.boronite,
		OblivionEnvironment.carmebonite
		};

	// bigger value, more powder'ish the ground is
	float getDustyness(Vec3 pos) {
		return Simplex.noise3d(dustSeed, dustOctaves, dustPersistence, dustScl, pos.x, pos.y, pos.z);
	}
	// bigger value, colder it is
	float getTemperature(Vec3 pos) {
		return Simplex.noise3d(tempSeed, tempOctaves, tempPersistence, tempScl, pos.x, pos.y, pos.z);
	}
 
	float rawHeight(Vec3 pos) {
		return Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}

	Block getBlock(Vec3 pos) {
		float height = rawHeight(pos) * heightMap.length;
		for (int i = 0; i < heightMap.length; i++) {
			if (height > i && height < i + 1) {
				return heightMap[(int) Mathf.clamp(i - getDustyness(pos) + getTemperature(pos), 0, heightMap.length)];
			}
		}
		return heightMap[heightMap.length];
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
		float temp = getTemperature(sector.tile.v);
		float dust = getDustyness(sector.tile.v);
		float height = rawHeight(sector.tile.v);

		pass((x, y) -> {
			floor = getBlock(sector.tile.v);
		});

		pass((x, y) -> {
			float noise = noise(x, y, 7, 0.8f, 280f, 1f);

			if (temp < 0.7f && dust > 0.7f && height > 0.3f && noise > 0.5f ) {
				floor = OblivionEnvironment.boronite;
			}
		});

		Schematics.placeLaunchLoadout(50, 50);
	}
}