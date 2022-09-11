package mchorse.mappet.commands.whitelist;

import mchorse.mappet.commands.MappetSubCommandBase;
import net.minecraft.command.ICommandSender;

public class CommandWhitelist extends MappetSubCommandBase
{
    @Override
    public String getName() { return "whitelist"; }

    @Override
    public String getUsage(ICommandSender sender) { return "mappet.commands.mp.whitelist.help"; }

    public CommandWhitelist() {
        this.add(new CommandWhitelistSave());
        this.add(new CommandWhitelistLoad());
    }
}
