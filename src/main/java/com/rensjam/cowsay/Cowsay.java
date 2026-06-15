package com.rensjam.cowsay;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Cowsay extends JavaPlugin {

    private static Cowsay instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getLogger().info("Cowsay Plugin Successfully Loaded!");

        PluginCommand command = getCommand("cowsay");
        if (command != null) {
            command.setExecutor(new cowsayCommand(this));
        }
    }

    public static Cowsay getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        getLogger().info("Cowsay Plugin Successfully Unloaded!");
    }
}