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
import mindustry.ai.Astar.*;
import mindustry.maps.planet.*;
import mindustry.ai.BaseRegistry.*;
import mindustry.maps.generators.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.world.blocks.environment.*;
import oblivion.content.*;

import static mindustry.Vars.*;

public class LamoniPlanetGenerator extends PlanetGenerator {
	public static final int seed = 69;
	public static int widthSeed = 1, heightSeed = 2, roomSeed = 3, strokeSeed = 4;

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
		return Simplex.noise3d(seed, 12, 0.6f, 0.9f, pos.x, pos.y, pos.z);
	}
	float humidity(Vec3 pos) {
		return Simplex.noise3d(420, 7, 0.5f, 0.5f, pos.x, pos.y, pos.z);
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
	public Seq<Tile> pathfind(int startX, int startY, int endX, int endY, TileHueristic th, DistanceHeuristic dh){
		return Astar.pathfind(startX, startY, endX, endY, th, dh, tile -> true);
	}

	@Override
	protected void generate() {

		Vec2 pos = new Vec2();
		Seq<Room> r = new Seq<>();
		float maxd = Mathf.dst(width/2f, height/2f);

		// enemy and player rooms
		Vec2 trns = Tmp.v1.trns(rand.random(360f), width/2.6f);
		int
		spawnX = (int)(trns.x + width/2f), spawnY = (int)(trns.y + height/2f),
		launchX = (int)(-trns.y + width/2f), launchY = (int)(-trns.y + height/2f);
		r.add(
			new Room(
				spawnX,
				spawnY,
				(int) (10f + noise3d(strokeSeed * 90, sector.tile.v, 1, 1, 1f, 5))
			),
			new Room(
				launchX,
				launchY,
				(int)( 10f + noise3d(strokeSeed * 96, sector.tile.v, 1, 1, 1f, 5))
			)
		);

		// floor
		pass((x, y) -> {
			floor = getBlock(x / (width * 0.5f), y / (height * 0.5f), sector.tile.v.z);
		});
		
		
		// inverseFloodFill() wasn't working soo
		for(Tile tile : tiles){
			if(tile.block() == Blocks.air){
				tile.setBlock(tile.floor().wall);
			}
		}

		// create rooms
		for (int i = 0; i < 7; i++) {
			pos.set(
				Mathf.clamp((int) noise3d(widthSeed * i, sector.tile.v, 1, 1, 1f, width), 20, width - 20),
				Mathf.clamp((int) noise3d(heightSeed * i, sector.tile.v, 1, 1, 1f, height), 20, height - 20)
			);
			r.add(
				new Room(
					(int) pos.x,
					(int) pos.y,
					(int) (10f + noise3d(strokeSeed * i, sector.tile.v, 1, 1, 1f, 5))
				)
			);
		}

		// connect rooms
		r.each(room -> {
			int roomId = 0;

			// get room to connect
			room.connect(
				r.get(
					(int) noise3d((int) roomSeed * roomId, sector.tile.v, 1, 1, 1f, r.size - 1)
				)
			);

			// if it tries to connect to itself, it'll connect to spawn instead
			if (room.connected == room) room.connect(r.get(0));

			// actually connect the rooms
			room.open();
			if (room.isConnected()) {
				brush(pathfind(room.x, room.y, room.connected.x, room.connected.y, tile -> 5000f, Astar.manhattan), 20);
			}
			roomId++;
		});
	
		// make connections look more natural
		distort(125f, 72f);

		// make core and enemy area
		erase(spawnX, spawnY, 20);
		erase(launchX, launchY, 20);
		brush(pathfind(r.get(0).x, r.get(0).y, r.get(1).x, r.get(1).y, tile -> 5000f, Astar.manhattan), 20);

		// more roughness
		distort(136f, 31f);
		distort(10f, 12f);
		distort(5f, 7f);
		median(4);
		
		// ores
		float poles = 1f - Math.abs(sector.tile.v.y);
		pass((x, y) -> {
			if (noise(x, y, 10, 0.3f, 30f, 1f) > 0.75f && block == Blocks.air) ore = OblivionEnvironment.oreNiobium;

			if (noise(x, y, 1, 0.2f, 40f, 1f) > 1.3f * poles && block != Blocks.air) ore = OblivionEnvironment.wallOreHafnium;

			// remove invalid ores
			if (ore == OblivionEnvironment.wallOreHafnium && !nearAir(x, y)) ore = Blocks.air;
			if (ore == OblivionEnvironment.wallOreHafnium && noise(x, y, 4, 0.5f, 167f) > 0.4f) ore = Blocks.air;

			if (ore == OblivionEnvironment.oreNiobium && block != Blocks.air) ore = Blocks.air;
		});

		// put core and enemy spawn in the map
		Schematics.placeLaunchLoadout(r.get(0).x, r.get(0).y);
		tiles.getn(r.get(1).x, r.get(1).y).setOverlay(Blocks.spawn);

		state.rules.waveSpacing = Mathf.lerp(60 * 65 * 2, 60f * 60f * 1f, Math.max(sector.threat - 0.4f, 0f));
		state.rules.winWave = sector.info.winWave = 10 + 5 * (int)Math.max(sector.threat * 10, 1);
		state.rules.waves = sector.info.waves = true;
		state.rules.env = sector.planet.defaultEnv;
	}

	public class Room {
		int x, y, size;
		Room connected;

		public Room(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.connected = this;
		}

		public int getDistance(Room to) {
			int
			distX = Math.abs(x - to.x),
			distY = Math.abs(y - to.y);
			return (int) (distX+distY/2f);
		}

		public boolean isConnected() {
			return connected != this;
		}

		public void open() {erase(x, y, size);}

		public void connect(Room to) {
			if (
				to.connected == this ||
				connected != this ||
				getDistance(to) < size
			) return;

			connected = to;
		}
	}
}