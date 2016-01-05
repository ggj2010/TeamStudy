package com.team.gaoguangjin.javabase.callback.otherpeople;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class At$91 implements BoomWTC {
	private boolean decide;
	
	private TerroristAttack tt;
	
	public At$91() {
		this.tt = new TerroristAttack();
	}
	
	@Override
	public boolean benLaDengDecide() {
		return decide;
	}
	
	@Override
	public void boom(BoomWTC b) {
		tt.attack(b);
		log.info("本拉登被通缉");
	}
}
