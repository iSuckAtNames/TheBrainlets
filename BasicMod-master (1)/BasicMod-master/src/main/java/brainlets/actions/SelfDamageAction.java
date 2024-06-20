package brainlets.actions;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SelfDamageAction extends DamageAction {
    public SelfDamageAction(DamageInfo info, AttackEffect effect) {
        super(AbstractDungeon.player, info, effect);
    }
}
