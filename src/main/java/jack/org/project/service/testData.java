package jack.org.project.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jack.org.project.model.User;
import jack.org.project.model.Role;
import jack.org.project.repository.UserRepository;
import jack.org.project.repository.RoleRepository;
import jack.org.project.service.UserService;

@Transactional
@Service
public class testData {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void Init() {

		User user = new User();
		user.setUsername("jack");
		String password = "test";
		user.setPassword(bCryptPasswordEncoder.encode(password));

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		Set<GrantedAuthority> grantedAuthoritiesAdmin = new HashSet<>();
		grantedAuthoritiesAdmin.add(new SimpleGrantedAuthority(roleAdmin.getName()));

		Role roleCustomer = new Role();
		roleCustomer.setName("ROLE_CUSTOMER");
		roleRepository.save(roleCustomer);
		Set<GrantedAuthority> grantedAuthoritiesCustomer = new HashSet<>();
		grantedAuthoritiesCustomer.add(new SimpleGrantedAuthority(roleCustomer.getName()));
		
		HashSet<Role> roles = new HashSet<>();
		roles.add(roleCustomer);
		user.setRoles(roles);
		userRepository.save(user);

	}
}