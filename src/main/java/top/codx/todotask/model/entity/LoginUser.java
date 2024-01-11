package top.codx.todotask.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * security UserDetails实现类
 *
 * @author Liuch
 * @since 2023-04-03 22:38
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 8027200311511691695L;

    /**
     * 用户信息
     */
    private SysUser sysUser;

    /**
     * 权限列表
     */
    private List<String> permissions;

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 把permissions中的权限信息封装成SimpleGrantedAuthority对象
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserPasswd();
    }

    @Override
    public String getUsername() {
        if (sysUser == null) {
            return null;
        }
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
