package br.com.alura.mvc.mudi.seed;


import br.com.alura.mvc.mudi.model.Authority;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.AuthorityRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
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
        seedUsers();
        seedAuthorities();
    }
    public void seedUsers() {
        User admin = new User("admin", "$2a$10$EdFWbQWVcWM.SrROF2LiReu1wFa8eiQWmRcdg8z3jsK0MGmE.g05i",true);
        User user = new User("user", "$2a$10$qY2fK2csl97tM3r1BFKES.OMJr7aT8szt9KYMeEbi2omHf4s33FZe",true);

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
