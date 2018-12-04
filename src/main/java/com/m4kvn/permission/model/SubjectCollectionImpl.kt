package com.m4kvn.permission.model

import org.spongepowered.api.service.context.Context
import org.spongepowered.api.service.permission.PermissionService
import org.spongepowered.api.service.permission.Subject
import org.spongepowered.api.service.permission.SubjectCollection
import org.spongepowered.api.service.permission.SubjectReference
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.function.Predicate

class SubjectCollectionImpl(
    private val identifier: String
) : SubjectCollection {
    private val subjects = mutableMapOf<String, Subject>()
    private val permissions = mutableMapOf<String, MutableMap<Subject, Boolean>>()

    override fun getIdentifier(): String = identifier

    override fun getAllWithPermission(permission: String): CompletableFuture<MutableMap<SubjectReference, Boolean>> {
        return CompletableFuture.completedFuture(permissions
            .getOrDefault(permission, mutableMapOf())
            .mapKeys { it.key.asSubjectReference() }
            .toMutableMap())
    }

    override fun getAllWithPermission(
        contexts: MutableSet<Context>,
        permission: String
    ): CompletableFuture<MutableMap<SubjectReference, Boolean>> {
        return getAllWithPermission(permission)
    }

    override fun newSubjectReference(subjectIdentifier: String): SubjectReference {
        return SubjectReferenceImpl(SubjectImpl(subjectIdentifier, this), this)
    }

    override fun getLoadedWithPermission(permission: String): MutableMap<Subject, Boolean> {
        return permissions.getOrPut(permission) { mutableMapOf() }
    }

    override fun getLoadedWithPermission(
        contexts: MutableSet<Context>,
        permission: String
    ): MutableMap<Subject, Boolean> {
        return getLoadedWithPermission(permission)
    }

    override fun getIdentifierValidityPredicate(): Predicate<String> {
        return Predicate { true }
    }

    override fun getSubject(identifier: String): Optional<Subject> {
        return Optional.ofNullable(subjects[identifier])
    }

    override fun suggestUnload(identifier: String) {
        subjects.remove(identifier)
        permissions.values.forEach { subjects ->
            subjects.keys.find { it.identifier == identifier }
                ?.let { subjects.remove(it) }
        }
    }

    override fun loadSubjects(identifiers: MutableSet<String>): CompletableFuture<MutableMap<String, Subject>> {
        identifiers.forEach { identifier ->
            val subject = SubjectImpl(identifier, this)
            subjects[identifier] = subject
        }
        return CompletableFuture.completedFuture(subjects)
    }

    override fun getLoadedSubjects(): MutableCollection<Subject> {
        return subjects.values
    }

    override fun getAllIdentifiers(): CompletableFuture<MutableSet<String>> {
        return CompletableFuture.completedFuture(subjects.keys.toMutableSet())
    }

    override fun hasSubject(identifier: String): CompletableFuture<Boolean> {
        return CompletableFuture.completedFuture(subjects.any { it.key == identifier })
    }

    override fun getDefaults(): Subject {
        return SubjectImpl(PermissionService.SUBJECTS_DEFAULT, this)
    }

    override fun loadSubject(identifier: String): CompletableFuture<Subject> {
        val subject = SubjectImpl(identifier, this)
        subjects[identifier] = subject
        return CompletableFuture.completedFuture(subject)
    }
}