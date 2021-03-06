package inficraft.infiblocks.magicslabs;

import inficraft.infiblocks.InfiBlocks;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class MagicSlabWool extends MagicSlabBase
{
    public MagicSlabWool(int i, int j)
    {
        super(i, j, Material.cloth);
    }
    
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if (j == 0)
        {
            return blockIndexInTexture;
        }
        else
        {
            j = ~(j & 0xf);
            return 113 + ((j & 8) >> 3) + (j & 7) * 16;
        }
    }
    
    public static int getBlockFromDye(int i)
    {
        return ~i & 0xf;
    }

    public static int getDyeFromBlock(int i)
    {
        return ~i & 0xf;
    }
    
    public void addCreativeItems(ArrayList arraylist)
    {
    	for (int iter = 0; iter < 16; iter++)
    	{
    		arraylist.add(new ItemStack(InfiBlocks.getContentInstance().magicSlabWool, 1, 0));
    	}
    }
}
