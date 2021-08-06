package mchorse.mappet.utils;

import mchorse.mappet.Mappet;
import mchorse.mclib.client.gui.utils.Icon;
import mchorse.mclib.client.gui.utils.IconRegistry;
import net.minecraft.util.ResourceLocation;

public class MPIcons
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(Mappet.MOD_ID, "textures/gui/icons.png");

    public static final Icon REPL = IconRegistry.register("repl", new Icon(TEXTURE, 0, 0));
    public static final Icon IN = IconRegistry.register("in", new Icon(TEXTURE, 16, 0));
    public static final Icon OUT = IconRegistry.register("out", new Icon(TEXTURE, 32, 0));
}