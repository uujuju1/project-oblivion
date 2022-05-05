package oblivion.blocks.payload;

import arc.util.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.consumers.*;
import mindustry.world.blocks.payloads.*;

public class BlockCrafter extends PayloadBlock {
	public Block outputBlock = Blocks.router;
	public float craftTime = 60f;

	public BlockCrafter(String name) { 
		super(name);
		solid = destructible = true;
	}

	public class BlockCrafterBuild extends PayloadBlockBuild<BuildPayload> {
		public float progress = 0f;
		
		@Override
		public void updateTile() {
			if (cons.valid()) {
				progress += Time.delta;
				if (progress <= craftTime) {
					progress = 0f;
					payVector.setZero();
					payload = new BuildPayload(outputBlock, team);
					consume();
				}
			}
			moveOutPayload();
		}
		
		@Override
		public void draw() {
			super.draw();
			drawPayload();
		}
	}
}