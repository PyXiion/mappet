package mchorse.mappet.api.conditions;

import mchorse.mappet.api.conditions.blocks.AbstractBlock;
import mchorse.mappet.api.utils.DataContext;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class Condition implements INBTSerializable<NBTTagCompound>
{
    public List<AbstractBlock> blocks = new ArrayList<AbstractBlock>();

    public boolean execute(DataContext context)
    {
        if (this.blocks.isEmpty())
        {
            return true;
        }

        boolean result = this.blocks.get(0).evaluate(context);

        for (int i = 1; i < this.blocks.size(); i++)
        {
            AbstractBlock block = this.blocks.get(i);
            boolean value = block.evaluate(context);

            if (block.not)
            {
                value = !value;
            }

            if (block.or)
            {
                result = result || value;
            }
            else
            {
                result = result && value;
            }
        }

        return result;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        NBTTagList blocks = new NBTTagList();

        for (AbstractBlock block : this.blocks)
        {
            NBTTagCompound blockTag = block.serializeNBT();

            blockTag.setString("Type", block.getType());
            blocks.appendTag(blockTag);
        }

        if (blocks.tagCount() > 0)
        {
            tag.setTag("Blocks", blocks);
        }

        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound tag)
    {
        if (!tag.hasKey("Blocks", Constants.NBT.TAG_LIST))
        {
            return;
        }

        NBTTagList blocks = tag.getTagList("Blocks", Constants.NBT.TAG_COMPOUND);

        for (int i = 0; i < blocks.tagCount(); i++)
        {
            NBTTagCompound blockTag = blocks.getCompoundTagAt(i);
            AbstractBlock block = AbstractBlock.create(blockTag.getString("Type"));

            if (block != null)
            {
                block.deserializeNBT(blockTag);
                this.blocks.add(block);
            }
        }
    }
}