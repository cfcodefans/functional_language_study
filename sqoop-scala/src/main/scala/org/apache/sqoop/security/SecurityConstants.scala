package org.apache.sqoop.security

import org.apache.sqoop.core.ConfigurationConstants

/**
  * Created by fan on 2016/8/12.
  */
object SecurityConstants {
	/**
	  * All security related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.</tt>
	  */
	val PREFIX_SECURITY_CONFIG: String = ConfigurationConstants.PREFIX_GLOBAL_CONFIG + "security."
	/**
	  * All authentication related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.authentication.</tt>
	  */
	val PREFIX_AUTHENTICATION_CONFIG: String = PREFIX_SECURITY_CONFIG + "authentication."
	/**
	  * All tls related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.tls.</tt>
	  */
	val PREFIX_TLS_CONFIG: String = PREFIX_SECURITY_CONFIG + "tls."
	/**
	  * All repository encryption related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.repo_encryption.</tt>
	  */
	val PREFIX_REPO_ENCRYPTION_CONFIG: String = PREFIX_SECURITY_CONFIG + "repo_encryption."
	/**
	  * The config specifies the sqoop authentication type (SIMPLE, KERBEROS).
	  * The default type is SIMPLE
	  * <tt>org.apache.sqoop.security.authentication.type</tt>.
	  */
	val AUTHENTICATION_TYPE: String = PREFIX_AUTHENTICATION_CONFIG + "type"
	/**
	  * The config specifies the sqoop authentication handler class.
	  * The default type is org.apache.sqoop.security.authentication.SimpleAuthenticationHandler
	  * <tt>org.apache.sqoop.security.authentication.handler</tt>.
	  */
	val AUTHENTICATION_HANDLER: String = PREFIX_AUTHENTICATION_CONFIG + "handler"
	/**
	  * The config enables or disables anonymous authentication.
	  * <tt>org.apache.sqoop.security.authentication.anonymous</tt>.
	  */
	val AUTHENTICATION_ANONYMOUS: String = PREFIX_AUTHENTICATION_CONFIG + "anonymous"
	/**
	  * All kerberos authentication related configuration is prefixed with this:
	  * <tt>org.apache.security.sqoop.authentication.kerberos.</tt>
	  */
	val PREFIX_AUTHENTICATION_KERBEROS_CONFIG: String = PREFIX_AUTHENTICATION_CONFIG + "kerberos."
	/**
	  * The config specifies the default user.
	  */
	val AUTHENTICATION_DEFAULT_USER: String = PREFIX_AUTHENTICATION_CONFIG + "default.user"
	val AUTHENTICATION_DEFAULT_USER_DEFAULT: String = "sqoop.anonymous.user"
	/**
	  * The config specifies the kerberos principal.
	  * <tt>org.apache.sqoop.security.authentication.kerberos.principal</tt>.
	  */
	val AUTHENTICATION_KERBEROS_PRINCIPAL: String = PREFIX_AUTHENTICATION_KERBEROS_CONFIG + "principal"
	/**
	  * The config specifies the kerberos keytab.
	  * <tt>org.apache.sqoop.security.authentication.kerberos.principal</tt>.
	  */
	val AUTHENTICATION_KERBEROS_KEYTAB: String = PREFIX_AUTHENTICATION_KERBEROS_CONFIG + "keytab"
	/**
	  * All kerberos authentication for http related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.authentication.kerberos.http.</tt>
	  */
	val PREFIX_AUTHENTICATION_KERBEROS_HTTP_CONFIG: String = PREFIX_AUTHENTICATION_KERBEROS_CONFIG + "http."
	/**
	  * The config specifies the kerberos principal for http.
	  * <tt>org.apache.sqoop.security.authentication.kerberos.http.principal</tt>.
	  */
	val AUTHENTICATION_KERBEROS_HTTP_PRINCIPAL: String = PREFIX_AUTHENTICATION_KERBEROS_HTTP_CONFIG + "principal"
	/**
	  * The config specifies the kerberos keytab for http.
	  * <tt>org.apache.sqoop.security.authentication.kerberos.http.keytab</tt>.
	  */
	val AUTHENTICATION_KERBEROS_HTTP_KEYTAB: String = PREFIX_AUTHENTICATION_KERBEROS_HTTP_CONFIG + "keytab"
	/**
	  * All authorization related configuration is prefixed with this:
	  * <tt>org.apache.sqoop.security.authorization.</tt>
	  */
	val PREFIX_AUTHORIZATION_CONFIG: String = PREFIX_SECURITY_CONFIG + "authorization."
	/**
	  * The config specifies the sqoop authorization handler class.
	  * The default type is org.apache.sqoop.security.authorization.DefaultAuthorizationHandler
	  * <tt>org.apache.sqoop.security.authorization.handler</tt>.
	  */
	val AUTHORIZATION_HANDLER: String = PREFIX_AUTHORIZATION_CONFIG + "handler"
	/**
	  * The config specifies the sqoop authorization access controller class.
	  * The default type is org.apache.sqoop.security.authorization.DefaultAuthorizationAccessController
	  * <tt>org.apache.sqoop.security.authorization.access_controller</tt>.
	  */
	val AUTHORIZATION_ACCESS_CONTROLLER: String = PREFIX_AUTHORIZATION_CONFIG + "access_controller"
	/**
	  * The config specifies the sqoop authorization validator class.
	  * The default type is org.apache.sqoop.security.authorization.DefaultAuthorizationValidator
	  * <tt>org.apache.sqoop.security.authorization.validator</tt>.
	  */
	val AUTHORIZATION_VALIDATOR: String = PREFIX_AUTHORIZATION_CONFIG + "validator"
	/**
	  * The config specifies the sqoop authentication provider class.
	  * The default type is org.apache.sqoop.security.authorization.DefaultAuthenticationProvider
	  * <tt>org.apache.sqoop.security.authorization.authentication_provider</tt>.
	  */
	val AUTHENTICATION_PROVIDER: String = PREFIX_AUTHORIZATION_CONFIG + "authentication_provider"
	/**
	  * The config specifies the sqoop server name for authorization.
	  * The default server name is "SqoopServer1"
	  * <tt>org.apache.sqoop.security.authorization.server_name</tt>.
	  */
	val SERVER_NAME: String = PREFIX_AUTHORIZATION_CONFIG + "server_name"
	/**
	  * The config specifies the whether the http server should use TLS.
	  * <tt>org.apache.sqoop.security.tls.enabled</tt>.
	  */
	val TLS_ENABLED: String = PREFIX_TLS_CONFIG + "enabled"
	/**
	  * The config specifies the tls protocol version
	  * <tt>org.apache.sqoop.security.tls.protocol</tt>.
	  */
	val TLS_PROTOCOL: String = PREFIX_TLS_CONFIG + "protocol"
	/**
	  * The config specifies the location of the JKS formatted keystore
	  * <tt>org.apache.sqoop.security.tls.keystore</tt>.
	  */
	val KEYSTORE_LOCATION: String = PREFIX_TLS_CONFIG + "keystore"
	/**
	  * The config specifies the password of the JKS formatted keystore
	  * <tt>org.apache.sqoop.security.tls.keystore_password</tt>.
	  */
	val KEYSTORE_PASSWORD: String = PREFIX_TLS_CONFIG + "keystore_password"
	/**
	  * The config specifies a script that outputs the password of the JKS formatted keystore
	  * to standard out
	  * <tt>org.apache.sqoop.security.tls.keystore_password_generator</tt>.
	  */
	val KEYSTORE_PASSWORD_GENERATOR: String = PREFIX_TLS_CONFIG + "keystore_password_generator"
	/**
	  * The config specifies the password for the private key in the JKS formatted
	  * keystore
	  * <tt>org.apache.sqoop.security.tls.keymanagerpassword</tt>.
	  */
	val KEYMANAGER_PASSWORD: String = PREFIX_TLS_CONFIG + "keymanager_password"
	/**
	  * The config specifies a script that outputs the password for the
	  * private key in the JKS formatted keystore to standard out
	  * <tt>org.apache.sqoop.security.tls.keymanager_password_generator</tt>.
	  */
	val KEYMANAGER_PASSWORD_GENERATOR: String = PREFIX_TLS_CONFIG + "keymanager_password_generator"
	/**
	  * The config specifies if repository encryption is enabled
	  * <tt>org.apache.sqoop.security.repo_encryption.enabled</tt>.
	  */
	val REPO_ENCRYPTION_ENABLED: String = PREFIX_REPO_ENCRYPTION_CONFIG + "enabled"
	/**
	  * The config specifies the password used to encrypt the repository
	  * <tt>org.apache.sqoop.security.repo_encryption.password</tt>.
	  */
	val REPO_ENCRYPTION_PASSWORD: String = PREFIX_REPO_ENCRYPTION_CONFIG + "password"
	/**
	  * The config specifies a command that prints the password used to encrypt
	  * the repository to standard out
	  * <tt>org.apache.sqoop.security.repo_encryption.password_generator</tt>.
	  */
	val REPO_ENCRYPTION_PASSWORD_GENERATOR: String = PREFIX_REPO_ENCRYPTION_CONFIG + "password_generator"
	/**
	  * The config specifies the algorithm to be used for hmac generation
	  * <tt>org.apache.sqoop.security.repo_encryption.hmac_algorithm</tt>.
	  */
	val REPO_ENCRYPTION_HMAC_ALGORITHM: String = PREFIX_REPO_ENCRYPTION_CONFIG + "hmac_algorithm"
	/**
	  * The config specifies the algorithm to be used for repository encryption
	  * <tt>org.apache.sqoop.security.repo_encryption.cipher_algorithm</tt>.
	  */
	val REPO_ENCRYPTION_CIPHER_ALGORITHM: String = PREFIX_REPO_ENCRYPTION_CONFIG + "cipher_algorithm"
	/**
	  * The config specifies the spec to be used for repository encryption
	  * <tt>org.apache.sqoop.security.repo_encryption.cipher_spec</tt>.
	  */
	val REPO_ENCRYPTION_CIPHER_SPEC: String = PREFIX_REPO_ENCRYPTION_CONFIG + "cipher_spec"
	/**
	  * The config specifies the size of the key used for repository encryption
	  * <tt>org.apache.sqoop.security.repo_encryption.cipher_key_size</tt>.
	  */
	val REPO_ENCRYPTION_CIPHER_KEY_SIZE: String = PREFIX_REPO_ENCRYPTION_CONFIG + "cipher_key_size"
	/**
	  * The config specifies the size of the initialization vector used for repository encryption
	  * <tt>org.apache.sqoop.security.repo_encryption.initialization_vector_size</tt>.
	  */
	val REPO_ENCRYPTION_INITIALIZATION_VECTOR_SIZE: String = PREFIX_REPO_ENCRYPTION_CONFIG + "initialization_vector_size"
	/**
	  * The config specifies the pbkdf2 algorithm to be used for master key generation
	  * <tt>org.apache.sqoop.security.repo_encryption.pbkdf2_algorithm</tt>.
	  */
	val REPO_ENCRYPTION_PBKDF2_ALGORITHM: String = PREFIX_REPO_ENCRYPTION_CONFIG + "pbkdf2_algorithm"
	/**
	  * The config specifies the number of rounds of the pbkdf2 algorithm
	  * to be used for master key generation
	  * <tt>org.apache.sqoop.security.repo_encryption.pbkdf2_algorithm</tt>.
	  */
	val REPO_ENCRYPTION_PBKDF2_ROUNDS: String = PREFIX_REPO_ENCRYPTION_CONFIG + "pbkdf2_rounds"
	/**
	  * The config specifies the token kind in delegation token.
	  */
	val TOKEN_KIND: String = "sqoop_token_kind"

	object TYPE extends Enumeration {
		type TYPE = Value
		val SIMPLE, KERBEROS = Value
	}
}
