package mchorse.mappet.network.server.ui;

import mchorse.mappet.Mappet;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.network.common.ui.PacketDashboard;
import mchorse.mclib.network.ServerMessageHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerHandlerDashboard extends ServerMessageHandler<PacketDashboard>
{
    @Override
    public void run(EntityPlayerMP player, PacketDashboard packetDashboard)
    {
        if (Mappet.dashboardWhitelist.isWhitelisted(player))
        {
            Dispatcher.sendTo(new PacketDashboard(), player);
        }
    }
}
