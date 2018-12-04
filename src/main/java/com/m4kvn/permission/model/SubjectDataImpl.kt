package com.m4kvn.permission.model

import org.spongepowered.api.service.context.Context
import org.spongepowered.api.service.permission.SubjectData
import org.spongepowered.api.service.permission.SubjectReference
import org.spongepowered.api.util.Tristate
import java.util.concurrent.CompletableFuture

class SubjectDataImpl : SubjectData {

    override fun setPermission(
        contexts: MutableSet<Context>,
        permission: String,
        value: Tristate
    ): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeParent(contexts: MutableSet<Context>, parent: SubjectReference): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addParent(contexts: MutableSet<Context>, parent: SubjectReference): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearOptions(): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearOptions(contexts: MutableSet<Context>): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParents(contexts: MutableSet<Context>): MutableList<SubjectReference> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermissions(contexts: MutableSet<Context>): MutableMap<String, Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setOption(contexts: MutableSet<Context>, key: String, value: String?): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearPermissions(): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearPermissions(contexts: MutableSet<Context>): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearParents(): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearParents(contexts: MutableSet<Context>): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllOptions(): MutableMap<MutableSet<Context>, MutableMap<String, String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllPermissions(): MutableMap<MutableSet<Context>, MutableMap<String, Boolean>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllParents(): MutableMap<MutableSet<Context>, MutableList<SubjectReference>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOptions(contexts: MutableSet<Context>): MutableMap<String, String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}