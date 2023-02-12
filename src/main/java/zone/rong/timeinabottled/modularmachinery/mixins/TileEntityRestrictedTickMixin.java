package zone.rong.timeinabottled.modularmachinery.mixins;

import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.TileEntityRestrictedTick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.timeinabottled.Configuration;

@Mixin(value = TileEntityRestrictedTick.class, remap = false)
public abstract class TileEntityRestrictedTickMixin extends TileColorableMachineComponent {

    @Shadow private long lastUpdateWorldTick;
    @Shadow protected int ticksExisted;

    @Shadow public abstract void doRestrictedTick();

    @Overwrite
    public final void update() {
        if (Configuration.modularMachinery) {
            this.doRestrictedTick();
            ++this.ticksExisted;
        } else {
            long currentTick = this.getWorld().getTotalWorldTime();
            if (this.lastUpdateWorldTick != currentTick) {
                this.lastUpdateWorldTick = currentTick;
                this.doRestrictedTick();
                ++this.ticksExisted;
            }
        }
    }

}
