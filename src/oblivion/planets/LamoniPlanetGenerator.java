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

public class LamoniPlanetGenerator extends PlanetGenerator {
	//alternate, le direct generation (wip)
	public static boolean alt = false;
	public static final int seed = 1;

	BaseGenerator basegen = new BaseGenerator();
	float scl = 5f;
	float waterOffset = 0.07f;

	Block pale = OblivionEnvironment.paletolime,
		gole = OblivionEnvironment.goletenira,
		arge = OblivionEnvironment.argeletine,
		male = OblivionEnvironment.malenatite,
		mudo = OblivionEnvironment.mudone,
		boro = OblivionEnvironment.boronite,
		tarr = OblivionEnvironment.tarrobonite,
		carm = OblivionEnvironment.carmebonite,
		poro = OblivionEnvironment.porotrate,
		amon = OblivionEnvironment.amonetha,
		lath = OblivionEnvironment.lathanite,
		tane = OblivionEnvironment.methystane,
		gras = Blocks.grass,
		ston = Blocks.stone;

	public Block[][] arr =
	{
		{pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale},
		{pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale, pale},
		{pale, pale, pale, pale, male, pale, male, pale, pale, gole, pale, male, pale},
		{male, gole, gole, male, gole, gole, male, male, gole, male, male, gole, poro},
		{gole, arge, gole, male, gole, male, arge, gole, gole, male, gole, arge, gole},
		{mudo, arge, gole, arge, mudo, arge, arge, arge, arge, arge, arge, arge, arge},
		{mudo, arge, mudo, mudo, mudo, arge, mudo, mudo, mudo, mudo, mudo, mudo, arge},
		{boro, tarr, mudo, mudo, mudo, boro, mudo, tarr, poro, mudo, mudo, mudo, mudo},
		{carm, poro, boro, tarr, boro, carm, tarr, carm, mudo, tarr, boro, tarr, mudo},
		{poro, carm, carm, carm, carm, poro, carm, poro, tarr, carm, carm, carm, tarr},
		{amon, lath, poro, poro, tane, amon, amon, tane, carm, lath, poro, amon, carm},
		{lath, tane, amon, tane, lath, tane, tane, lath, amon, tane, tane, lath, tane},
		{tane, tane, tane, tane, tane, tane, tane, tane, tane, tane, tane, tane, tane}
	};

	float water = 0;

	float rawHeight(Vec3 position){
		position = Tmp.v33.set(position).scl(scl);
		return (Mathf.pow(Simplex.noise3d(seed, 7, 0.5f, 1f/3f, position.x, position.y, position.z), 2.3f) + waterOffset) / (1f + waterOffset);
	}

	@Override
	public void generateSector(Sector sector){
		sector.generateEnemyBase = false;
	}

	@Override
	public float getHeight(Vec3 position){
		float height = rawHeight(position);
		return Math.max(height, water);
	}

	@Override
	public Color getColor(Vec3 position){
		Block block = getBlock(position);
		return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
	}

	@Override
	public void genTile(Vec3 position, TileGen tile){
		tile.floor = getBlock(position);
		tile.block = tile.floor.asFloor().wall;

		if(Ridged.noise3d(1, position.x, position.y, position.z, 2, 22) > 0.31){
			tile.block = Blocks.air;
		}
	}

	Block getBlock(Vec3 position){
		float height = rawHeight(position);
		Tmp.v31.set(position);
		position = Tmp.v33.set(position).scl(scl);
		float rad = scl;
		float temp = Mathf.clamp(Math.abs(position.y * 2f) / (rad));
		float tnoise = Simplex.noise3d(seed, 7, 0.56, 1f/3f, position.x, position.y + 999f, position.z);
		temp = Mathf.lerp(temp, tnoise, 0.5f);
		height *= 1.2f;
		height = Mathf.clamp(height);

		float tar = Simplex.noise3d(seed, 4, 0.55f, 1f/2f, position.x, position.y + 999f, position.z) * 0.3f + Tmp.v31.dst(0, 0, 1f) * 0.2f;

		Block res = arr[Mathf.clamp((int)(temp * arr.length), 0, arr[0].length - 1)][Mathf.clamp((int)(height * arr[0].length), 0, arr[0].length - 1)];
		return res;
	}

	@Override
	protected float noise(float x, float y, double octaves, double falloff, double scl, double mag){
		Vec3 v = sector.rect.project(x, y).scl(5f);
		return Simplex.noise3d(seed, octaves, falloff, 1f / scl, v.x, v.y, v.z) * (float)mag;
	}

	@Override
	protected void generate(){

		class Room{
			int x, y, radius;
			ObjectSet<Room> connected = new ObjectSet<>();

			Room(int x, int y, int radius){
				this.x = x;
				this.y = y;
				this.radius = radius;
				connected.add(this);
			}

			void join(int x1, int y1, int x2, int y2){
				float nscl = rand.random(100f, 140f) * 6f;
				int stroke = rand.random(3, 9);
				brush(pathfind(x1, y1, x2, y2, tile -> (tile.solid() ? 50f : 0f) + noise(tile.x, tile.y, 2, 0.4f, 1f / nscl) * 500, Astar.manhattan), stroke);
			}

			void connect(Room to){
				if(!connected.add(to) || to == this) return;

				Vec2 midpoint = Tmp.v1.set(to.x, to.y).add(x, y).scl(0.5f);
				rand.nextFloat();

				if(alt){
					midpoint.add(Tmp.v2.set(1, 0f).setAngle(Angles.angle(to.x, to.y, x, y) + 90f * (rand.chance(0.5) ? 1f : -1f)).scl(Tmp.v1.dst(x, y) * 2f));
				}else{
					//add randomized offset to avoid straight lines
					midpoint.add(Tmp.v2.setToRandomDirection(rand).scl(Tmp.v1.dst(x, y)));
				}

				midpoint.sub(width/2f, height/2f).limit(width / 2f / Mathf.sqrt3).add(width/2f, height/2f);

				int mx = (int)midpoint.x, my = (int)midpoint.y;

				join(x, y, mx, my);
				join(mx, my, to.x, to.y);
			}

			void joinLiquid(int x1, int y1, int x2, int y2){
				float nscl = rand.random(100f, 140f) * 6f;
				int rad = rand.random(5, 10);
				int avoid = 2 + rad;
				var path = pathfind(x1, y1, x2, y2, tile -> (tile.solid() || !tile.floor().isLiquid ? 70f : 0f) + noise(tile.x, tile.y, 2, 0.4f, 1f / nscl) * 500, Astar.manhattan);
				path.each(t -> {
					//don't place liquid paths near the core
					if(Mathf.dst2(t.x, t.y, x2, y2) <= avoid * avoid){
						return;
					}
					
					for(int x = -rad; x <= rad; x++){
						for(int y = -rad; y <= rad; y++){
							int wx = t.x + x, wy = t.y + y;
							if(Structs.inBounds(wx, wy, width, height) && Mathf.within(x, y, rad)){
								Tile other = tiles.getn(wx, wy);
								other.setBlock(Blocks.air);
								if(Mathf.within(x, y, rad - 1) && !other.floor().isLiquid){
									Floor floor = other.floor();
									//TODO does not respect tainted floors
									other.setFloor((Floor)(floor == Blocks.sand || floor == Blocks.salt ? Blocks.sandWater : Blocks.darksandTaintedWater));
								}
							}
						}
					}
				});
			}

			void connectLiquid(Room to){
				if(to == this) return;

				Vec2 midpoint = Tmp.v1.set(to.x, to.y).add(x, y).scl(0.5f);
				rand.nextFloat();

				//add randomized offset to avoid straight lines
				midpoint.add(Tmp.v2.setToRandomDirection(rand).scl(Tmp.v1.dst(x, y)));
				midpoint.sub(width/2f, height/2f).limit(width / 2f / Mathf.sqrt3).add(width/2f, height/2f);

				int mx = (int)midpoint.x, my = (int)midpoint.y;

				joinLiquid(x, y, mx, my);
				joinLiquid(mx, my, to.x, to.y);
			}
		}

		cells(4);
		distort(10f, 12f);

		float constraint = 1.3f;
		float radius = width / 2f / Mathf.sqrt3;
		int rooms = rand.random(2, 5);
		Seq<Room> roomseq = new Seq<>();

		for(int i = 0; i < rooms; i++){
			Tmp.v1.trns(rand.random(360f), rand.random(radius / constraint));
			float rx = (width/2f + Tmp.v1.x);
			float ry = (height/2f + Tmp.v1.y);
			float maxrad = radius - Tmp.v1.len();
			float rrad = Math.min(rand.random(9f, maxrad / 2f), 30f);
			roomseq.add(new Room((int)rx, (int)ry, (int)rrad));
		}

		//check positions on the map to place the player spawn. this needs to be in the corner of the map
		Room spawn = null;
		Seq<Room> enemies = new Seq<>();
		int enemySpawns = rand.random(1, Math.max((int)(sector.threat * 4), 1));
		int offset = rand.nextInt(360);
		float length = width/2.55f - rand.random(13, 23);
		int angleStep = 5;
		int waterCheckRad = 5;
		for(int i = 0; i < 360; i+= angleStep){
			int angle = offset + i;
			int cx = (int)(width/2 + Angles.trnsx(angle, length));
			int cy = (int)(height/2 + Angles.trnsy(angle, length));

			int waterTiles = 0;

			//check for water presence
			for(int rx = -waterCheckRad; rx <= waterCheckRad; rx++){
				for(int ry = -waterCheckRad; ry <= waterCheckRad; ry++){
					Tile tile = tiles.get(cx + rx, cy + ry);
					if(tile == null || tile.floor().liquidDrop != null){
						waterTiles ++;
					}
				}
			}

			if(waterTiles <= 4 || (i + angleStep >= 360)){
				roomseq.add(spawn = new Room(cx, cy, rand.random(8, 15)));

				for(int j = 0; j < enemySpawns; j++){
					float enemyOffset = rand.range(60f);
					Tmp.v1.set(cx - width/2, cy - height/2).rotate(180f + enemyOffset).add(width/2, height/2);
					Room espawn = new Room((int)Tmp.v1.x, (int)Tmp.v1.y, rand.random(8, 16));
					roomseq.add(espawn);
					enemies.add(espawn);
				}

				break;
			}
		}

		//clear radius around each room
		for(Room room : roomseq){
			erase(room.x, room.y, room.radius);
		}

		//randomly connect rooms together
		int connections = rand.random(Math.max(rooms - 1, 1), rooms + 3);
		for(int i = 0; i < connections; i++){
			roomseq.random(rand).connect(roomseq.random(rand));
		}

		for(Room room : roomseq){
			spawn.connect(room);
		}

		Room fspawn = spawn;

		cells(1);

		distort(10f, 6f);

		Seq<Block> ores = Seq.with(OblivionEnvironment.oreNiobium);
		float poles = Math.abs(sector.tile.v.y);
		float nmag = 0.5f;
		float scl = 1f;
		float addscl = 1.3f;

		pass((x, y) -> {
			if (nearAir(x, y)) {
				if (Simplex.noise3d(seed, 2, 0.5f, scl, sector.tile.v.x, sector.tile.v.y, sector.tile.v.z) * nmag + poles > 0.85f && block != Blocks.air) {
					ore = OblivionEnvironment.wallOreHafnium;
				}
			}
		});

		FloatSeq frequencies = new FloatSeq();
		for(int i = 0; i < ores.size; i++){
			frequencies.add(rand.random(-0.1f, 0.01f) - i * 0.01f + poles * 0.04f);
		}

		pass((x, y) -> {
			if(!floor.asFloor().hasSurface()) return;

			int offsetX = x - 4, offsetY = y + 23;
			for(int i = ores.size - 1; i >= 0; i--){
				Block entry = ores.get(i);
				float freq = frequencies.get(i);
				if(Math.abs(0.5f - noise(offsetX, offsetY + i*999, 2, 0.7, (40 + i * 2))) > 0.22f + i*0.01 &&
					Math.abs(0.5f - noise(offsetX, offsetY - i*999, 1, 1, (30 + i * 4))) > 0.37f + freq){
					ore = entry;
					break;
				}
			}
		});

		trimDark();

		median(2);

		inverseFloodFill(tiles.getn(spawn.x, spawn.y));

		float difficulty = sector.threat;
		ints.clear();
		ints.ensureCapacity(width * height / 4);

		int ruinCount = rand.random(-2, 4);
		if(ruinCount > 0){
			int padding = 25;

			//create list of potential positions
			for(int x = padding; x < width - padding; x++){
				for(int y = padding; y < height - padding; y++){
					Tile tile = tiles.getn(x, y);
					if(!tile.solid() && (tile.drop() != null || tile.floor().liquidDrop != null)){
						ints.add(tile.pos());
					}
				}
			}

			ints.shuffle(rand);

			int placed = 0;
			float diffRange = 0.4f;
			//try each position
			for(int i = 0; i < ints.size && placed < ruinCount; i++){
				int val = ints.items[i];
				int x = Point2.x(val), y = Point2.y(val);

				//do not overwrite player spawn
				if(Mathf.within(x, y, spawn.x, spawn.y, 18f)){
					continue;
				}

				float range = difficulty + rand.random(diffRange);

				Tile tile = tiles.getn(x, y);
				BasePart part = null;
				if(tile.overlay().itemDrop != null){
					part = bases.forResource(tile.drop()).getFrac(range);
				}else if(tile.floor().liquidDrop != null && rand.chance(0.05)){
					part = bases.forResource(tile.floor().liquidDrop).getFrac(range);
				}else if(rand.chance(0.05)){ //ore-less parts are less likely to occur.
					part = bases.parts.getFrac(range);
				}

				//actually place the part
				if(part != null && BaseGenerator.tryPlace(part, x, y, Team.derelict, (cx, cy) -> {
					Tile other = tiles.getn(cx, cy);
					if(other.floor().hasSurface()){
						other.setOverlay(Blocks.oreScrap);
						for(int j = 1; j <= 2; j++){
							for(Point2 p : Geometry.d8){
								Tile t = tiles.get(cx + p.x*j, cy + p.y*j);
								if(t != null && t.floor().hasSurface() && rand.chance(j == 1 ? 0.4 : 0.2)){
									t.setOverlay(Blocks.oreScrap);
								}
							}
						}
					}
				})){
					placed ++;

					int debrisRadius = Math.max(part.schematic.width, part.schematic.height)/2 + 3;
					Geometry.circle(x, y, tiles.width, tiles.height, debrisRadius, (cx, cy) -> {
						float dst = Mathf.dst(cx, cy, x, y);
						float removeChance = Mathf.lerp(0.05f, 0.5f, dst / debrisRadius);

						Tile other = tiles.getn(cx, cy);
						if(other.build != null && other.isCenter()){
							if(other.team() == Team.derelict && rand.chance(removeChance)){
								other.remove();
							}else if(rand.chance(0.5)){
								other.build.health = other.build.health - rand.random(other.build.health * 0.9f);
							}
						}
					});
				}
			}
		}

		//remove invalid ores
		for(Tile tile : tiles){
			if(tile.overlay().needsSurface && !tile.floor().hasSurface()){
				tile.setOverlay(Blocks.air);
			}
		}

		Schematics.placeLaunchLoadout(spawn.x, spawn.y);

		for(Room espawn : enemies){
			tiles.getn(espawn.x, espawn.y).setOverlay(Blocks.spawn);
		}

		if(sector.hasEnemyBase()){
			basegen.generate(tiles, enemies.map(r -> tiles.getn(r.x, r.y)), tiles.get(spawn.x, spawn.y), state.rules.waveTeam, sector, difficulty);

			state.rules.attackMode = sector.info.attack = true;
		}else{
			state.rules.winWave = sector.info.winWave = 10 + 5 * (int)Math.max(difficulty * 10, 1);
		}

		float waveTimeDec = 0.4f;

		state.rules.waveSpacing = Mathf.lerp(60 * 65 * 2, 60f * 60f * 1f, Math.max(difficulty - waveTimeDec, 0f));
		state.rules.waves = sector.info.waves = true;
		state.rules.enemyCoreBuildRadius = 600f;

		//spawn air only when spawn is blocked
		state.rules.spawns = Waves.generate(difficulty, new Rand(sector.id), state.rules.attackMode, state.rules.attackMode && spawner.countGroundSpawns() == 0, false);
	}

	@Override
	public void postGenerate(Tiles tiles){
	}
}