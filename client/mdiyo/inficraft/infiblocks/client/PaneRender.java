package mDiyo.inficraft.infiblocks.client;

import mDiyo.inficraft.infiblocks.InfiBlocks;
import mDiyo.inficraft.infiblocks.blocks.PaneBase;
import mDiyo.inficraft.infiblocks.magicslabs.MagicSlabBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class PaneRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		
		if (modelID == InfiBlocks.getContentInstance().paneModelID)
		{
			block.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
	        BlockRenderHelper.renderDo(renderer, block, metadata);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelID, RenderBlocks renderer) 
	{		
		if (modelID == InfiBlocks.getContentInstance().paneModelID)
		{
			return renderPaneInWorld(renderer, world, x, y, z, (PaneBase)block);
		} 
		
		else
		{	  	
			return false;
		}
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return InfiBlocks.getContentInstance().paneModelID;
	}
	
	public static boolean renderPaneInWorld(RenderBlocks renderblocks, 
            IBlockAccess iblockaccess, int x, int y, int z, PaneBase pane)
    {
        int l = iblockaccess.getHeight();
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(pane.getMixedBrightnessForBlock(iblockaccess, x, y, z));
        float f = 1.0F;
        int i1 = pane.colorMultiplier(iblockaccess, x, y, z);
        float f1 = (float)(i1 >> 16 & 0xff) / 255F;
        float f2 = (float)(i1 >> 8 & 0xff) / 255F;
        float f3 = (float)(i1 & 0xff) / 255F;
        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30F + f2 * 59F + f3 * 11F) / 100F;
            float f5 = (f1 * 30F + f2 * 70F) / 100F;
            float f6 = (f1 * 30F + f3 * 70F) / 100F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        boolean flag = false;
        boolean flag1 = false;
        int l1;
        int i2;
        
        int j1 = iblockaccess.getBlockMetadata(x, y, z);
        l1 = pane.getBlockTextureFromSideAndMetadata(0, j1);
        i2 = pane.getSideTextureIndex(j1);
        
        int k1 = (l1 & 0xf) << 4;
        int j2 = l1 & 0xf0;
        double d = (float)k1 / 256F;
        double d1 = ((float)k1 + 7.99F) / 256F;
        double d2 = ((float)k1 + 15.99F) / 256F;
        double d3 = (float)j2 / 256F;
        double d4 = ((float)j2 + 15.99F) / 256F;
        int k2 = (i2 & 0xf) << 4;
        int l2 = i2 & 0xf0;
        double d5 = (float)(k2 + 7) / 256F;
        double d6 = ((float)k2 + 8.99F) / 256F;
        double d7 = (float)l2 / 256F;
        double d8 = (float)(l2 + 8) / 256F;
        double d9 = ((float)l2 + 15.99F) / 256F;
        double xBot = x;
        double xMid = (double)x + 0.5D;
        double xTop = x + 1;
        double zBot = z;
        double zMid = (double)z + 0.5D;
        double zTop = z + 1;
        double xMidDown = ((double)x + 0.5D) - 0.0625D;
        double xMidUp = (double)x + 0.5D + 0.0625D;
        double zMidDown = ((double)z + 0.5D) - 0.0625D;
        double zMidUp = (double)z + 0.5D + 0.0625D;
        boolean west = pane.canConnectTo(iblockaccess.getBlockId(x, y, z - 1));
        boolean east = pane.canConnectTo(iblockaccess.getBlockId(x, y, z + 1));
        boolean south = pane.canConnectTo(iblockaccess.getBlockId(x - 1, y, z));
        boolean north = pane.canConnectTo(iblockaccess.getBlockId(x + 1, y, z));
        boolean renderAbove = pane.shouldSideBeRendered(iblockaccess, x, y + 1, z, 1);
        boolean renderBelow = pane.shouldSideBeRendered(iblockaccess, x, y - 1, z, 0);
        if ((!south || !north) && (south || north || west || east))
        {
            if (south && !north)
            {
                /*tessellator.addVertexWithUV(xBot, y + 1, zMid, d, d3);
                tessellator.addVertexWithUV(xBot, y + 0, zMid, d, d4);
                tessellator.addVertexWithUV(xMid, y + 0, zMid, d1, d4);
                tessellator.addVertexWithUV(xMid, y + 1, zMid, d1, d3);
                tessellator.addVertexWithUV(xMid, y + 1, zMid, d, d3);
                tessellator.addVertexWithUV(xMid, y + 0, zMid, d, d4);
                tessellator.addVertexWithUV(xBot, y + 0, zMid, d1, d4);
                tessellator.addVertexWithUV(xBot, y + 1, zMid, d1, d3);*/
                tessellator.addVertexWithUV(xBot, y + 1, zMidDown, d, d3);
                tessellator.addVertexWithUV(xBot, y + 0, zMidDown, d, d4);
                tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d1, d4);
                tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d1, d3);
                tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d, d3);
                tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d, d4);
                tessellator.addVertexWithUV(xBot, y + 0, zMidUp, d1, d4);
                tessellator.addVertexWithUV(xBot, y + 1, zMidUp, d1, d3);
                if (!east && !west)
                {
                    tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d5, d7);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d5, d9);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d6, d9);
                    tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d6, d7);
                    /*tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d5, d7);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d6, d7);*/
                }
                if (renderAbove || y < l - 1 && iblockaccess.isAirBlock(x - 1, y + 1, z))
                {
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    /*tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d8);*/
                }
                if (renderBelow || y > 1 && iblockaccess.isAirBlock(x - 1, y - 1, z))
                {
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d8);
                    /*tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d8);*/
                }
            }
            else if (!south && north)
            {
                tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d1, d3);
                tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d1, d4);
                tessellator.addVertexWithUV(xTop, y + 0, zMidDown, d2, d4);
                tessellator.addVertexWithUV(xTop, y + 1, zMidDown, d2, d3);
                tessellator.addVertexWithUV(xTop, y + 1, zMidUp, d1, d3);
                tessellator.addVertexWithUV(xTop, y + 0, zMidUp, d1, d4);
                tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d2, d4);
                tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d2, d3);
                if (!east && !west)
                {
                    tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d5, d7);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d6, d7);
                   /* tessellator.addVertexWithUV(xMid, y + 1, zMidUp, d5, d7);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidUp, d5, d9);
                    tessellator.addVertexWithUV(xMid, y + 0, zMidDown, d6, d9);
                    tessellator.addVertexWithUV(xMid, y + 1, zMidDown, d6, d7);*/
                }
                if (renderAbove || y < l - 1 && iblockaccess.isAirBlock(x + 1, y + 1, z))
                {
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d7);
                    /*tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d7);*/
                }
                if (renderBelow || y > 1 && iblockaccess.isAirBlock(x + 1, y - 1, z))
                {
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d7);
                    /*tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d7);*/
                }
            }
        }
        else
        {
            tessellator.addVertexWithUV(xBot, y + 1, zMidDown, d, d3);
            tessellator.addVertexWithUV(xBot, y + 0, zMidDown, d, d4);
            tessellator.addVertexWithUV(xTop, y + 0, zMidDown, d2, d4);
            tessellator.addVertexWithUV(xTop, y + 1, zMidDown, d2, d3);
            tessellator.addVertexWithUV(xTop, y + 1, zMidUp, d, d3);
            tessellator.addVertexWithUV(xTop, y + 0, zMidUp, d, d4);
            tessellator.addVertexWithUV(xBot, y + 0, zMidUp, d2, d4);
            tessellator.addVertexWithUV(xBot, y + 1, zMidUp, d2, d3);
            if (renderAbove)
            {
                tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d7);
                tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d9);
                /*tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d7);
                tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d9);*/
            }
            else
            {
                if (y < l - 1 && iblockaccess.isAirBlock(x - 1, y + 1, z))
                {
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    /*tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xBot, (double)(y + 1) + 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d8);*/
                }
                if (y < l - 1 && iblockaccess.isAirBlock(x + 1, y + 1, z))
                {
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d7);
                    /*tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)(y + 1) + 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xTop, (double)(y + 1) + 0.01D, zMidDown, d5, d7);*/
                }
            }
            if (renderBelow)
            {
                tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d9);
                tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d7);
                tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d7);
                tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d9);
                /*tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d9);
                tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d7);
                tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d7);
                tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d9);*/
            }
            else
            {
                if (y > 1 && iblockaccess.isAirBlock(x - 1, y - 1, z))
                {
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d8);
                    /*tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidUp, d6, d9);
                    tessellator.addVertexWithUV(xBot, (double)y - 0.01D, zMidDown, d5, d9);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d8);*/
                }
                if (y > 1 && iblockaccess.isAirBlock(x + 1, y - 1, z))
                {
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d7);
                    /*tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidUp, d6, d7);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidUp, d6, d8);
                    tessellator.addVertexWithUV(xMid, (double)y - 0.01D, zMidDown, d5, d8);
                    tessellator.addVertexWithUV(xTop, (double)y - 0.01D, zMidDown, d5, d7);*/
                }
            }
        }
        if ((!west || !east) && (south || north || west || east))
        {
            if (west && !east)
            {
                tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d, d3);
                tessellator.addVertexWithUV(xMidDown, y + 0, zBot, d, d4);
                tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d1, d4);
                tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d1, d3);
                tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d, d3);
                tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d, d4);
                tessellator.addVertexWithUV(xMidUp, y + 0, zBot, d1, d4);
                tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d1, d3);
                if (!north && !south)
                {
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d7);
                    /*tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d7);
                    tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d7);*/
                }
                if (renderAbove || y < l - 1 && iblockaccess.isAirBlock(x, y + 1, z - 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d5, d7);
                    /*tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d7);*/
                }
                if (renderBelow || y > 1 && iblockaccess.isAirBlock(x, y - 1, z - 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y, zBot, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zBot, d5, d7);
                    /*tessellator.addVertexWithUV(xMidDown, y, zMid, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y, zBot, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zBot, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d5, d7);*/
                }
            }
            else if (!west && east)
            {
                tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d1, d3);
                tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d1, d4);
                tessellator.addVertexWithUV(xMidDown, y + 0, zTop, d2, d4);
                tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d2, d3);
                tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d1, d3);
                tessellator.addVertexWithUV(xMidUp, y + 0, zTop, d1, d4);
                tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d2, d4);
                tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d2, d3);
                if (!north && !south)
                {
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d7);
                    tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d7);
                    /*tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 0, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 0, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d7);*/
                }
                if (renderAbove || y < l - 1 && iblockaccess.isAirBlock(x, y + 1, z + 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d8);
                    /*tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d8);*/
                }
                if (renderBelow || y > 1 && iblockaccess.isAirBlock(x, y - 1, z + 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d6, d8);
                    /*tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d8);*/
                }
            }
        }
        else
        {
            tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d, d3);
            tessellator.addVertexWithUV(xMidDown, y + 0, zTop, d, d4);
            tessellator.addVertexWithUV(xMidDown, y + 0, zBot, d2, d4);
            tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d2, d3);
            tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d, d3);
            tessellator.addVertexWithUV(xMidUp, y + 0, zBot, d, d4);
            tessellator.addVertexWithUV(xMidUp, y + 0, zTop, d2, d4);
            tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d2, d3);
            if (renderAbove)
            {
                tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d9);
                tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d6, d7);
                tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d5, d7);
                tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d9);
                /*tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d6, d9);
                tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d7);
                tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d7);
                tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d5, d9);*/
            }
            else
            {
                if (y < l - 1 && iblockaccess.isAirBlock(x, y + 1, z - 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d5, d7);
                    /*tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zBot, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zBot, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d5, d7);*/
                }
                if (y < l - 1 && iblockaccess.isAirBlock(x, y + 1, z + 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d8);
                    /*tessellator.addVertexWithUV(xMidDown, y + 1, zTop, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y + 1, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y + 1, zTop, d6, d8);*/
                }
            }
            if (renderBelow)
            {
                tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d9);
                tessellator.addVertexWithUV(xMidUp, y, zBot, d6, d7);
                tessellator.addVertexWithUV(xMidDown, y, zBot, d5, d7);
                tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d9);
                tessellator.addVertexWithUV(xMidUp, y, zBot, d6, d9);
                tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d7);
                tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d7);
                tessellator.addVertexWithUV(xMidDown, y, zBot, d5, d9);
            }
            else
            {
                if (y > 1 && iblockaccess.isAirBlock(x, y - 1, z - 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y, zBot, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zBot, d5, d7);
                    /*tessellator.addVertexWithUV(xMidDown, y, zMid, d6, d7);
                    tessellator.addVertexWithUV(xMidDown, y, zBot, d6, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zBot, d5, d8);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d5, d7);*/
                }
                if (y > 1 && iblockaccess.isAirBlock(x, y - 1, z + 1))
                {
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d6, d8);
                    /*tessellator.addVertexWithUV(xMidDown, y, zTop, d5, d8);
                    tessellator.addVertexWithUV(xMidDown, y, zMid, d5, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zMid, d6, d9);
                    tessellator.addVertexWithUV(xMidUp, y, zTop, d6, d8);*/
                }
            }
        }
        return true;
    }

}
