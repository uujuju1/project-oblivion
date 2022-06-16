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

	Block getBlock(Vec3 pos) {
		float temp = getTemperature(pos) * 3f;
		float height = rawHeight(pos) * 5f;
		for (int i = 0; i < 3; i++) {
			if (temp > i && temp < i + 1) {
				for (int j = 0; j < 5; j++) {
					if (height > j && height < j + 1) {
						return arr[(int) Mathf.clamp(i, 0, 2)][(int) Mathf.clamp(j, 0, 4)];
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
		return getBlock(pos).mapColor;
	}

	@Override
	protected void generate() {
		float temp = getTemperature(sector.tile.v);
		float height = rawHeight(sector.tile.v);

		// base
		pass((x, y) -> {
			float hnoise = noise(x + 782, y, 7, 0.8f, 280f, 1f);
			floor = Blocks.stone;
			for (int i = 0; i < 3; i++) {
				if (temp > i && temp < i + 1) {
					for (int j = 0; j < 5; j++) {
						if (height > j && height < j + 1) {
							if (hnoise > 0.66f) {
								floor = arr[(int) Mathf.clamp(i, 0, 2)][(int) Mathf.clamp(j + 1, 0, 4)];
							} else {
								floor = arr[(int) Mathf.clamp(i, 0, 2)][(int) Mathf.clamp(j, 0, 4)];
							}
						}
					}
				}
			}
		});

		cells(5);
		float length = width/2.6f;
    Vec2 trns = Tmp.v1.trns(rand.random(320f), length);
    int
    spawnX = (int)(trns.x + width/2f), spawnY = (int)(trns.y + height/2f),
    endX = (int)(-trns.x + width/2f), endY = (int)(-trns.y + height/2f);
    float maxd = Mathf.dst(width/2f, height/2f);

    erase(spawnX, spawnY, 15);
    brush(pathfind(spawnX, spawnY, endX, endY, tile -> (tile.solid() ? 300f : 0f) + maxd - tile.dst(width/2f, height/2f)/10f, Astar.manhattan), 9);
    erase(endX, endY, 15);

    inverseFloodFill(tiles.getn(spawnX, spawnY));
    erase(endX, endY, 6);
    tiles.getn(endX, endY).setOverlay(Blocks.spawn);
    blend(OblivionEnvironment.carmebonite, OblivionEnvironment.boronite, 4);

		Schematics.placeLaunchLoadout(spawnX, spawnY);
	}
}