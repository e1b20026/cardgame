package playingcards.cardgame.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class CardGameAuthConfiguration {

  /**
   * 認証処理に関する設定（誰がどのようなロールでログインできるか）
   *
   * @return
   */
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    // このクラスの下部にあるPasswordEncoderを利用して，ユーザのビルダ（ログインするユーザを作成するオブジェクト）を作成する
    UserBuilder users = User.builder();

    // UserBuilder usersにユーザ名，パスワード，ロールを指定してbuildする
    // このときパスワードはBCryptでハッシュ化されている．
    // ハッシュ化されたパスワードを得るには，この授業のbashターミナルで下記のように末尾にユーザ名とパスワードを指定すると良い(要VPN)
    // $ sshrun htpasswd -nbBC 10 user1 p@ss
    // ロールを複数追加することもできる
    UserDetails kitamoto = users //pass:isdev
        .username("kitamoto")
        .password("$2y$10$1K49WFaQQeeI0dTV7DACh.acmMlNVwaAC9r/1s/A9m0wV3F2dmezy")
        .roles("USER", "MANAGER")
        .build();

    UserDetails komaki = users //pass:isdev
        .username("komaki")
        .password("$2y$10$QlkLFq7fslc13CFiEwHG0e6eVL5YKJkLjKxlRgEemmexmKpAQuZZC")
        .roles("USER", "MANAGER")
        .build();

    UserDetails ogawa = users //pass:isdev
        .username("ogawa")
        .password("$2y$10$rp80a5F43CzKVOEzycbi/On1wrHjHPmLtGWMq28Q6Qqkvfg5r.xv2")
        .roles("USER", "MANAGER")
        .build();

    UserDetails kurokawa = users //pass:isdev
        .username("kurokawa")
        .password("$2y$10$khvkOd7vWY0HOjDesG5YPufHOPU8aIkX2ysPfimf55TBnt1DiD9zm")
        .roles("USER", "MANAGER")
        .build();

    // 生成したユーザをImMemoryUserDetailsManagerに渡す（いくつでも良い）
    return new InMemoryUserDetailsManager(kitamoto,komaki,ogawa,kurokawa);
  }

  /**
   * 認可処理に関する設定（認証されたユーザがどこにアクセスできるか）
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Spring Securityのフォームを利用してログインを行う（自前でログインフォームを用意することも可能）
    http.formLogin();
    // http://localhost:8000/sample3 で始まるURLへのアクセスはログインが必要
    // mvcMatchers().authenticated()がmvcMatchersに指定されたアクセス先に認証処理が必要であることを示す
    // authenticated()の代わりにpermitAll()と書くと認証不要となる
    http.authorizeHttpRequests()
        .mvcMatchers("/").authenticated();
    http.logout().logoutSuccessUrl("/"); // ログアウト時は "http://localhost:8000/" に戻る
    /**
     * 以下2行はh2-consoleを利用するための設定なので，開発が完了したらコメントアウトすることが望ましい
     * CSRFがONになっているとフォームが対応していないためアクセスできない
     * HTTPヘッダのX-Frame-OptionsがDENYになるとiframeでlocalhostでのアプリが使えなくなるので，H2DBのWebクライアントのためだけにdisableにする必要がある
     */
    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();
  }

  /**
   *
   * UserBuilder users = User.builder();で利用するPasswordEncoderを指定する．
   *
   * @return BCryptPasswordEncoderを返す
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
