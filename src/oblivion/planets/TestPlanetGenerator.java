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
			OblivionEnvironment.carmebonite,
			OblivionEnvironment.porotrate, 
			OblivionEnvironment.amonetha, 
			OblivionEnvironment.lathanite, 
			OblivionEnvironment.methystane,
		}
	};
 
	float rawHeight(Vec3 pos) {
		return Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}

	int getBiome(Vec3 pos) {
		return Mathf.round(Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z) * 2);
	}

	Block getBlock(Vec3 pos) {
		float poles = Math.abs(sector.tile.v.y);
		for (int i = 0; i < 3; i++) {
			return arr[Mathf.clamp(getBiome(pos), 0, 2)][2];
		}
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
		pass((x, y) -> {
			floor = getBlock(sector.tile.v);
		});
	}
}