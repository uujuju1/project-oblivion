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
	public float heightScl = 1f, minHeight = 0.3f, octaves = 3, persistence = 0.5f;
	public static int seed = 69; 

	public Block[] heightMap = {Blocks.water, Blocks.sand, Blocks.dirt, Blocks.stone, Blocks.snow};

	float rawHeight(Vec3 pos) {
		return Simplex.noise3d(seed, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}

	Block getBlock(Vec3 pos) {
		float height = rawHeight(pos) * heightMap.length;
		for (int i = 0; i < heightMap.length; i++) {
			if (height > i && height < i + 1) {
				return heightMap[Mathf.clamp(i, 0, heightMap.length)];
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
		floor = Blocks.stone;
		block = Blocks.air;
		ore = Blocks.air;
	}
}