package oblivion.blocks.production;

import arc.func.*;
import arc.struct.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.graphics.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.payloads.*;

public class PayloadCrafter extends PayloadBlock {
	public Seq<PayloadRecipe> plans = new Seq<>(4);

	public PayloadCrafter(String name) {
		super(name);
		configurable = true;
		solid = destructible = true;
		acceptsPayload = outputsPayload = true;

		config(Block.class, (PayloadCrafterBuild tile, Block block) -> {
			if(!configurable) return;

			int next = plans.indexOf(b -> b.output == block);
			if(tile.currentPlan == next) return;
			tile.currentPlan = next;
			tile.progress = 0;
		});
	}

	public class PayloadRecipe {
		public Block output;
		public Block input;
		public float time;

		public PayloadRecipe(Block output, Block input, float time) {
			this.output = output;
			this.input = input;
			this.time = time;
		}
	}

	public class PayloadCrafterBuild extends PayloadBlockBuild<Payload> {
		public int currentPlan = -1;
		public float progress = 0;

		@Override
		public void buildConfiguration(Table table) {
			Seq<Block> blocks = Seq.with(plans).map(b -> b.output).filter(b -> b.unlockedNow());
			table.setBackground(Tex.whiteui);
			table.setColor(Pal.darkestGray);
			ItemSelection.buildTable(PayloadCrafter.this, table, blocks, () -> currentPlan == -1 ? null : plans.get(currentPlan).output, block -> configure(plans.indexOf(b -> b.output == block)));
		}

		@Override
		public boolean acceptPayload(Building source, Payload payload) {
			if (currentPlan == -1 || !(payload instanceof BuildPayload) || payload != null) return false;
			BuildPayload in = (BuildPayload) payload;
			return plans.get(currentPlan).input == in.build.block;
		}
		@Override
		public boolean acceptUnitPayload(Unit unit) {return false;}

		@Override
		public void updateTile() {
			if (currentPlan == -1) return;
			if (acceptPayload(this, payload)) {
				payload = new BuildPayload(plans.get(currentPlan).output, team);
			}
			moveOutPayload();
		}
	}
}