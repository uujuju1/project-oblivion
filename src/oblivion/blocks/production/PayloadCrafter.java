package oblivion.blocks.production;

import arc.struct.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.graphics.*;
import mindustry.world.blocks.payloads.*;

public class PayloadCrafter extends PayloadBlock {
	public Seq<PayloadRecipe> plans = new Seq<>(4);

	public PayloadCrafter(String name) {
		super(name);
		configurable = true;
		solid = destructible = true;

		config(Block.class, (PayloadCrafterBuild tile, Block i) -> {
			if(!configurable) return;

			int next = plans.indexOf(p -> p.output == val);
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

		public boolean comparePayload(Payload payload) {
			BuildPayload in;
			if(!(payload instanceof BuildPayload)) return false;
			in = (BuildPayload) payload;
			return in.build.block;
		}

		public Table table() {
			return table -> {
				table.image(output.uiicon).size(60);
			};
		}
	}

	public class PayloadCrafterBuild extends PayloadBuild<Payload> {
		public int currentPlan = -1;
		public float progress = 0;

		@Override
		public void buildConfiguration(Table table) {
			table.setBackground(Tex.whiteui);
			table.setColor(Pal.darkestGray);
			plans.each(p -> {
				table.table(
					p.table()
				);
			});
		}
		@Override
		public boolean acceptPayload(Building source, Payload payload) {
			plans.get(currentPlan == -1).comparePayload(payload);
		}
		@Override
		public boolean acceptUnitPayload(Unit unit) {return false;}

		@Override
		public void updateTile() {
			if (acceptPayload(this, payload)) {
				payload = plans.get(currentPlan == -1).output;
				moveOutPayload();
			}
		}
	}
}