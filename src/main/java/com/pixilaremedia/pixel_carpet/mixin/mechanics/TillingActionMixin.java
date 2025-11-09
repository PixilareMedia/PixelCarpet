package com.pixilaremedia.pixel_carpet.mixin.mechanics;

import com.mojang.datafixers.util.Pair;
import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import net.fabricmc.fabric.mixin.content.registry.HoeItemAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class TillingActionMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void addTillable(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (PixelCarpetSettings.moreTillable) {
            tillingAction(Blocks.MYCELIUM, Blocks.FARMLAND);
            if (PixelCarpetSettings.podzolLeafLitter) {
                tillingAction(Blocks.PODZOL, Blocks.DIRT, Items.LEAF_LITTER);
            } else {
                tillingAction(Blocks.PODZOL, Blocks.FARMLAND);
            }
        } else {
            tillingAction(Blocks.PODZOL);
            tillingAction(Blocks.MYCELIUM);
        }
    }

    @Unique
    protected void tillingAction(Block block, Block tilledBlock) {
        HoeItemAccessor.getTillingActions().put(block, Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(tilledBlock.getDefaultState())));
    }

    @Unique
    protected void tillingAction(Block block, Block tilledBlock, ItemConvertible droppedItem) {
        HoeItemAccessor.getTillingActions().put(block, Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAndDropAction(tilledBlock.getDefaultState(), droppedItem)));
    }

    @Unique
    protected void tillingAction(Block block) {
        HoeItemAccessor.getTillingActions().remove(block);
    }
}
