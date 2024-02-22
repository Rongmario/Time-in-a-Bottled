package zone.rong.timeinabottled;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.common.Loader;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;

public class TimeInABottledMixinLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Lists.newArrayList(
                retrieveConfigName("gregtech"),
                retrieveConfigName("modularmachinery"),
                retrieveConfigName("valkyrielib")
        );
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        return Loader.isModLoaded(mixinConfig.substring(mixinConfig.indexOf('_') + 1, mixinConfig.lastIndexOf('.')));
    }

    private String retrieveConfigName(String modId) {
        return "mixins.timeinabottled_" + modId + ".json";
    }

}
