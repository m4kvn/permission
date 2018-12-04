package com.m4kvn.permission

import com.m4kvn.permission.model.PermissionDescriptionImpl
import com.m4kvn.permission.model.SubjectCollectionImpl
import org.spongepowered.api.Sponge
import org.spongepowered.api.service.context.ContextCalculator
import org.spongepowered.api.service.permission.*
import org.spongepowered.api.service.permission.PermissionService.*
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Predicate

class PermissionServiceImpl : PermissionService {
    private val collections = mutableMapOf<String, SubjectCollection>()
    private val permissions = mutableMapOf<String, PermissionDescription>()

    companion object {
        fun register(plugin: PermissionPlugin) {
            Sponge.getServiceManager().setProvider(
                plugin,
                PermissionService::class.java,
                PermissionServiceImpl()
            )
        }
    }

    override fun getUserSubjects(): SubjectCollection {
        return collections.getOrPut(SUBJECTS_USER) {
            SubjectCollectionImpl(SUBJECTS_USER)
        }
    }

    override fun getGroupSubjects(): SubjectCollection {
        return collections.getOrPut(SUBJECTS_GROUP) {
            SubjectCollectionImpl(SUBJECTS_GROUP)
        }
    }

    override fun registerContextCalculator(calculator: ContextCalculator<Subject>) {}

    override fun newSubjectReference(collectionIdentifier: String, subjectIdentifier: String): SubjectReference {
        return collections.getOrPut(collectionIdentifier) {
            SubjectCollectionImpl(collectionIdentifier)
        }.newSubjectReference(subjectIdentifier)
    }

    override fun getLoadedCollections(): MutableMap<String, SubjectCollection> {
        collections.putIfAbsent(SUBJECTS_DEFAULT, SubjectCollectionImpl(SUBJECTS_DEFAULT))
        collections.putIfAbsent(SUBJECTS_USER, SubjectCollectionImpl(SUBJECTS_USER))
        collections.putIfAbsent(SUBJECTS_GROUP, SubjectCollectionImpl(SUBJECTS_GROUP))
        return collections
    }

    override fun getIdentifierValidityPredicate(): Predicate<String> {
        return Predicate { true }
    }

    override fun newDescriptionBuilder(plugin: Any): PermissionDescription.Builder {
        val owner = Sponge.getPluginManager().fromInstance(plugin)
        return PermissionDescriptionImpl.Builder(owner)
    }

    override fun loadCollection(identifier: String): CompletableFuture<SubjectCollection> {
        return CompletableFuture.completedFuture(collections.getOrPut(identifier) {
            SubjectCollectionImpl(identifier)
        })
    }

    override fun getDescription(permission: String): Optional<PermissionDescription> {
        return Optional.ofNullable(permissions[permission])
    }

    override fun getDescriptions(): MutableCollection<PermissionDescription> {
        return permissions.values
    }

    override fun getAllIdentifiers(): CompletableFuture<MutableSet<String>> {
        return CompletableFuture.completedFuture(collections.keys.toMutableSet())
    }

    override fun getCollection(identifier: String): Optional<SubjectCollection> {
        return Optional.ofNullable(collections[identifier])
    }

    override fun getDefaults(): Subject {
        return collections.getOrPut(SUBJECTS_DEFAULT) {
            SubjectCollectionImpl(SUBJECTS_DEFAULT)
        }.defaults
    }

    override fun hasCollection(identifier: String): CompletableFuture<Boolean> {
        return CompletableFuture.completedFuture(collections.containsKey(identifier))
    }
}