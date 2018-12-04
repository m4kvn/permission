package com.m4kvn.permission.model

import org.spongepowered.api.plugin.PluginContainer
import org.spongepowered.api.service.permission.PermissionDescription
import org.spongepowered.api.service.permission.Subject
import org.spongepowered.api.service.permission.SubjectReference
import org.spongepowered.api.text.Text
import java.util.*
import java.util.concurrent.CompletableFuture

data class PermissionDescriptionImpl(
    private val owner: Optional<PluginContainer>,
    private val id: String,
    private val description: Text?,
    private val assign: Pair<String, Boolean>
) : PermissionDescription {
    private val collections = mutableMapOf<String, MutableMap<Subject, Boolean>>()

    override fun getAssignedSubjects(
        collectionIdentifier: String
    ): MutableMap<Subject, Boolean> {
        return collections.getOrPut(collectionIdentifier) { mutableMapOf() }
    }

    override fun getOwner(): Optional<PluginContainer> {
        return owner
    }

    override fun getId(): String {
        return id
    }

    override fun getDescription(): Optional<Text> {
        return Optional.ofNullable(description)
    }

    override fun findAssignedSubjects(
        collectionIdentifier: String
    ): CompletableFuture<MutableMap<SubjectReference, Boolean>> {
        return CompletableFuture.completedFuture(collections
            .getOrPut(collectionIdentifier) { mutableMapOf() }
            .mapKeys { it.key.asSubjectReference() }
            .toMutableMap())
    }

    data class Builder(
        private val owner: Optional<PluginContainer>,
        private val id: String = "",
        private val description: Text? = null,
        private val assign: Pair<String, Boolean> = "" to false
    ) : PermissionDescription.Builder {

        override fun id(permissionId: String): PermissionDescription.Builder {
            return copy(id = permissionId)
        }

        override fun register(): PermissionDescription {
            if (id.isBlank())
                throw IllegalStateException("PermissionDescription id must not be blank.")
            if (assign.first.isBlank())
                throw IllegalStateException("PermissionDescription assign role must not be blank")
            return PermissionDescriptionImpl(owner, id, description, assign)
        }

        override fun description(description: Text?): PermissionDescription.Builder {
            return copy(description = description)
        }

        override fun assign(role: String, value: Boolean): PermissionDescription.Builder {
            return copy(assign = role to value)
        }

    }
}