package com.example.springinaction.tacos

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val username: String,
    private val password: String,
    val fullName: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val phoneNumber: String,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    /*override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }*/

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?
}

@Service
class UserRepositoryDetailsService(
    val userRepository: UserRepository
) : UserDetailsService {

    private val log: Logger = LoggerFactory.getLogger(UserRepositoryDetailsService::class.java)

    override fun loadUserByUsername(username: String?): UserDetails = when (username) {
        null -> {
            log.error("username is null in loadUserByUsername")
            throw UsernameNotFoundException("No username was provided")
        }
        else -> userRepository.findByUsername(username)?.let {
            log.debug("Retrieved user $username from repository")
            it
        }
            ?: run {
                log.warn("Username $username not found")
                throw UsernameNotFoundException("User $username not found")
            }
    }

}

data class RegistrationForm(
    val username: String,
    private val password: String,
    private val fullname: String,
    private val street: String,
    private val city: String,
    private val state: String,
    private val zip: String,
    private val phoneNumber: String
) {
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username = username,
            password = passwordEncoder.encode(password),
            fullName = fullname,
            street = street,
            city = city,
            state = state,
            zip = zip,
            phoneNumber = phoneNumber
        )
    }
}