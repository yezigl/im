package perhaps.love.im.service;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.directory.Attribute;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import perhaps.love.im.entity.User;
import perhaps.love.im.entity.UserMapping;

@Service
public class LdapService {

    @Resource
    LdapTemplate ldapTemplate;

    public User find(String username, String password) {
        LdapQuery ldapQuery = LdapQueryBuilder.query().base("ou=sohu-users").filter(new EqualsFilter("mail", username));
        List<User> userList = ldapTemplate.search(ldapQuery, (AttributesMapper<User>) attributes -> {
            final User user = new User();
            for (UserMapping um : UserMapping.values()) {
                Attribute attribute = attributes.get(um.getAttr());
                if (attribute != null) {
                    um.getConsumer().accept(user, (String) attribute.get());
                }
            }
            return user;
        });
        return userList.isEmpty() ? null : userList.get(0);
    }
}
