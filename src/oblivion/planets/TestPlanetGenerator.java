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
		return Simplex.noise3d(69, octaves, persistence, heightScl, pos.x, pos.y, pos.z);
	}
	float humidity(Vec3 pos) {
		return Simplex.noise3d(420, humidOctaves, humidPersistence, humidScl, pos.x, pos.y, pos.z);
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
		return Math.max(0.1f, rawHeight(pos));
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
	protected float noise3d(int seed, Vec3 p, double octaves, double falloff, double scl, double mag) {
		return Simplex.noise3d(seed, octaves, falloff, 1f / scl, p.x, p.y, p.z) * (float)mag;
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
		
		// inverseFloodFill() wasn't working soo
		for(Tile tile : tiles){
			if(tile.block() == Blocks.air){
				tile.setBlock(tile.floor().wall);
			}
		}

		// create rooms
		for (int i = 0; i < 20; i++) {
			Vec2 rotate = Tmp.v1.trns(noise3d(i, sector.tile.v, 3, 0.5f, 200f, 360f), width/(2.5f + noise3d(i + 21, sector.tile.v, 3, 0.5f, 200f, 2f)));
			int roomX = (int)(trns.x + width/2f), roomY = (int)(trns.y + height/2f);
			rooms.add(
				new Vec2(rotate.x + width/2f, rotate.y + height/2f)
			);
		}

		// connect rooms
		rooms.each(r -> {
			int roomId = 0;
			// get room to connect
			Vec2 to = rooms.get((int) noise3d(roomId + 42, sector.tile.v, 3, 0.5f, 200f, rooms.size - 1));

			// if it tries to connect to itself, it'll connect to spawn instead
			to = to == r ? to : rooms.get(0);

			// actually connect the rooms
			erase((int) r.x, (int) r.y, (int) noise3d(roomId + 63, sector.tile.v, 3, 0.5f, 200f, 12f));
			brush(pathfind((int) r.x, (int) r.y, (int) to.x, (int) to.y, tile -> 300f, Astar.manhattan), 9);
			roomId++;
		});

		// mostly guaranteed path to the units
		brush(pathfind(spawnX, spawnY, launchX, launchY, tile -> 300f, Astar.manhattan), 9);
	
		// make connections look more natural
		distort(136f, 31f);
		distort(10f, 12f);
		distort(5f, 7f);
		median(4);

		// make core and enemy area
		erase(spawnX, spawnY, 8);
		erase(launchX, launchY, 8);

		// ores
		float poles = 1f - Math.abs(sector.tile.v.y);
		pass((x, y) -> {
			if (noise(x, y, 10, 0.3f, 30f, 1f) > 0.75f && block == Blocks.air) ore = OblivionEnvironment.oreNiobium;

			if (noise(x, y, 1, 0.2f, 40f, 1f) > 1f * poles && block != Blocks.air) ore = OblivionEnvironment.wallOreHafnium;

			// remove invalid ores
			if (ore == OblivionEnvironment.wallOreHafnium && !nearAir(x, y)) ore = Blocks.air;
			if (ore == OblivionEnvironment.wallOreHafnium && noise(x, y, 4, 0.5f, 167f) > 0.4f) ore = Blocks.air;

			if (ore == OblivionEnvironment.oreNiobium && block != Blocks.air) ore = Blocks.air;
		});

		// put core and enemy spawn in the map
		tiles.getn(launchX, launchY).setOverlay(Blocks.spawn);
		Schematics.placeLaunchLoadout(spawnX, spawnY);
	}
}