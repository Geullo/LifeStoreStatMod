package com.geullo.stats.proxy;

import com.geullo.stats.Keybind.BagOpenKey;
import com.geullo.stats.Keybind.RegisterKeybind;
import com.geullo.stats.Packet.LifeStorePacket.LifePacket;
import com.geullo.stats.Packet.StatPluginPacket.StatPacket;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
    public static final SimpleNetworkWrapper GEULLO_STAT = NetworkRegistry.INSTANCE.newSimpleChannel("geulloStat");
    public static SimpleNetworkWrapper LIFE_STORE = NetworkRegistry.INSTANCE.newSimpleChannel("lifeStore2");

    @Override
    public void preInit() {
    }

    @Override
    public void init() {
        LIFE_STORE.registerMessage(LifePacket.Handle.class, LifePacket.class, 0, Side.CLIENT);
        FMLCommonHandler.instance().bus().register(new BagOpenKey());
        GEULLO_STAT.registerMessage(StatPacket.Handle.class, StatPacket.class, 0, Side.CLIENT);
        ClientRegistry.registerKeyBinding(RegisterKeybind.bagOpen);
        ClientRegistry.registerKeyBinding(RegisterKeybind.statOpen);
    }

    @Override
    public void postInit() {
    }

}
