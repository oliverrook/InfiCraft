package mDiyo.inficraft.orizon.pastelmarble;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ColoredSquareBrickItem extends ItemBlock
{
    public static final String blockType[] =
    {
        "medium", "low", "dark", "high", "red", "blue", "yellow", "purple",
        "aqua", "darkgreen", "orange", "pink", "bluegray", "mint", "violet", "cyan"
    };

    public ColoredSquareBrickItem(int i)
    {
        super(i);
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
        return (new StringBuilder()).append(blockType[itemstack.getItemDamage()]).append("Square").toString();
    }
}
