package com.pixilaremedia.pixel_carpet.mixin.leads;

import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import net.minecraft.entity.passive.PandaEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PandaEntity.class)
public class PandaEntityMixin {
    @Inject(at = @At("HEAD"), method = "canBeLeashed", cancellable = true)
    public void canBeLeashed(CallbackInfoReturnable<Boolean> cir) {
        if(PixelCarpetSettings.moreMobsOnLeads) {
            cir.cancel();
            cir.setReturnValue(true);
        }
    }
}