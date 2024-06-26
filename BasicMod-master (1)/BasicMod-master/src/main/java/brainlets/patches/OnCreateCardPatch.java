package brainlets.patches;

import brainlets.util.GeneralUtils;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(clz = StSLib.class, method = "onCreateCard", paramtypez = {AbstractCard.class})
public class OnCreateCardPatch {
    @SpirePostfixPatch
    public static void onCreateCard(AbstractCard c) {
        GeneralUtils.onGenerateCardMidcombat(c);
    }
}
