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
	Block getBlock(float x, float y, float z) {
		Vec3 pos = new Vec3(x, y, z);
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
	protected float noise(float x, float y, double octaves, double falloff, double scl, double mag) {
		return Simplex.noise2d(seed, octaves, falloff, 1f / scl, x, y) * (float)mag;
	}

	@Override
	protected void generate() {

		Seq<Vec2> rooms = new Seq<>();
		float maxd = Mathf.dst(width/2f, height/2f);

		// enemy and player rooms
		Vec2 trns = Tmp.v1.trns(rand.random(360f), width/2.6f);
		int
		spawnX = (int)(trns.x + width/2f), spawnY = (int)(trns.y + height/2f),
		launchX = (int)(-trns.y + width/2f), launchY = (int)(-trns.y + height/2f);
		rooms.add(
			new Vec2(spawnX, spawnY),
			new Vec2(launchX, launchY)
		);

		// floor
		pass((x, y) -> {
			floor = getBlock(x / (width * 0.5f), y / (height * 0.5f), sector.tile.v.z);
		});
		distort(125f, 72f);
		
		// walls
		for(Tile tile : tiles){
			if(tile.block() == Blocks.air){
				tile.setBlock(tile.floor().wall);
			}
		}

		// create rooms
		for (int i = 0; i < 10; i++) {
			Vec2 rotate = Tmp.v1.trns(rand.random(360f), width/(2.5f + rand.random(1f)));
			int roomX = (int)(trns.x + width/2f), roomY = (int)(trns.y + height/2f);
			rooms.add(
				new Vec2(rotate.x + width/2f, rotate.y + height/2f)
			);
		}
		rooms.each(r -> {
			erase((int) r.x, (int) r.y, rand.random((int) 12));
			brush(pathfind((int) r.x, (int) r.y, (int) rooms.get(rand.random((int) rooms.size - 1)).x, (int) rooms.get(rand.random((int) rooms.size - 1)).y, tile -> (tile.solid() && tile.block() == OblivionEnvironment.goletenira ? 300f : 0f) + maxd - tile.dst(width/2f, height/2f)/10f, Astar.manhattan), 9);
		});

		// natural rooms
		median(4);
		distort(136f, 31f);
		erase(spawnX, spawnY, 8);
		erase(launchX, launchY, 8);
		brush(pathfind(spawnX, spawnY, launchX, launchY, tile -> (tile.solid() && tile.block() == OblivionEnvironment.goletenira ? 300f : 0f) + maxd - tile.dst(width/2f, height/2f)/10f, Astar.manhattan), 9);
		
		// ores
		float poles = 1f - Math.abs(sector.tile.v.y);
		pass((x, y) -> {
			if (noise(x, y, 10, 0.3f, 30f, 1f) > 0.7f && block == Blocks.air) ore = OblivionEnvironment.oreNiobium;

			if (noise(x, y, 1, 0.2f, 40f, 1f) > 1f * poles && block != Blocks.air) ore = OblivionEnvironment.wallOreHafnium;

			// remove invalid ores
			if (ore == OblivionEnvironment.wallOreHafnium && !nearAir(x, y)) ore = Blocks.air;

			if (ore == OblivionEnvironment.oreNiobium && block != Blocks.air) ore = Blocks.air;
		});

		// core and spawn
		tiles.getn(launchX, launchY).setOverlay(Blocks.spawn);
		Schematics.placeLaunchLoadout(spawnX, spawnY);
	}
}