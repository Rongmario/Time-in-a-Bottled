package zone.rong.timeinabottled.valkyrielib.mixins;

import com.valkyrieofnight.vliblegacy.lib.sys.tile.plugin.ITlePlugin;
import com.valkyrieofnight.vliblegacy.lib.tile.VLTileOwned;
import com.valkyrieofnight.vliblegacy.lib.tile.tickable.VLTileTickable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.timeinabottled.Configuration;

import java.util.List;

@Mixin(value = VLTileTickable.class, remap = false)
public class VLTileTickableMixin extends VLTileOwned {

    @Shadow(remap = false) private List<ITlePlugin> modules;
    @Shadow(remap = false) public void updateTile() {}

    @Inject(method = "update", at = @At("HEAD"), cancellable = true, require = 1, remap = true)
    private void onUpdate(CallbackInfo ci) {
        if (Configuration.valkyrieLib) {
            for (ITlePlugin iTileModule : this.modules) {
                iTileModule.update();
            }
            this.updateTile();
            ci.cancel();
        }
    }
}
