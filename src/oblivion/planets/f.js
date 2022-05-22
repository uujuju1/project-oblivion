let planet = Vars.content.getByName(ContentType.planet, "oblivion-lamoni")
let pa = Vars.content.getByName(ContentType.block, "oblivion-paletolime")
let go = Vars.content.getByName(ContentType.block, "oblivion-goletenira")
let ar = Vars.content.getByName(ContentType.block, "oblivion-argeletine")
let ma = Vars.content.getByName(ContentType.block, "oblivion-malenatite")
let mu = Vars.content.getByName(ContentType.block, "oblivion-mudone")
let gr = Blocks.grass
let st = Blocks.stone

planet.generator.arr = [
	[pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa],
	[pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa, pa],
	[pa, pa, pa, pa, ma, pa, ma, pa, pa, go, pa, ma, pa],
	[ma, go, go, ma, go, go, ma, ma, go, ma, ma, go, st],
	[go, ar, go, ma, go, ma, ar, go, go, ma, go, ar, go],
	[mu, ar, go, ar, mu, ar, ar, ar, ar, ar, ar, ar, ar],
	[mu, ar, mu, mu, mu, ar, mu, mu, mu, mu, mu, mu, ar],
	[gr, gr, mu, mu, mu, gr, mu, gr, st, mu, mu, mu, mu],
	[st, st, gr, gr, gr, st, gr, st, mu, gr, gr, gr, mu],
	[st, st, st, st, st, st, st, st, gr, st, st, st, gr],
	[st, st, st, st, st, st, st, st, st, st, st, st, st],
	[st, st, st, st, st, st, st, st, st, st, st, st, st],
	[st, st, st, st, st, st, st, st, st, st, st, st, st]
]

planet.reloadMesh()