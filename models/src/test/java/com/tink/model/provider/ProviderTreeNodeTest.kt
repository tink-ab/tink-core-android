package com.tink.model.provider

import com.tink.model.credentials.Credentials
import com.tink.model.provider.ProviderTreeNode.FinancialInstitutionGroupNode
import com.tink.model.provider.ProviderTreeNode.FinancialInstitutionNode
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Condition
import org.junit.jupiter.api.Test
import java.util.function.Predicate

internal class ProviderTreeNodeTest {

    private val nordeaBankId = Provider(
        name = "nordea-bankid",
        displayName = "Nordea",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )

    private val nordeaBankIdBusiness = Provider(
        name = "nordea-bankid",
        displayName = "Nordea",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.BUSINESS
    )

    private val nordeaBankIdCorporate = Provider(
        name = "nordea-bankid",
        displayName = "Nordea",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.CORPORATE
    )

    private val nordeaPassword = Provider(
        name = "nordea-password",
        displayName = "Nordea",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.PASSWORD,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Password",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )

    private val nordeaPasswordBusiness = Provider(
        name = "nordea-password",
        displayName = "Nordea",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.PASSWORD,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Password",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.BUSINESS
    )

    private val nordeaOpenBanking = Provider(
        name = "se-nordea-ob",
        displayName = "Nordea Open Banking",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Nordea",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OPEN_BANKING,
        financialInstitution = Provider.FinancialInstitution(
            id = "dde2463acf40501389de4fca5a3693a4",
            name = "Nordea"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )

    private val sparbankernaBankID = Provider(
        name = "savingsbank-bankid",
        displayName = "Sparbankerna",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Swedbank och Sparbankerna",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "a0afa9bbc85c52aba1b1b8d6a04bc57c",
            name = "Sparbankerna"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )
    private val sparbankernaPassword = Provider(
        name = "savingsbank-token",
        displayName = "Sparbankerna",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.PASSWORD,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Swedbank och Sparbankerna",
        images = null,
        displayDescription = "Security token",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "a0afa9bbc85c52aba1b1b8d6a04bc57c",
            name = "Sparbankerna"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )

    private val swedbankBankID = Provider(
        name = "swedbank-bankid",
        displayName = "Swedbank",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.MOBILE_BANKID,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Swedbank och Sparbankerna",
        images = null,
        displayDescription = "Mobile BankID",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "6c1749b4475e5677a83e9fa4bb60a18a",
            name = "Swedbank"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )
    private val swedbankPassword = Provider(
        name = "swedbank-token",
        displayName = "Swedbank",
        type = Provider.Type.BANK,
        status = Provider.Status.ENABLED,
        releaseStatus = Provider.ReleaseStatus.UNKNOWN,
        credentialsType = Credentials.Type.PASSWORD,
        helpText = "",
        isPopular = true,
        fields = emptyList(),
        groupDisplayName = "Swedbank och Sparbankerna",
        images = null,
        displayDescription = "Security token",
        marketCode = "SE",
        accessType = Provider.AccessType.OTHER,
        financialInstitution = Provider.FinancialInstitution(
            id = "6c1749b4475e5677a83e9fa4bb60a18a",
            name = "Swedbank"
        ),
        capabilities = emptyList(),
        authenticationUserType = Provider.AuthenticationUserType.PERSONAL
    )

    private val nordeaFinancialInstitution = Condition<FinancialInstitutionNode>(
        Predicate<FinancialInstitutionNode> { it.name == "Nordea" },
        "FinancialInstitution is 'Nordea'"
    )

    private val swedbankFinancialInstitution = Condition<FinancialInstitutionNode>(
        Predicate<FinancialInstitutionNode> { it.name == "Swedbank" },
        "FinancialInstitution is 'Swedbank'"
    )

    private val sparbankernaFinancialInstitution = Condition<FinancialInstitutionNode>(
        Predicate<FinancialInstitutionNode> { it.name == "Sparbankerna" },
        "FinancialInstitution is 'Sparbankerna'"
    )

    private val nordeaOpenBankingAccessType = Condition<ProviderTreeNode.AccessTypeNode>(
        Predicate<ProviderTreeNode.AccessTypeNode> { it.type == Provider.AccessType.OPEN_BANKING },
        "AccessType is OPEN_BANKING"
    )

    private val nordeaOtherAccessType = Condition<ProviderTreeNode.AccessTypeNode>(
        Predicate<ProviderTreeNode.AccessTypeNode> { it.type == Provider.AccessType.OTHER },
        "AccessType is OTHER"
    )

    @Test
    fun `test credentials types grouping`() {
        val providers = listOf(nordeaBankId, nordeaPassword)
        val groups = providers.toProviderTree()
        assertThat(groups).hasSize(1)

        val groupDisplayNameNode = groups.first()
        assertThat(groupDisplayNameNode)
            .isInstanceOf(FinancialInstitutionGroupNode::class.java)

        val financialInstitutions =
            (groupDisplayNameNode as FinancialInstitutionGroupNode).financialInstitutions
        assertThat(financialInstitutions).hasSize(1)

        val authenticationUserTypes = financialInstitutions.first().authenticationUserTypes
        val accessTypes = authenticationUserTypes.first().accessTypes
        assertThat(accessTypes).hasSize(1)

        val credentialsTypes = accessTypes.first().credentialsTypes
        assertThat(credentialsTypes)
            .hasSize(2)
            .anySatisfy { assertThat(it.type).isEqualTo(Credentials.Type.MOBILE_BANKID) }
            .anySatisfy { assertThat(it.type).isEqualTo(Credentials.Type.PASSWORD) }
    }

    @Test
    fun `test access type grouping`() {
        val providers = listOf(nordeaBankId, nordeaPassword, nordeaOpenBanking)
        val groups = providers.toProviderTree()
        assertThat(groups).hasSize(1)

        val groupDisplayNameNode = groups.first()
        assertThat(groupDisplayNameNode)
            .isInstanceOf(FinancialInstitutionGroupNode::class.java)

        val financialInstitutions =
            (groupDisplayNameNode as FinancialInstitutionGroupNode).financialInstitutions
        assertThat(financialInstitutions).hasSize(1)

        val authenticationUserTypes = financialInstitutions.first().authenticationUserTypes
        val accessTypes = authenticationUserTypes.first().accessTypes
        assertThat(accessTypes)
            .hasSize(2)
            .anySatisfy { assertThat(it.type).isEqualTo(Provider.AccessType.OPEN_BANKING) }
            .anySatisfy { assertThat(it.type).isEqualTo(Provider.AccessType.OTHER) }
    }

    @Test
    fun `test group display name grouping`() {
        val providers = listOf(nordeaBankId, nordeaPassword, swedbankBankID, swedbankPassword)
        val groups = providers.toProviderTree()
        assertThat(groups)
            .hasSize(2)
            .allSatisfy { it is FinancialInstitutionGroupNode }

        val nordeaGroup = groups[0] as FinancialInstitutionGroupNode
        val swedbankGroup = groups[1] as FinancialInstitutionGroupNode

        assertThat(nordeaGroup.financialInstitutions)
            .hasSize(1)
            .haveExactly(1, nordeaFinancialInstitution)

        assertThat(swedbankGroup.financialInstitutions)
            .hasSize(1)
            .haveExactly(1, swedbankFinancialInstitution)
    }

    @Test
    fun `test group display name and financial institution grouping`() {
        val providers = listOf(
            nordeaBankId,
            nordeaPassword,
            swedbankBankID,
            swedbankPassword,
            sparbankernaBankID,
            sparbankernaPassword
        )
        val groups = providers.toProviderTree()
        assertThat(groups)
            .describedAs("Check that we have two GroupDisplayName groups")
            .hasSize(2)
            .allMatch { it is FinancialInstitutionGroupNode }

        val nordeaGroup = groups[0] as FinancialInstitutionGroupNode
        val swedbankAndSparbankernaGroup = groups[1] as FinancialInstitutionGroupNode

        assertThat(nordeaGroup.financialInstitutions)
            .hasSize(1)
            .haveExactly(1, nordeaFinancialInstitution)

        assertThat(swedbankAndSparbankernaGroup.financialInstitutions)
            .describedAs("Check that Swedbank and Sparbankerna group contains both financialInstitutions")
            .hasSize(2)
            .haveExactly(1, swedbankFinancialInstitution)
            .haveExactly(1, sparbankernaFinancialInstitution)
    }

    @Test
    fun `test authentication user type grouping`() {
        val providers = listOf(nordeaBankId, nordeaBankIdBusiness, nordeaBankIdCorporate, nordeaPassword, nordeaPasswordBusiness, nordeaOpenBanking)
        val groups = providers.toProviderTree()
        assertThat(groups).hasSize(1)

        val groupDisplayNameNode = groups.first()
        assertThat(groupDisplayNameNode)
            .isInstanceOf(FinancialInstitutionGroupNode::class.java)

        val financialInstitutions =
            (groupDisplayNameNode as FinancialInstitutionGroupNode).financialInstitutions
        assertThat(financialInstitutions).hasSize(1)

        val authenticationUserTypes = financialInstitutions.first().authenticationUserTypes
        assertThat(authenticationUserTypes)
            .hasSize(3)
            .anySatisfy { assertThat(it.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.PERSONAL) }
            .anySatisfy { assertThat(it.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.BUSINESS) }
            .anySatisfy { assertThat(it.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.CORPORATE) }
    }

    @Test
    fun `test sort order of authentication user type grouping`() {
        val providers = listOf(nordeaBankId, nordeaBankIdBusiness, nordeaBankIdCorporate, nordeaPassword, nordeaPasswordBusiness, nordeaOpenBanking)
        val groups = providers.toProviderTree()
        val groupDisplayNameNode = groups.first()
        val financialInstitutions =
            (groupDisplayNameNode as FinancialInstitutionGroupNode).financialInstitutions
        val authenticationUserTypes = financialInstitutions.first().authenticationUserTypes

        val firstNode = authenticationUserTypes.first()
        val secondNode = authenticationUserTypes[1]
        val thirdNode = authenticationUserTypes[2]

        assertThat(firstNode.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.PERSONAL)
        assertThat(secondNode.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.BUSINESS)
        assertThat(thirdNode.authenticationUserType).isEqualTo(Provider.AuthenticationUserType.CORPORATE)
    }

    @Test
    fun `test access type grouping within authentication user type group`() {
        val providers = listOf(nordeaBankId, nordeaBankIdBusiness, nordeaBankIdCorporate, nordeaPassword, nordeaPasswordBusiness, nordeaOpenBanking)
        val groups = providers.toProviderTree()
        val groupDisplayNameNode = groups.first()
        val financialInstitutions =
            (groupDisplayNameNode as FinancialInstitutionGroupNode).financialInstitutions
        val authenticationUserTypes = financialInstitutions.first().authenticationUserTypes

        val personalGroup = authenticationUserTypes.first { it.authenticationUserType == Provider.AuthenticationUserType.PERSONAL }
        val businessGroup = authenticationUserTypes.first { it.authenticationUserType == Provider.AuthenticationUserType.BUSINESS }
        val corporateGroup = authenticationUserTypes.first { it.authenticationUserType == Provider.AuthenticationUserType.CORPORATE }

        assertThat(personalGroup.accessTypes)
            .hasSize(2)
            .haveExactly(1, nordeaOpenBankingAccessType)
            .haveExactly(1, nordeaOtherAccessType)
        assertThat(businessGroup.accessTypes)
            .hasSize(1)
            .haveExactly(1, nordeaOtherAccessType)
        assertThat(corporateGroup.accessTypes)
            .hasSize(1)
            .haveExactly(1, nordeaOtherAccessType)
    }
}
