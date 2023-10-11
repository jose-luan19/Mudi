package br.com.alura.mvc.mudi.seed;


import br.com.alura.mvc.mudi.model.Authority;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.AuthorityRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Seed {
    private final UserRepository userRepository;
    private final AuthorityRepository authoritiesRepository;

    @Autowired
    public Seed(UserRepository userRepository, AuthorityRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        if (userRepository.getOne("admin") == null) {
            seedUsers();
        }
        if (authoritiesRepository.getOne(1L) == null) {
            seedAuthorities();
        }
    }
    public void seedUsers() {
        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

        String senhaAdmin = crypt.encode("admin");
        String senhaUser = crypt.encode("user");

        User admin = new User("admin", senhaAdmin,true);
        User user = new User("user", senhaUser,true);

        userRepository.save(admin);
        userRepository.save(user);
    }

    public void seedAuthorities() {
        Authority adminADM = new Authority(1L,"admin","ADM");
        Authority userADM = new Authority(2L, "user","ADM");

        authoritiesRepository.save(adminADM);
        authoritiesRepository.save(userADM);
    }
}
