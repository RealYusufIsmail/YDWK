/*
 * Copyright 2022 YDWK inc.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.ydwk.ydwk.entities

import com.fasterxml.jackson.databind.node.ArrayNode
import io.github.ydwk.ydwk.entities.audit.AuditLogType
import io.github.ydwk.ydwk.entities.channel.DmChannel
import io.github.ydwk.ydwk.entities.channel.GuildChannel
import io.github.ydwk.ydwk.entities.channel.getter.guild.GuildChannelGetter
import io.github.ydwk.ydwk.entities.channel.guild.GuildCategory
import io.github.ydwk.ydwk.entities.channel.guild.message.text.GuildTextChannel
import io.github.ydwk.ydwk.entities.channel.guild.vc.GuildVoiceChannel
import io.github.ydwk.ydwk.entities.guild.Ban
import io.github.ydwk.ydwk.entities.guild.Member
import io.github.ydwk.ydwk.entities.guild.Role
import io.github.ydwk.ydwk.entities.guild.WelcomeScreen
import io.github.ydwk.ydwk.entities.guild.enums.*
import io.github.ydwk.ydwk.entities.util.GenericEntity
import io.github.ydwk.ydwk.impl.YDWKImpl
import io.github.ydwk.ydwk.impl.entities.AuditLogImpl
import io.github.ydwk.ydwk.impl.entities.channel.DmChannelImpl
import io.github.ydwk.ydwk.impl.entities.guild.MemberImpl
import io.github.ydwk.ydwk.rest.EndPoint
import io.github.ydwk.ydwk.rest.result.NoResult
import io.github.ydwk.ydwk.util.GetterSnowFlake
import io.github.ydwk.ydwk.util.NameAbleEntity
import io.github.ydwk.ydwk.util.SnowFlake
import io.github.ydwk.ydwk.voice.VoiceConnection
import java.util.concurrent.CompletableFuture
import kotlin.time.Duration
import okhttp3.RequestBody.Companion.toRequestBody

/** This class is used to represent a discord guild object. */
interface Guild : SnowFlake, NameAbleEntity, GenericEntity {
    /**
     * The guild's icon hash.
     *
     * @return the guild's icon hash.
     */
    var icon: String?

    /**
     * The guild's splash hash.
     *
     * @return the guild's splash hash.
     */
    var splash: String?

    /**
     * The guild's discovery splash hash.
     *
     * @return the guild's discovery splash hash.
     */
    var discoverySplash: String?

    /**
     * Checks if this is user is owner of the guild.
     *
     * @return true if the user is owner of the guild.
     */
    var isOwner: Boolean?

    /**
     * The guild's owner id.
     *
     * @return the guild's owner id.
     */
    var ownerId: GetterSnowFlake

    /**
     * The guild's permissions.
     *
     * @return the guild's permissions.
     */
    var permissions: String?

    /**
     * The guild's afk channel id.
     *
     * @return the guild's afk channel id.
     */
    var afkChannelId: GetterSnowFlake?

    /**
     * The guild's afk timeout.
     *
     * @return the guild's afk timeout.
     */
    var afkTimeout: Int

    /**
     * Checks if the guild's widget is enabled.
     *
     * @return true if the guild's widget is enabled
     */
    var isWidgetEnabled: Boolean?

    /**
     * The guild's widget channel id.
     *
     * @return the guild's widget channel id.
     */
    var widgetChannelId: GetterSnowFlake?

    /**
     * The guild's verification level.
     *
     * @return the guild's verification level.
     */
    var verificationLevel: VerificationLevel

    /**
     * The guild's default message notification level.
     *
     * @return the guild's default message notification level.
     */
    var defaultMessageNotificationsLevel: MessageNotificationLevel

    /**
     * The guild's explicit content filter level.
     *
     * @return the guild's explicit content filter level.
     */
    var explicitContentFilterLevel: ExplicitContentFilterLevel

    /**
     * The guild's roles.
     *
     * @return the guild's roles.
     */
    var roles: List<Role>

    /**
     * The guild's emojis.
     *
     * @return the guild's emojis.
     */
    var emojis: List<Emoji>

    /**
     * The guild's features.
     *
     * @return the guild's features.
     */
    var features: Set<GuildFeature>

    /**
     * The guild's mfa level.
     *
     * @return the guild's mfa level.
     */
    var mfaLevel: MFALevel

    /**
     * The guild's application id.
     *
     * @return the guild's application id.
     */
    var applicationId: GetterSnowFlake?

    /**
     * The guild's system channel id.
     *
     * @return the guild's system channel id.
     */
    var systemChannelId: GetterSnowFlake?

    /**
     * The guild's system channel flags.
     *
     * @return the guild's system channel flags.
     */
    var systemChannelFlags: SystemChannelFlag

    /**
     * The guild's rules channel id.
     *
     * @return the guild's rules channel id.
     */
    var rulesChannelId: GetterSnowFlake?

    /**
     * The maximum number of presences for the guild (null is always returned, apart from the
     * largest of guilds).
     *
     * @return the maximum number of presences for the guild.
     */
    var maxPresences: Int?

    /**
     * The maximum number of members for the guild.
     *
     * @return the maximum number of members for the guild.
     */
    var maxMembers: Int

    /**
     * The vanity url code for the guild.
     *
     * @return the vanity url code for the guild.
     */
    var vanityUrlCode: String?

    /**
     * The description for the guild.
     *
     * @return the description for the guild.
     */
    var description: String?

    /**
     * The banner hash for the guild.
     *
     * @return the banner hash for the guild.
     */
    var banner: String?

    /**
     * The premium tier for the guild.
     *
     * @return the premium tier for the guild.
     */
    var premiumTier: PremiumTier

    /**
     * The premium subscription count for the guild.
     *
     * @return the premium subscription count for the guild.
     */
    var premiumSubscriptionCount: Int

    /**
     * The preferred locale for the guild.
     *
     * @return the preferred locale for the guild.
     */
    var preferredLocale: String

    /**
     * The public updates channel id for the guild.
     *
     * @return the public updates channel id for the guild.
     */
    var publicUpdatesChannelId: GetterSnowFlake?

    /**
     * The maximum amount of users in a video channel.
     *
     * @return the maximum amount of users in a video channel.
     */
    var maxVideoChannelUsers: Int?

    /**
     * The approximate member count for the guild.
     *
     * @return the approximate member count for the guild.
     */
    var approximateMemberCount: Int?

    /**
     * The approximate presence count for the guild.
     *
     * @return the approximate presence count for the guild.
     */
    var approximatePresenceCount: Int?

    /**
     * The welcome screen of the guild.
     *
     * @return the welcome screen of the guild.
     */
    var welcomeScreen: WelcomeScreen?

    /**
     * The guild's nsfw level.
     *
     * @return the guild's nsfw level.
     */
    var nsfwLevel: NSFWLeveL

    /**
     * The guild's custom stickers.
     *
     * @return the guild's custom stickers.
     */
    var stickers: List<Sticker>

    /**
     * Gets weather the guild has the boost progress bar enabled.
     *
     * @return weather the guild has the boost progress bar enabled.
     */
    var isBoostProgressBarEnabled: Boolean

    /**
     * Requests a list of ban's for the guild.
     *
     * @return a list of ban's for the guild.
     */
    val requestBans: CompletableFuture<List<Ban>>

    /**
     * Creates a dm channel..
     *
     * @param userId The id of the user.
     * @return The [DmChannel] object.
     */
    fun createDmChannel(userId: Long): CompletableFuture<DmChannel> {
        return ydwk.restApiManager
            .post(
                ydwk.objectMapper
                    .createObjectNode()
                    .put("recipient_id", id)
                    .toString()
                    .toRequestBody(),
                EndPoint.UserEndpoint.CREATE_DM)
            .execute {
                val jsonBody = it.jsonBody
                if (jsonBody == null) {
                    throw IllegalStateException("json body is null")
                } else {
                    DmChannelImpl(ydwk, jsonBody, jsonBody["id"].asLong())
                }
            }
    }

    /**
     * Creates a dm channel.
     *
     * @param userId The id of the user.
     * @return The [DmChannel] object.
     */
    fun createDmChannel(userId: String): CompletableFuture<DmChannel> =
        createDmChannel(userId.toLong())

    /**
     * Creates a dm channel.
     *
     * @param user The user who you want to create a dm channel with.
     * @return The [DmChannel] object.
     */
    fun createDmChannel(user: User): CompletableFuture<DmChannel> = createDmChannel(user.id)

    /**
     * The bot as a member of the guild.
     *
     * @return The [Member] object.
     * @throws IllegalStateException If the bot is not in the guild.
     */
    val botAsMember: Member

    /**
     * Bans a user from the guild.
     *
     * @param userId The id of the user.
     * @param deleteMessageDuration The duration of the messages to delete.
     * @param reason The reason for the ban.
     * @return A [CompletableFuture] that completes when the ban is created.
     */
    fun banUser(
        userId: Long,
        deleteMessageDuration: Duration = Duration.ZERO,
        reason: String? = null
    ): CompletableFuture<NoResult> {
        return ydwk.restApiManager
            .put(
                ydwk.objectMapper
                    .createObjectNode()
                    .put("delete_message_seconds", deleteMessageDuration.inWholeSeconds)
                    .toString()
                    .toRequestBody(),
                EndPoint.GuildEndpoint.BAN,
                id,
                userId.toString())
            .addReason(reason)
            .executeWithNoResult()
    }

    /**
     * Bans a user from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the ban.
     * @return A [CompletableFuture] that completes when the ban is created.
     */
    fun banUser(userId: Long, reason: String? = null) = banUser(userId, Duration.ZERO, reason)

    /**
     * Bans a user from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the ban.
     * @return A [CompletableFuture] that completes when the ban is created.
     */
    fun banUser(userId: String, reason: String? = null) = banUser(userId, Duration.ZERO, reason)

    /**
     * Bans a user from the guild.
     *
     * @param userId The id of the user.
     * @param deleteMessageDuration The duration of the messages to delete.
     * @param reason The reason for the ban.
     * @return A [CompletableFuture] that completes when the ban is created.
     */
    fun banUser(
        userId: String,
        deleteMessageDuration: Duration = Duration.ZERO,
        reason: String? = null
    ): CompletableFuture<NoResult> = banUser(userId.toLong(), deleteMessageDuration, reason)

    /**
     * Bans a user from the guild.
     *
     * @param user The user to ban.
     * @param deleteMessageDuration The duration of the messages to delete.
     * @param reason The reason for the ban.
     */
    fun banUser(
        user: User,
        deleteMessageDuration: Duration = Duration.ZERO,
        reason: String? = null
    ): CompletableFuture<NoResult> = banUser(user.id, deleteMessageDuration, reason)

    /**
     * Bans a member from the guild.
     *
     * @param member The member to ban.
     * @param deleteMessageDuration The duration of the messages to delete.
     * @param reason The reason for the ban.
     * @return A [CompletableFuture] that completes when the ban is created.
     */
    fun banMember(
        member: Member,
        deleteMessageDuration: Duration = Duration.ZERO,
        reason: String? = null
    ): CompletableFuture<NoResult> = banUser(member.user, deleteMessageDuration, reason)

    /**
     * Unbans a user from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the unban.
     * @return A [CompletableFuture] that completes when the unban is created.
     */
    fun unbanUser(userId: Long, reason: String? = null): CompletableFuture<NoResult> {
        return ydwk.restApiManager
            .delete(EndPoint.GuildEndpoint.BAN, id, userId.toString())
            .addReason(reason)
            .executeWithNoResult()
    }

    /**
     * Unbans a user from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the unban.
     * @return A [CompletableFuture] that completes when the unban is created.
     */
    fun unbanUser(userId: String, reason: String? = null): CompletableFuture<NoResult> =
        unbanUser(userId.toLong(), reason)

    /**
     * Unbans a user from the guild.
     *
     * @param user The user to unban.
     * @param reason The reason for the unban.
     * @return A [CompletableFuture] that completes when the unban is created.
     */
    fun unbanUser(user: User, reason: String? = null): CompletableFuture<NoResult> =
        unbanUser(user.id, reason)

    /**
     * Kicks a member from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the kick.
     * @return A [CompletableFuture] that completes when the kick is created.
     */
    fun kickMember(userId: Long, reason: String? = null): CompletableFuture<NoResult> {
        return ydwk.restApiManager
            .delete(EndPoint.GuildEndpoint.KICK, id, userId.toString())
            .addReason(reason)
            .executeWithNoResult()
    }

    /**
     * Kicks a member from the guild.
     *
     * @param userId The id of the user.
     * @param reason The reason for the kick.
     * @return A [CompletableFuture] that completes when the kick is created.
     */
    fun kickMember(userId: String, reason: String? = null): CompletableFuture<NoResult> =
        kickMember(userId.toLong(), reason)

    /**
     * Kicks a member from the guild.
     *
     * @param member The member to kick.
     * @param reason The reason for the kick.
     * @return A [CompletableFuture] that completes when the kick is created.
     */
    fun kickMember(member: Member, reason: String? = null): CompletableFuture<NoResult> =
        kickMember(member.user.id, reason)

    /**
     * Request the audit log for the guild.
     *
     * @param userId The id of the user.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @param before Entries that preceded a specific audit log entry ID.
     * @param actionType The type of action to filter by.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(
        userId: GetterSnowFlake? = null,
        limit: Int = 50,
        before: GetterSnowFlake? = null,
        actionType: AuditLogType? = null
    ): CompletableFuture<AuditLog> {
        val rest = ydwk.restApiManager

        if (userId != null) {
            rest.addQueryParameter("user_id", userId.asString)
        }

        if (before != null) {
            rest.addQueryParameter("before", before.asString)
        }

        if (actionType != null) {
            rest.addQueryParameter("action_type", actionType.getType().toString())
        }

        return rest
            .addQueryParameter("limit", limit.toString())
            .get(EndPoint.GuildEndpoint.GET_AUDIT_LOGS, id)
            .execute {
                val jsonBody = it.jsonBody
                if (jsonBody == null) {
                    throw IllegalStateException("json body is null")
                } else {
                    AuditLogImpl(ydwk, jsonBody)
                }
            }
    }

    /**
     * Request the audit log for the guild.
     *
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(): CompletableFuture<AuditLog> =
        requestedAuditLog(GetterSnowFlake.asNull, 50, null, null)

    /**
     * Request the audit log for the guild.
     *
     * @param userId The id of the user.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(userId: GetterSnowFlake): CompletableFuture<AuditLog> =
        requestedAuditLog(userId, 50, null, null)

    /**
     * Request the audit log for the guild.
     *
     * @param userId The id of the user.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(userId: GetterSnowFlake, limit: Int): CompletableFuture<AuditLog> =
        requestedAuditLog(userId, limit, null, null)

    /**
     * Request the audit log for the guild.
     *
     * @param userId The id of the user.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @param before Entries that preceded a specific audit log entry ID.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(
        userId: GetterSnowFlake,
        limit: Int,
        before: GetterSnowFlake
    ): CompletableFuture<AuditLog> = requestedAuditLog(userId, limit, before, null)

    /**
     * Request the audit log for the guild.
     *
     * @param user The user to filter by.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @param before Entries that preceded a specific audit log entry ID.
     * @param actionType The type of action to filter by.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(
        user: User? = null,
        limit: Int = 50,
        before: GetterSnowFlake? = null,
        actionType: AuditLogType? = null
    ): CompletableFuture<AuditLog> = requestedAuditLog(user, limit, before, actionType)

    /**
     * Request the audit log for the guild.
     *
     * @param user The user to filter by.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(user: User): CompletableFuture<AuditLog> =
        requestedAuditLog(user, 50, null, null)

    /**
     * Request the audit log for the guild.
     *
     * @param user The user to filter by.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(user: User, limit: Int): CompletableFuture<AuditLog> =
        requestedAuditLog(user, limit, null, null)

    /**
     * Request the audit log for the guild.
     *
     * @param user The user to filter by.
     * @param limit Maximum number of entries (between 1-100) to return, defaults to 50
     * @param before Entries that preceded a specific audit log entry ID.
     * @return A [CompletableFuture] that completes when the audit log is retrieved.
     */
    fun requestedAuditLog(
        user: User,
        limit: Int,
        before: GetterSnowFlake
    ): CompletableFuture<AuditLog> = requestedAuditLog(user, limit, before, null)

    /**
     * Gets a role from the guild.
     *
     * @param roleId The id of the role.
     * @return The role, or null if it doesn't exist.
     */
    fun getRoleById(roleId: Long): Role?

    /**
     * Gets a role from the guild.
     *
     * @param roleId The id of the role.
     * @return The role, or null if it doesn't exist.
     */
    fun getRoleById(roleId: String): Role? = getRoleById(roleId.toLong())

    /**
     * Gets all the channels as unordered list.
     *
     * @return The channels.
     */
    val getUnorderedChannels: List<GuildChannel>

    /**
     * Gets all the channels as sorted list.
     *
     * @return The channels.
     */
    val getChannels: List<GuildChannel>
        get() = getUnorderedChannels.sortedBy { it.position }

    /**
     * Gets all the categories as sorted list.
     *
     * @return The categories.
     */
    val getCategories: List<GuildCategory>
        get() = getChannels.filterIsInstance<GuildCategory>()

    /**
     * Gets all the text channels as sorted list.
     *
     * @return The text channels.
     */
    val getTextChannels: List<GuildTextChannel>
        get() = getChannels.filterIsInstance<GuildTextChannel>()

    /**
     * Gets all the voice channels as sorted list.
     *
     * @return The voice channels.
     */
    val getVoiceChannels: List<GuildVoiceChannel>
        get() = getChannels.filterIsInstance<GuildVoiceChannel>()

    /**
     * The channel by its id.
     *
     * @param channelId The id of the channel.
     * @return The channel, or null if it doesn't exist.
     */
    fun getChannelById(channelId: Long): GuildChannel?

    /**
     * The channel by its id.
     *
     * @param channelId The id of the channel.
     * @return The channel, or null if it doesn't exist.
     */
    fun getChannelById(channelId: String): GuildChannel? = getChannelById(channelId.toLong())

    /**
     * The channel getter by its id.
     *
     * @param channelId The id of the channel.
     * @return The channel getter.
     */
    fun getChannelGetterById(channelId: Long): GuildChannelGetter? =
        getChannelById(channelId)?.guildChannelGetter

    /**
     * The channel getter by its id.
     *
     * @param channelId The id of the channel.
     * @return The channel getter.
     */
    fun getChannelGetterById(channelId: String): GuildChannelGetter? =
        getChannelGetterById(channelId.toLong())

    /**
     * Gets all the members of the guild.
     *
     * @return The members.
     */
    val retrieveMembers: CompletableFuture<List<Member>>
        get() {
            return ydwk.restApiManager
                .addQueryParameter("limit", "")
                .get(EndPoint.GuildEndpoint.GET_MEMBERS, id)
                .execute {
                    val jsonBody = it.jsonBody
                    val members: ArrayNode = jsonBody as ArrayNode
                    val memberList = mutableListOf<Member>()
                    for (member in members) {
                        memberList.add(MemberImpl(ydwk as YDWKImpl, member, this))
                    }
                    memberList
                }
        }

    /**
     * Gets all the members of the guild.
     *
     * @param limit The limit of members to retrieve.
     * @return The members.
     */
    fun retrieveMembers(limit: Int): CompletableFuture<List<Member>> {
        return ydwk.restApiManager
            .addQueryParameter("limit", limit.toString())
            .get(EndPoint.GuildEndpoint.GET_MEMBERS, id)
            .execute {
                val jsonBody = it.jsonBody
                val members: ArrayNode = jsonBody as ArrayNode
                val memberList = mutableListOf<Member>()
                for (member in members) {
                    memberList.add(MemberImpl(ydwk as YDWKImpl, member, this))
                }
                memberList
            }
    }

    /**
     * The member by its user id.
     *
     * @param userId The id of the user.
     * @return The member, or null if it doesn't exist.
     */
    fun getMemberById(userId: Long): Member? {
        return ydwk.getMemberById(idAsLong, userId)
    }

    /**
     * The member by its user id.
     *
     * @param userId The id of the user.
     * @return The member, or null if it doesn't exist.
     */
    fun getMemberById(userId: String): Member? = getMemberById(userId.toLong())

    /**
     * Joins a vc.
     *
     * @param guildVoiceChannelId the guild vc id to join.
     * @param muted if the bot should be muted.
     * @param deafened if the bot should be deafened.
     * @return A [CompletableFuture] that completes when the bot joins the vc.
     */
    fun joinVoiceChannel(
        guildVoiceChannelId: Long,
        muted: Boolean,
        deafened: Boolean
    ): CompletableFuture<VoiceConnection>

    /**
     * Joins a vc.
     *
     * @param guildVoiceChannelId the guild vc id to join.
     * @param muted if the bot should be muted.
     * @param deafened if the bot should be deafened.
     * @return A [CompletableFuture] that completes when the bot joins the vc.
     */
    fun joinVoiceChannel(
        guildVoiceChannelId: String,
        muted: Boolean,
        deafened: Boolean
    ): CompletableFuture<VoiceConnection> =
        joinVoiceChannel(guildVoiceChannelId.toLong(), muted, deafened)

    /**
     * Joins a vc.
     *
     * @param guildVoiceChannel the guild vc to join.
     * @param muted if the bot should be muted.
     * @param deafened if the bot should be deafened.
     * @return A [CompletableFuture] that completes when the bot joins the vc.
     */
    fun joinVoiceChannel(
        guildVoiceChannel: GuildVoiceChannel,
        muted: Boolean,
        deafened: Boolean
    ): CompletableFuture<VoiceConnection> = joinVoiceChannel(guildVoiceChannel.id, muted, deafened)

    /**
     * Leaves a vc.
     *
     * @param guildVoiceChannelId the guild vc id to leave.
     * @return A [CompletableFuture] that completes when the bot leaves the vc.
     */
    fun leaveVoiceChannel(guildVoiceChannelId: Long): CompletableFuture<Void>

    /**
     * Leaves a vc.
     *
     * @param guildVoiceChannelId the guild vc id to leave.
     * @return A [CompletableFuture] that completes when the bot leaves the vc.
     */
    fun leaveVoiceChannel(guildVoiceChannelId: String): CompletableFuture<Void> =
        leaveVoiceChannel(guildVoiceChannelId.toLong())

    /**
     * Leaves a vc.
     *
     * @param guildVoiceChannel the guild vc to leave.
     * @return A [CompletableFuture] that completes when the bot leaves the vc.
     */
    fun leaveVoiceChannel(guildVoiceChannel: GuildVoiceChannel): CompletableFuture<Void> =
        leaveVoiceChannel(guildVoiceChannel.id)

    /**
     * The @everyone role. This role is always present.
     *
     * @return The @everyone role.
     */
    val everyoneRole: Role
        get() =
            getRoleById(idAsLong)
                ?: throw IllegalStateException("The @everyone role is not present.")

    /**
     * Gets a voice connection if it exists.
     *
     * @return The [VoiceConnection] instance, or null if it doesn't exist.
     */
    val voiceConnection: VoiceConnection?

    /**
     * Sets the voice connection.
     *
     * @param voiceConnection The voice connection.
     */
    fun setVoiceConnection(voiceConnection: VoiceConnection)

    /**
     * Removes the voice connection.
     *
     * @param voiceConnection The voice connection.
     */
    fun removeVoiceConnection(voiceConnection: VoiceConnection)
}
