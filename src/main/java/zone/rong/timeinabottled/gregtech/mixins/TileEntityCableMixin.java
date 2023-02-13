package zone.rong.timeinabottled.gregtech.mixins;

import gregtech.common.pipelike.cable.net.EnergyNet;
import gregtech.common.pipelike.cable.tile.TileEntityCable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.WeakReference;

@Mixin(value = TileEntityCable.class, remap = false)
public class TileEntityCableMixin {

    @Shadow private WeakReference<EnergyNet> currentEnergyNet;
    @Unique private long timeinabottled$currentTick = 0L;

    @Inject(method = "update", at = @At("HEAD"))
    private void onUpdate(CallbackInfoReturnable<Boolean> cir) {
        long currentTick = this.currentEnergyNet.get().getWorldData().getTotalWorldTime();
        if (currentTick != this.timeinabottled$currentTick) {
            this.timeinabottled$currentTick = currentTick;
        } else {
            cir.cancel();
        }
    }

}
