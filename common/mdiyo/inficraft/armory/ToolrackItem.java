package mDiyo.inficraft.armory;

import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ToolrackItem extends ItemBlock
{
	public ToolrackItem(int id)
    {
        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int md)
    {
        return md;
    }
    
    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder()).append("block.").append(blockType[itemstack.getItemDamage()]).append("Toolrack").toString();
    }
    public static final String blockType[] =
    {
        "stone", "stonebrick", "brick", "obsidian"
    };
}
