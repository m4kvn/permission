package com.m4kvn.permission.model

import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.service.context.Context
import org.spongepowered.api.service.permission.Subject
import org.spongepowered.api.service.permission.SubjectCollection
import org.spongepowered.api.service.permission.SubjectData
import org.spongepowered.api.service.permission.SubjectReference
import org.spongepowered.api.util.Tristate
import java.util.*

class SubjectImpl(
    private val identifier: String,
    private val collection: SubjectCollection
) : Subject {

    override fun getIdentifier(): String {
        return identifier
    }

    override fun asSubjectReference(): SubjectReference {
        return SubjectReferenceImpl(this, collection)
    }

    override fun getCommandSource(): Optional<CommandSource> {
        return Optional.empty()
    }

    override fun getOption(contexts: MutableSet<Context>, key: String): Optional<String> {
        return Optional.empty()
    }

    override fun getTransientSubjectData(): SubjectData {
        return SubjectDataImpl()
    }

    override fun getParents(contexts: MutableSet<Context>): MutableList<SubjectReference> {
        return mutableListOf()
    }

    override fun getContainingCollection(): SubjectCollection {
        return collection
    }

    override fun getSubjectData(): SubjectData {
        return SubjectDataImpl()
    }

    override fun isChildOf(contexts: MutableSet<Context>, parent: SubjectReference): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getActiveContexts(): MutableSet<Context> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPermissionValue(contexts: MutableSet<Context>, permission: String): Tristate {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isSubjectDataPersisted(): Boolean {
        return false
    }

}