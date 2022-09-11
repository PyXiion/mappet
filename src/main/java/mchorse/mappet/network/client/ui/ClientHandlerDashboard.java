package mchorse.mappet.network.client.ui;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import mchorse.mappet.network.common.ui.PacketDashboard;
import mchorse.mclib.network.ClientMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientHandlerDashboard extends ClientMessageHandler<PacketDashboard>
{
    @Override
    @SideOnly(Side.CLIENT)
    public void run(EntityPlayerSP entityPlayerSP, PacketDashboard packetDashboard)
    {
        Minecraft mc = Minecraft.getMinecraft();

        mc.displayGuiScreen(GuiMappetDashboard.get(mc));
    }
}
