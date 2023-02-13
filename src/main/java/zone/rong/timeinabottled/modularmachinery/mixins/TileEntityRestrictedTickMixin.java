package zone.rong.timeinabottled.modularmachinery.mixins;

import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.TileEntityRestrictedTick;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.timeinabottled.Configuration;

@Mixin(value = TileEntityRestrictedTick.class)
public abstract class TileEntityRestrictedTickMixin extends TileColorableMachineComponent {

    @Shadow(remap = false) protected int ticksExisted;

    @Shadow(remap = false) public abstract void doRestrictedTick();

    @Inject(method = "update", at = @At("HEAD"), cancellable = true, require = 1, remap = true)
    private void onUpdate(CallbackInfo ci) {
        if (Configuration.modularMachinery) {
            this.doRestrictedTick();
            ++this.ticksExisted;
            ci.cancel();
        }
    }

}
