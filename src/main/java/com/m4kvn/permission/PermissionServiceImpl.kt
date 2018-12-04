package com.m4kvn.permission

import com.m4kvn.permission.model.SubjectCollectionImpl
import org.spongepowered.api.Sponge
import org.spongepowered.api.service.context.ContextCalculator
import org.spongepowered.api.service.permission.*
import org.spongepowered.api.service.permission.PermissionService.SUBJECTS_GROUP
import org.spongepowered.api.service.permission.PermissionService.SUBJECTS_USER
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Predicate

class PermissionServiceImpl : PermissionService {
    private val users = SubjectCollectionImpl(SUBJECTS_USER)
    private val groups = SubjectCollectionImpl(SUBJECTS_GROUP)

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
        return users
    }

    override fun getGroupSubjects(): SubjectCollection {
        return groups
    }

    override fun registerContextCalculator(calculator: ContextCalculator<Subject>) {}

    override fun newSubjectReference(collectionIdentifier: String, subjectIdentifier: String): SubjectReference {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoadedCollections(): MutableMap<String, SubjectCollection> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIdentifierValidityPredicate(): Predicate<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newDescriptionBuilder(plugin: Any): PermissionDescription.Builder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadCollection(identifier: String): CompletableFuture<SubjectCollection> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDescription(permission: String): Optional<PermissionDescription> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDescriptions(): MutableCollection<PermissionDescription> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllIdentifiers(): CompletableFuture<MutableSet<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCollection(identifier: String): Optional<SubjectCollection> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDefaults(): Subject {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCollection(identifier: String): CompletableFuture<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}