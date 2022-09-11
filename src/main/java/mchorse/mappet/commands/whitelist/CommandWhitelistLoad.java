package mchorse.mappet.commands.whitelist;

import mchorse.mappet.Mappet;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class CommandWhitelistLoad extends CommandWhitelistBase
{
    @Override
    public String getName() { return "load"; }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "mappet.commands.mp.whitelist.load";
    }

    @Override
    public String getSyntax()
    {
        return "{l}{6}/{r}mp {8}whitelist load{r}";
    }

    @Override
    public void executeCommand(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        Mappet.dashboardWhitelist.load();

        this.getL10n().success(sender, "whitelist.loaded");
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return super.getTabCompletions(server, sender, args, targetPos);
    }
}
