package com.tink.model.provider

import android.os.Parcelable
import com.tink.model.credentials.Credentials
import com.tink.model.provider.ProviderTreeNode.AccessTypeNode
import com.tink.model.provider.ProviderTreeNode.AuthenticationUserTypeNode
import com.tink.model.provider.ProviderTreeNode.CredentialsTypeNode
import com.tink.model.provider.ProviderTreeNode.FinancialInstitutionGroupNode
import com.tink.model.provider.ProviderTreeNode.FinancialInstitutionNode
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * This class represents a tree structure of [FinancialInstitutionGroupNode] objects with children.
 * This eventually leads to a leaf object of type [ProviderNode],
 * that contains more detailed [Provider] data.
 *
 * The tree will always follow the structure:
 *
 * [FinancialInstitutionGroupNode] -> [FinancialInstitutionNode] -> [AccessTypeNode] -> [CredentialsTypeNode] -> [ProviderNode]
 */
sealed class ProviderTreeNode : Parcelable {
    /** A textual description of the node. */
    abstract val name: String?
    /** An optional url linking to a visual description of the node. */
    abstract val icon: String?
    /** Denotes that there is at least one underlying Provider in beta. */
    abstract val isBeta: Boolean

    /**
     * The top level node of the tree structure, with a list of [FinancialInstitutionNode] children.
     *
     * @param name The grouping identifier related to the [Provider]'s `groupDisplayName` or
     *              `financialInstitution.name` property.
     * @param financialInstitutions The list of child nodes.
     */
    @Parcelize
    data class FinancialInstitutionGroupNode(
        override val name: String,
        val financialInstitutions: @RawValue List<FinancialInstitutionNode>
    ) : ProviderTreeNode() {
        override val icon: String?
            get() = financialInstitutions.first().icon
        override val isBeta: Boolean
            get() = financialInstitutions.any { it.isBeta }
    }

    /**
     * A parent node of the tree structure, with a list of [AuthenticationUserTypeNode] children.
     *
     * @param financialInstitution The `financialInstitution` that this node represents.
     * @param authenticationUserTypes The list of child nodes.
     */
    @Parcelize
    data class FinancialInstitutionNode(
        val financialInstitution: Provider.FinancialInstitution,
        val authenticationUserTypes: @RawValue List<AuthenticationUserTypeNode>
    ) : ProviderTreeNode() {
        override val name: String get() = financialInstitution.name
        override val icon: String?
            get() = authenticationUserTypes.first().icon
        override val isBeta: Boolean
            get() = authenticationUserTypes.any { it.isBeta }
    }

    /**
     * A parent node of the tree structure, with a list of [AccessTypeNode] children.
     *
     * @param authenticationUserType The `authenticationUserType` that this node represents.
     * @param accessTypes The list of child nodes.
     */
    @Parcelize
    data class AuthenticationUserTypeNode(
        val authenticationUserType: Provider.AuthenticationUserType,
        val accessTypes: @RawValue List<AccessTypeNode>
    ) : ProviderTreeNode() {
        override val name: String? get() = null
        override val icon: String?
            get() = accessTypes.first().icon
        override val isBeta: Boolean
            get() = accessTypes.any { it.isBeta }
    }

    /**
     * A parent node of the tree structure, with a list of [CredentialsTypeNode] children.
     *
     * @param type Grouping identifier. See [Provider.AccessType]
     * @param credentialsTypes The list of child nodes.
     */
    @Parcelize
    data class AccessTypeNode(
        val type: Provider.AccessType,
        val credentialsTypes: @RawValue List<CredentialsTypeNode>
    ) : ProviderTreeNode() {
        override val name: String? get() = null
        override val icon: String?
            get() = credentialsTypes.first().icon
        override val isBeta: Boolean
            get() = credentialsTypes.any { it.isBeta }
    }

    /**
     * A parent node of the tree structure, with a list of [ProviderNode] children.
     *
     * @param type Grouping identifier. See [Credentials.Type]
     * @param providers The list of child nodes.
     */
    @Parcelize
    data class CredentialsTypeNode(
        override val name: String?,
        val type: Credentials.Type,
        val providers: List<ProviderNode>
    ) : ProviderTreeNode() {
        override val icon
            get() = providers.first().icon
        override val isBeta
            get() = providers.any { it.isBeta }
    }

    /**
     * The leaf node of the tree structure, containing the more detailed [Provider] object.
     */
    @Parcelize
    data class ProviderNode(
        val provider: Provider
    ) : ProviderTreeNode() {
        override val name: String
            get() = provider.displayName
        override val icon
            get() = provider.images?.icon
        override val isBeta: Boolean
            get() = provider.releaseStatus == Provider.ReleaseStatus.BETA
    }
}

/**
 * Groups the providers by a few defining elements, creating a tree structure.
 * Each level in the tree structure may have 1 to `n` children.
 *
 * @return A tree of [ProviderTreeNode] objects that will always follow the structure:
 *
 * [FinancialInstitutionGroupNode] -> [FinancialInstitutionNode] -> [AccessTypeNode] -> [CredentialsTypeNode]
 */
fun List<Provider>.toProviderTree(): List<ProviderTreeNode> =
    groupBy { it.groupDisplayName.ifBlank { it.financialInstitution.name } }
        .map { (name, providers) ->
            FinancialInstitutionGroupNode(name, providers.groupByFinancialInstitution())
        }
        .sortedBy { it.name }

private fun List<Provider>.groupByFinancialInstitution(): List<FinancialInstitutionNode> =
    groupBy { it.financialInstitution }
        .map { (financialInstitution, providers) ->
            FinancialInstitutionNode(financialInstitution, providers.groupByAuthenticationUserType())
        }
        .sortedBy { it.name }

private fun List<Provider>.groupByAuthenticationUserType(): List<AuthenticationUserTypeNode> =
    groupBy { it.authenticationUserType }
        .map { (authenticationUserType, providers) ->
            AuthenticationUserTypeNode(authenticationUserType, providers.groupByAccessType())
        }
        .sortedBy { it.authenticationUserType.ordinal }

private fun List<Provider>.groupByAccessType(): List<AccessTypeNode> =
    groupBy { it.accessType }
        .map { (type, providers) ->
            AccessTypeNode(type, providers.groupByCredentialsType())
        }
        .sortedBy { it.name }

private fun List<Provider>.groupByCredentialsType(): List<CredentialsTypeNode> =
    groupBy { it.credentialsType }
        .flatMap { (type, providers) ->
            /*
             * Group the credentials type internally by displayDescription as a first step.
             * This is necessary since we sometimes have providers with same credentials type that
             * have different descriptions, for example PASSWORD is used for multiple purposes,
             * and we might have "Pin" and "Password" as two different descriptions.
             *
             * This lets the CredentialsTypeNode allow for a better UI where the providers with the
             * same credentials type can use different names.
             */
            providers
                .groupBy { it.displayDescription }
                .map { (displayDescription, providerList) ->
                    CredentialsTypeNode(
                        name = displayDescription.takeIf { it.isNotEmpty() },
                        type = type,
                        providers = providerList.map { ProviderTreeNode.ProviderNode(it) }
                    )
                }
        }
        .sortedBy { it.name }
