package brainlets.patches;

import brainlets.powers.NicotineAddictionPower;
import brainlets.powers.SoulburnPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SoulburnPatch {
    public static int  isActive=0;
    public static boolean thisRun=false;

    @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
    public static class DisableHealing
    {
        @SpirePrefixPatch
        public static void Prefix(GameActionManager m)
        {
            if (m.actions.isEmpty() && m.preTurnActions.isEmpty() && m.cardQueue.isEmpty() && isActive > 0) {

                SoulburnPatch.thisRun = true;
            }
        }
        @SpirePostfixPatch
        public static void Postfix(GameActionManager m)
        {
            if (thisRun) {
                System.out.println("Is this run");
                while (isActive > 0) {
                    if (AbstractDungeon.player.hasPower(NicotineAddictionPower.POWER_ID)) {

                        AbstractPlayer p = AbstractDungeon.player;
                        AbstractPower pow = AbstractDungeon.player.getPower(NicotineAddictionPower.POWER_ID);
                        pow.flash();
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SoulburnPower(p, pow.amount), pow.amount));
                    }
                    thisRun = false;
                    isActive -= 1;
                }
            }

        }
    }
}
