package com.m4kvn.permission

import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameInitializationEvent
import org.spongepowered.api.plugin.Plugin

@Plugin(
    name = "Permission",
    id = "permission"
)
class PermissionPlugin {

    @Listener
    fun onGameInitialization(event: GameInitializationEvent) {
        PermissionServiceImpl.register(this)
    }
}