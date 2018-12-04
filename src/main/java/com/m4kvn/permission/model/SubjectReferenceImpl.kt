package com.m4kvn.permission.model

import org.spongepowered.api.service.permission.Subject
import org.spongepowered.api.service.permission.SubjectCollection
import org.spongepowered.api.service.permission.SubjectReference
import java.util.concurrent.CompletableFuture

class SubjectReferenceImpl(
    private val subject: Subject,
    private val collection: SubjectCollection
) : SubjectReference {

    override fun resolve(): CompletableFuture<Subject> {
        return CompletableFuture.completedFuture(subject)
    }

    override fun getSubjectIdentifier(): String {
        return subject.identifier
    }

    override fun getCollectionIdentifier(): String {
        return collection.identifier
    }
}