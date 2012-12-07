package mDiyo.inficraft.armory;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * InfiBlocks: Armory
 * Every good castle has a place to put its tools
 * Armor stands are entites!
 * @author: mDiyo
 */

@Mod(modid = "InfiArmory", name = "InfiTools Armory", version = "Test")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class InfiArmory 
{
	/* Proxies for sides, used for client-only processing */
	@SidedProxy(clientSide = "mDiyo.inficraft.armory.ArmoryProxyClient", serverSide = "mDiyo.inficraft.armory.ArmoryProxyCommon")
	public static ArmoryProxyCommon proxy;
	
	/* Instance of this mod, used for grabbing prototype fields */
	@Instance("InfiArmory")
	public static InfiArmory instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent evt)
	{
		PHArmory.initProps();
		stoneRack = new Toolrack(1500, Material.rock);
		GameRegistry.registerBlock(stoneRack, mDiyo.inficraft.armory.ToolrackItem.class);
		GameRegistry.registerTileEntity(mDiyo.inficraft.armory.ToolrackLogic.class, "InfiToolrack");
		/*armorStand = new ArmorStand(1501, Material.rock);
		GameRegistry.registerBlock(armorStand, mDiyo.inficraft.armory.ArmorStandItem.class);
		GameRegistry.registerTileEntity(mDiyo.inficraft.armory.ArmorStandLogic.class, "InfiArmorStand");*/
		
		EntityRegistry.registerModEntity(mDiyo.inficraft.armory.ArmorStandEntity.class, "Armor Stand", 0, this, 32, 5, true);
	}

	@Init
	public void init(FMLInitializationEvent evt) 
	{		
		proxy.registerRenderer();
		proxy.addNames();
		proxy.addRecipes();
	}
	
	/* Prototype fields, used elsewhere */
	public static Block stoneRack;
	public static Block woodRack;
	public static Block armorStand;
	public static Block pedestal;
}
