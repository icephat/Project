<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the website, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/documentation/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** Database username */
define( 'DB_USER', 'root' );

/** Database password */
define( 'DB_PASSWORD', '' );

/** Database hostname */
define( 'DB_HOST', 'localhost' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         '48C{6`yIN=`Sd,Y^rg>K NzbS;96n2)Es*{8J?dhgABt#7eb(SpO,nSYf%5lG*}G' );
define( 'SECURE_AUTH_KEY',  'YNt+w-TDjGT&.W8y+I~_NMJ^hZ8[W5=skb|HJ7JMQGKmWMa&kvrgsZQ7h[n.dh*3' );
define( 'LOGGED_IN_KEY',    '),_mjCGjz.MagO(vG5YyhR0ytz_S/YW6=ups.y+@HwX5?T#,GlHXNP~h>nX~q$ P' );
define( 'NONCE_KEY',        'zAJ_i} W )8belo[T0L~?,,&jdv;x0tTXd;c8?as!u!~^fGIS<~#m*c/YIV7ny I' );
define( 'AUTH_SALT',        'JC4Hc_FXgsQPGq=VIceZWG-)DEz4+7_n)3rE2n)kD}?9@69<F+%nI1P70-TpH+/f' );
define( 'SECURE_AUTH_SALT', 'I%S-o[Zzc7<k4q%`@n-oI9wwy~PH4-%d(Im-SFq@*e.Kusaw*R^80)a6wul?`j+=' );
define( 'LOGGED_IN_SALT',   'pPT$n.+YW9S!St^99_U!a U%[}2LezTKc9|pOzLOSJ nmw[@,kq}D=|V6;dMZ-#r' );
define( 'NONCE_SALT',       'v1zKdk>9r,~6PF;nxBcV`2#8?Bj;OlreL,Q`50xnFLk;>YeFX6;Fd/L8by$PmR!2' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/documentation/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';
