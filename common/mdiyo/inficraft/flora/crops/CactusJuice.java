package mDiyo.inficraft.flora.crops;

import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;

public class CactusJuice extends ItemFood
{
    public CactusJuice(int i, int j, boolean flag, int k)
    {
        super(i, j, flag);
        maxStackSize = 64;
    }

    public String getTextureFile()
    {
        return FloraCrops.foodTexture;
    }
    
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 12;
    }
}
